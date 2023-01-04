package TDT.backend.service;

import TDT.backend.dto.schedule.ScheduleRequestDto;
import TDT.backend.dto.schedule.ScheduleResForMember;
import TDT.backend.dto.schedule.ScheduleResForTeam;
import TDT.backend.dto.schedule.TodoCheckRequestDto;
import TDT.backend.entity.MemberSchedule;
import TDT.backend.entity.Schedule;
import TDT.backend.entity.Team;
import TDT.backend.entity.TeamMember;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.memberSchedule.MemberScheduleRepository;
import TDT.backend.repository.schedule.ScheduleRepository;
import TDT.backend.repository.team.TeamRepository;
import TDT.backend.repository.teamMember.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final MemberScheduleRepository memberScheduleRepository;

    public void addSchedule(ScheduleRequestDto dto) {
        Team team = teamRepository.findById(dto.getStudyId())
                .orElseThrow(() -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS));

        TeamMember member = teamMemberRepository.findByMemberIdAndTeamId(dto.getMemberId(), dto.getStudyId())
                .orElseThrow(() -> new BusinessException(ExceptionCode.NOT_TEAM_MEMBERS));

        if (member.getIsLeader()) {
            Schedule schedule = Schedule.builder()
                    .team(team)
                    .title(dto.getTitle())
                    .contents(dto.getContents())
                    .endAt(dto.getEndAt())
                    .status(dto.getStatus())
                    .build();

            scheduleRepository.save(schedule);

            for (TeamMember teamMember : team.getTeamMembers()) {
                memberScheduleRepository.save(
                        MemberSchedule.builder()
                                .teamMember(teamMember)
                                .schedule(schedule)
                                .isDoneTodo(false)
                                .build());
            }
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);

    }

    @Transactional(readOnly = true)
    public List<ScheduleResForMember> getSchedulesForMember(Long memberId) {

        List<ScheduleResForMember> schedules = memberScheduleRepository.findSchedulesByMemberId(memberId);

        return schedules;
    }

    @Transactional(readOnly = true)
    public List<ScheduleResForTeam> getSchedulesForTeam(Long studyId) {

        Team team = teamRepository.findById(studyId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS));

        List<ScheduleResForTeam> schedules =
                team.getSchedules().stream().map(schedule ->
                ScheduleResForTeam.builder()
                        .title(schedule.getTitle())
                        .contents(schedule.getContents())
                        .endAt(schedule.getEndAt())
                        .status(schedule.getStatus())
                        .build()
        ).collect(Collectors.toList());

        return schedules;
    }

    public void editSchedule(Long scheduleId, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.SCHEDULE_NOT_EXISTS));

        TeamMember member = teamMemberRepository.findByMemberIdAndTeamId(requestDto.getMemberId(), requestDto.getStudyId())
                .orElseThrow(() -> new BusinessException(ExceptionCode.NOT_TEAM_MEMBERS));

        if (member.getIsLeader()) {
            schedule.edit(requestDto.getTitle(), requestDto.getContents(), requestDto.getEndAt(), requestDto.getStatus());
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    public void deleteSchedule(Long scheduleId, Long memberId, Long studyId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.SCHEDULE_NOT_EXISTS));

        TeamMember member = teamMemberRepository.findByMemberIdAndTeamId(memberId, studyId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.NOT_TEAM_MEMBERS));

        if (member.getIsLeader()) {
            scheduleRepository.delete(schedule);
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    public void isDoneTodo(TodoCheckRequestDto dto) {

    }
}







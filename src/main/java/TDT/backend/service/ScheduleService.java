package TDT.backend.service;

import TDT.backend.dto.schedule.ScheduleAddReqDto;
import TDT.backend.dto.schedule.ScheduleEditReqDto;
import TDT.backend.dto.schedule.ScheduleResForMember;
import TDT.backend.dto.schedule.TodoCheckRequestDto;
import TDT.backend.entity.*;
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

@RequiredArgsConstructor
@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final MemberScheduleRepository memberScheduleRepository;

    public void addSchedule(Long studyId, ScheduleAddReqDto dto, Member member) {
        Team team = teamRepository.findById(studyId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS));

        TeamMember leader = teamMemberRepository.findLeaderByTeamId(studyId);

        if (leader.getMember().getId().equals(member.getId())) {
            dto.getSchedules().forEach(scheduleTitleDto -> {
                Schedule schedule = dto.toEntity(team, scheduleTitleDto);
                scheduleRepository.save(schedule);

                for (TeamMember teamMember : team.getTeamMembers()) {
                    memberScheduleRepository.save(MemberSchedule.of(teamMember, schedule));
                }
            });
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);

    }

    @Transactional(readOnly = true)
    public List<ScheduleResForMember> getSchedulesForMember(Long memberId) {

        List<ScheduleResForMember> schedules = memberScheduleRepository.findSchedulesByMemberId(memberId);

        return schedules;
    }

    /*사용안함*/
//    @Transactional(readOnly = true)
//    public List<ScheduleResForTeam> getSchedulesForTeam(Long studyId) {
//
//        Team team = teamRepository.findById(studyId)
//                .orElseThrow(() -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS));
//
//        List<ScheduleResForTeam> schedules =
//                team.getSchedules().stream().map(schedule ->
//                        ScheduleResForTeam.builder()
//                                .title(schedule.getTitle())
//                                .contents(schedule.getContents())
//                                .endAt(schedule.getEndAt())
//                                .status(schedule.getStatus())
//                                .build()
//                ).collect(Collectors.toList());
//
//        return schedules;
//    }

    /*수정중*/
    public void editSchedule(Long scheduleId, ScheduleEditReqDto requestDto, Member member) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.SCHEDULE_NOT_EXISTS));

        TeamMember leader = teamMemberRepository.findLeaderByTeamId(schedule.getTeam().getId());

        if (leader.getMember().getId().equals(member.getId())) {

            requestDto.getScheduleEditTitleDto().forEach(scheduleEditTitleDto -> {
                schedule.edit(scheduleEditTitleDto.getTitle(), requestDto.getDate(), ScheduleStatus.FINISHED);
            });

        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    public void deleteSchedule(Long scheduleId, Member member, Long studyId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.SCHEDULE_NOT_EXISTS));

        TeamMember teamMember = teamMemberRepository.findByMemberIdAndTeamId(member.getId(), studyId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.NOT_TEAM_MEMBERS));

        if (teamMember.getIsLeader()) {
            scheduleRepository.delete(schedule);
        } else throw new BusinessException(ExceptionCode.UNAUTHORIZED_ERROR);
    }

    public void isDoneTodo(TodoCheckRequestDto dto) {

    }
}







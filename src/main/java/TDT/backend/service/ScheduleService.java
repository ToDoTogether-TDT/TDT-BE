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

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final MemberScheduleRepository memberScheduleRepository;

    public List<ScheduleAddReqDto.ScheduleTitleDto> addSchedule(Long studyId, ScheduleAddReqDto dto, Member member) {
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
        List<ScheduleAddReqDto.ScheduleTitleDto> response = new ArrayList<>(dto.getSchedules());
        return response;
    }

    @Transactional(readOnly = true)
    public List<ScheduleResForMember> getSchedulesForMember(Long memberId) {

        List<ScheduleResForMember> schedules = memberScheduleRepository.findSchedulesByMemberId(memberId);

        return schedules;
    }

    public void editSchedule(Long studyId, ScheduleEditReqDto requestDto, Member member) {

        TeamMember leader = teamMemberRepository.findLeaderByTeamId(studyId);

        if (leader.getMember().getId().equals(member.getId())) {

            requestDto.getScheduleEditTitleDto().forEach(scheduleEditTitleDto -> {
                Schedule schedule = scheduleRepository.findById(scheduleEditTitleDto.getScheduleId())
                        .orElseThrow(() -> new BusinessException(ExceptionCode.SCHEDULE_NOT_EXISTS));

                schedule.edit(scheduleEditTitleDto.getTitle(), requestDto.getDate());
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

    public boolean isDoneTodo(TodoCheckRequestDto dto) {

        return memberScheduleRepository.findByMemberIdAndScheduleId(dto.getMemberId(), dto.getScheduleId());
    }

}







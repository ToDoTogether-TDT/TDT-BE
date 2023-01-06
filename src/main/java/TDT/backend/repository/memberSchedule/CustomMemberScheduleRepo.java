package TDT.backend.repository.memberSchedule;

import TDT.backend.dto.member.MemberDto;
import TDT.backend.dto.schedule.ScheduleResForMember;
import TDT.backend.dto.team.StudyResponseDto;

import java.util.List;

public interface CustomMemberScheduleRepo {

    List<ScheduleResForMember> findSchedulesByMemberId(Long memberId);

    List<MemberDto> findIsDoneTodoMembers(Long scheduleId);

    List<MemberDto> findMembersByStudyId(Long studyId);

    boolean findByMemberIdAndScheduleId(Long memberId, Long scheduleId);
}

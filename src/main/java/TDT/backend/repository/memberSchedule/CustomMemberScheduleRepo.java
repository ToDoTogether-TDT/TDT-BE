package TDT.backend.repository.memberSchedule;

import TDT.backend.dto.schedule.ScheduleResForMember;

import java.util.List;

public interface CustomMemberScheduleRepo {

    List<ScheduleResForMember> findSchedulesByMemberId(Long memberId);
}

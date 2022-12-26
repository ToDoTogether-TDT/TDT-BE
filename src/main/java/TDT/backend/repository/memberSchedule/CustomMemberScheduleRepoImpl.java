package TDT.backend.repository.memberSchedule;

import TDT.backend.dto.schedule.ScheduleResForMember;
import TDT.backend.entity.QMember;
import TDT.backend.entity.QTeam;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static TDT.backend.entity.QMemberSchedule.*;

@RequiredArgsConstructor
public class CustomMemberScheduleRepoImpl implements CustomMemberScheduleRepo{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScheduleResForMember> findSchedulesByMemberId(Long memberId) {

        return queryFactory
                .select(Projections.fields(ScheduleResForMember.class,
                        memberSchedule.teamMember.team.title.as("teamTitle"), memberSchedule.schedule.title.as("scheduleTitle"),
                        memberSchedule.schedule.contents.as("scheduleContents"), memberSchedule.schedule.endAt, memberSchedule.schedule.status))
                .from(memberSchedule)
                .where(memberSchedule.teamMember.member.id.eq(memberId))
                .orderBy(memberSchedule.schedule.endAt.asc())
                .fetch();
    }
}
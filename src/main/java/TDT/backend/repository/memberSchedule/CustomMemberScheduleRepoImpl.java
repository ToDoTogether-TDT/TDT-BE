package TDT.backend.repository.memberSchedule;

import TDT.backend.dto.member.MemberDto;
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
                        memberSchedule.schedule.endAt, memberSchedule.schedule.status))
                .from(memberSchedule)
                .where(memberSchedule.teamMember.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public List<MemberDto> findIsDoneTodoMembers(Long scheduleId) {
        return queryFactory
                .select(Projections.fields(MemberDto.class,
                        memberSchedule.teamMember.member.nickname, memberSchedule.teamMember.member.picture.as("image"),
                        memberSchedule.teamMember.member.introduction))
                .from(memberSchedule)
                .where(memberSchedule.schedule.id.eq(scheduleId))
                .fetch();
    }

    @Override
    public List<MemberDto> findMembersByStudyId(Long studyId) {
        return queryFactory
                .select(Projections.fields(MemberDto.class,
                        memberSchedule.schedule.id.as("scheduleId"), memberSchedule.teamMember.member.nickname,
                        memberSchedule.teamMember.member.picture.as("image"), memberSchedule.isDoneTodo))
                .from(memberSchedule)
                .where(memberSchedule.teamMember.team.id.eq(studyId).and(memberSchedule.isDoneTodo.eq(true)))
                .fetch();
    }
}

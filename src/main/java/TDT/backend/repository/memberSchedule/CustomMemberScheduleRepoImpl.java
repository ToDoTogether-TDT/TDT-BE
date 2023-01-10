package TDT.backend.repository.memberSchedule;

import TDT.backend.dto.member.MemberDto;
import TDT.backend.dto.schedule.QScheduleResForMember;
import TDT.backend.dto.schedule.ScheduleResForMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static TDT.backend.entity.QMemberSchedule.memberSchedule;
import static TDT.backend.entity.QTeam.team;

@RequiredArgsConstructor
@Slf4j
public class CustomMemberScheduleRepoImpl implements CustomMemberScheduleRepo {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScheduleResForMember> findSchedulesByMemberId(Long memberId) {


        return queryFactory.select(new QScheduleResForMember(team, memberSchedule.schedule))
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

    @Override
    public boolean findByMemberIdAndScheduleId(Long memberId, Long scheduleId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(memberSchedule)
                .where(memberSchedule.teamMember.member.id.eq(memberId), memberSchedule.schedule.id.eq(scheduleId)
                        , memberSchedule.isDoneTodo.eq(true))
                .fetchFirst();

        log.info(String.valueOf(fetchOne));
        return fetchOne != null;
    }

}

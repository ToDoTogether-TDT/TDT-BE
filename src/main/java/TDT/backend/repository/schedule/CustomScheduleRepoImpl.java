package TDT.backend.repository.schedule;

import TDT.backend.dto.member.MemberDto;
import TDT.backend.dto.schedule.ScheduleDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static TDT.backend.entity.QMemberSchedule.memberSchedule;
import static TDT.backend.entity.QSchedule.schedule;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@RequiredArgsConstructor
public class CustomScheduleRepoImpl implements CustomScheduleRepo {

    private final JPAQueryFactory queryFactory;

//    @Override
//    public List<ScheduleDto> findScheduleByStudyId(Long studyId) {
//        return queryFactory
//                .select(Projections.fields(ScheduleDto.class,
//                        schedule.id, schedule.title,
//                        JPAExpressions.select(Projections.fields(MemberDto.class,
//                                        memberSchedule.teamMember.member.nickname, memberSchedule.teamMember.member.picture.as("image"),
//                                        memberSchedule.teamMember.member.introduction))
//                                .from(memberSchedule)
//                                .where(memberSchedule.schedule.id.eq(schedule.id))))
//                .from(schedule)
//                .join(memberSchedule).fetchJoin()
//                .where(memberSchedule.teamMember.team.id.eq(studyId))
//                .fetch();
//
//    }

    public List<ScheduleDto> findScheduleByStudyId(Long studyId) {
        return queryFactory
                .from(schedule)
                .join(memberSchedule).fetchJoin()
                .on(memberSchedule.schedule.id.eq(schedule.id).and(memberSchedule.isDoneTodo.eq(true)))
                .where(memberSchedule.teamMember.team.id.eq(studyId))
                .transform(groupBy(schedule.id).list(
                        Projections.fields(ScheduleDto.class,
                                schedule.id.as("scheduleId"), schedule.title,
                                list(
                                        Projections.fields(MemberDto.class,
                                        memberSchedule.teamMember.member.nickname, memberSchedule.teamMember.member.picture.as("image"),
                                        memberSchedule.teamMember.member.introduction)).as("doneTodoMembers"))));

    }
}

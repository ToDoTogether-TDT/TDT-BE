package TDT.backend.repository.schedule;

import TDT.backend.dto.member.MemberDto;
import TDT.backend.dto.schedule.ScheduleCheckedDto;
import TDT.backend.dto.schedule.ScheduleDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
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
        List<ScheduleDto> scheduleDtos = queryFactory
                .from(schedule)
                .join(memberSchedule).fetchJoin()
                .on(memberSchedule.schedule.id.eq(schedule.id))
                .where(schedule.team.id.eq(studyId))
                .transform(groupBy(schedule.endAt.as("date"))
                        .list(Projections.fields(ScheduleDto.class, schedule.endAt.as("date"),
                                list(Projections.fields(ScheduleCheckedDto.class,
                                        schedule.id.as("scheduleId"), schedule.title)).as("lists"))));

        return scheduleDtos;

    }
}

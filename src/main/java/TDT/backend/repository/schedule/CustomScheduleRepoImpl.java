package TDT.backend.repository.schedule;

import TDT.backend.dto.schedule.ScheduleCheckedDto;
import TDT.backend.dto.schedule.ScheduleDto;
import com.querydsl.core.types.Projections;
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

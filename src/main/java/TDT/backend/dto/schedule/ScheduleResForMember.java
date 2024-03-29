package TDT.backend.dto.schedule;

import TDT.backend.entity.Schedule;
import TDT.backend.entity.ScheduleStatus;
import TDT.backend.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ScheduleResForMember {

    private String teamTitle;
    private String scheduleTitle;
    private Date endAt;
    private ScheduleStatus status;

    @QueryProjection
    public ScheduleResForMember(Team team, Schedule schedule) {
        this.teamTitle = team.getTitle();
        this.scheduleTitle = schedule.getTitle();
        this.status = schedule.getStatus();
        this.endAt = schedule.getEndAt();
    }

}

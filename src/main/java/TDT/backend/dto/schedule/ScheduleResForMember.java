package TDT.backend.dto.schedule;

import TDT.backend.entity.ScheduleStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleResForMember {

    private String teamTitle;
    private String scheduleTitle;
    private LocalDateTime endAt;
    private ScheduleStatus status;
}

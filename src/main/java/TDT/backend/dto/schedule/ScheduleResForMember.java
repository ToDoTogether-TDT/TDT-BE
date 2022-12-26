package TDT.backend.dto.schedule;

import TDT.backend.entity.ScheduleStatus;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleResForMember {

    private String teamTitle;
    private String scheduleTitle;
    private String scheduleContents;
    private LocalDateTime endAt;
    private ScheduleStatus status;
}

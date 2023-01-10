package TDT.backend.dto.schedule;

import TDT.backend.entity.ScheduleStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleResForTeam {

    private String title;
    private String contents;
    private LocalDateTime endAt;
    private ScheduleStatus status;

    @Builder
    public ScheduleResForTeam(String title, String contents, LocalDateTime endAt, ScheduleStatus status) {
        this.title = title;
        this.contents = contents;
        this.endAt = endAt;
        this.status = status;
    }
}

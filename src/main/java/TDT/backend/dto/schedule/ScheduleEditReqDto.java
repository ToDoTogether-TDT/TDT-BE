package TDT.backend.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleEditReqDto {

    private Date date;
    private List<ScheduleEditTitleDto> scheduleEditTitleDto;

    @Getter
    public static class ScheduleEditTitleDto {
        private Long scheduleId;
        private String title;
    }
}


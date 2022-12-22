package TDT.backend.dto.schedule;

import TDT.backend.entity.ScheduleStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleRequestDto {

    private Long studyId;
    private Long memberId;
    private String title;
    private String contents;
    private LocalDateTime endAt;
    private ScheduleStatus status;
}

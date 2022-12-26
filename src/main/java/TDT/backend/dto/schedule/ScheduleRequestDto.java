package TDT.backend.dto.schedule;

import TDT.backend.entity.ScheduleStatus;
import lombok.Builder;
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

    // 테스트용
    @Builder
    public ScheduleRequestDto(Long studyId, Long memberId, String title, String contents, LocalDateTime endAt, ScheduleStatus status) {
        this.studyId = studyId;
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.endAt = endAt;
        this.status = status;
    }
}

package TDT.backend.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoCheckRequestDto {

    private Long scheduleId;
    private Long memberId;

}

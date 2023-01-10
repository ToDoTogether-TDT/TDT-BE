package TDT.backend.dto.team;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyJoinReqDto {
    private Long studyId;
    private Long memberId;
}

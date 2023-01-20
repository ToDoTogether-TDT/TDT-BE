package TDT.backend.dto.team;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyJoinReqMemberDto {
    private Long memberId;
    private String nickname;
    private String image;
}

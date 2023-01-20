package TDT.backend.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileReqDto {

    private String nickname;
    private String introduction;
    private String category;

}


package TDT.backend.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileReqDto {

    private String email;
    private String name;
    private String picture;

}


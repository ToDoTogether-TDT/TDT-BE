package TDT.backend.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private String nickname;
    private String image;
    private String introduction;

    @Builder
    public MemberDto(String nickname, String image, String introduction) {
        this.nickname = nickname;
        this.image = image;
        this.introduction = introduction;
    }
}

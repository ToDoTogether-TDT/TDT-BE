package TDT.backend.dto.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeMemberResponseDto {

    private Long memberId;
    private String nickname;
    private String image;

    @Builder
    public NoticeMemberResponseDto(Long memberId, String nickname, String image) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.image = image;
    }
}

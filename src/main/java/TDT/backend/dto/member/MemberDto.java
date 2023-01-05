package TDT.backend.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private Long scheduleId;
    private String nickname;
    private String image;
    private Boolean isDoneTodo;

    @Builder
    public MemberDto(String nickname, String image, Boolean isDoneTodo) {
        this.nickname = nickname;
        this.image = image;
        this.isDoneTodo = isDoneTodo;
    }

    @Builder
    public static class MemberDtoRes {
        @JsonProperty
        private String nickname;
        @JsonProperty
        private String image;
    }

    public MemberDtoRes toMemberDto() {
        return MemberDtoRes.builder()
                .nickname(this.nickname)
                .image(this.image)
                .build();
    }


}

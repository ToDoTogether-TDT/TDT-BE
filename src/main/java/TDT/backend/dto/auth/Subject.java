package TDT.backend.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class Subject {

    private Long memberId;

    private String email;

    private String nickname;

    private String type;

    private Subject(Long memberId, String email, String nickname, String type) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.type = type;
    }
    public static Subject ac(Long memberId, String email, String nickname) {
        return new Subject(memberId, email, nickname, "ACCESS");
    }

    public static Subject re(Long memberId, String email, String nickname) {
        return new Subject(memberId, email, nickname, "REFRESH");
    }
}

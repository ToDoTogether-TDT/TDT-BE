package TDT.backend.common.resolver;
import TDT.backend.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.servlet.http.HttpSessionBindingListener;

@Getter
@ToString
@NoArgsConstructor
public class UserSession implements HttpSessionBindingListener {
    public static final String USER_SESSION_KEY = "loginUser";
    private Long id;
    private String email;
    private String name;
    private String picture;
    @Builder
    private UserSession(Long id, String email, String name, String picture) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
    public static UserSession from(Member member) {
        return UserSession.builder().id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .picture(member.getPicture())
                .build();
    }

}
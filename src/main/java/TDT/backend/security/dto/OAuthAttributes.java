//package TDT.backend.security.dto;
//
//import TDT.backend.entity.Member;
//import TDT.backend.entity.Role;
//import lombok.Builder;
//import lombok.Getter;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//
//import java.util.Map;
//
//@Getter
//public class OAuthAttributes {
//    private Map<String, Object> attributes;
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//    private String picture;
//
//    private String provider;
//
//    private String providerId;
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes,
//                           String nameAttributeKey, String name,
//                           String email, String picture,String provider) {
//        this.attributes = attributes;
//        this.nameAttributeKey= nameAttributeKey;
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//    }
//
//    public static OAuthAttributes of(String provider,
//                                     String userNameAttributeName,
//                                     Map<String, Object> attributes) {
//        return ofGoogle(provider ,userNameAttributeName, attributes);
//    }
//
//    private static OAuthAttributes ofGoogle(
//            String provider, String userNameAttributeName,
//                                            Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .provider(provider)
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
//                .picture((String) attributes.get("picture"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//
//    public Member toEntity() {
//        return Member.builder()
//                .provider(provider)
//                .providerId(providerId)
//                .name(name)
//                .email(email)
//                .role(Role.GUEST)
//                .build();
//    }
//
//}

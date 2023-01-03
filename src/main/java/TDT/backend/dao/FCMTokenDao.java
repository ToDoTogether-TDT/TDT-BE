package TDT.backend.dao;

import TDT.backend.dto.InsertMemberReq;
import TDT.backend.repository.member.MemberRepository;
import com.google.api.Authentication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class FCMTokenDao {


    private final HashMap<String, String> tokenMap;

    private final StringRedisTemplate tokenRedisTemplate;
    @Value("${fcm.certification}")
    private String googleApplicationCredentials;
    private final String AUTH_URL = "https://www.googleapis.com/auth/cloud-platform";
    private String getAccessToken() throws IOException {
        // 토큰 발급
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream
                        (new ClassPathResource(googleApplicationCredentials).getInputStream())
                .createScoped(List.of(AUTH_URL));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    public void saveToken(InsertMemberReq req) throws IOException {

        tokenMap.put(req.getEmail(), getAccessToken());
        tokenRedisTemplate.opsForValue().set(req.getEmail(), getAccessToken());
    }

    public String getToken(String email) {
        return tokenRedisTemplate.opsForValue().get(email);
    }

    public void deleteToken(InsertMemberReq req) {
        tokenRedisTemplate.delete(req.getEmail());
    }

    public boolean hasKey(String email) {
        return tokenRedisTemplate.hasKey(email);
    }
}

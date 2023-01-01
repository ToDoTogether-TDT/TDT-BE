package TDT.backend.dao;

import TDT.backend.dto.InsertMemberReq;
import TDT.backend.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FCMTokenDao {

    private final StringRedisTemplate tokenRedisTemplate;

    public void saveToken(InsertMemberReq req) {
        tokenRedisTemplate.opsForValue()
                .set(req.getEmail(), req.getToken());
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

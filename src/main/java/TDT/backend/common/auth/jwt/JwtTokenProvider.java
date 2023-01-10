package TDT.backend.common.auth.jwt;

import TDT.backend.dao.RedisDao;
import TDT.backend.dto.member.InsertMemberResponse;
import TDT.backend.dto.auth.Subject;
import TDT.backend.dto.auth.jwt.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final RedisDao redisDao;
    private final ObjectMapper objectMapper;

    @Value("${spring.jwt.key}")
    private String key;

    @Value("${spring.jwt.live.access}")
    private Long acToken;

    @Value("${spring.jwt.live.refresh}")
    private Long reToken;

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());

    }
    public TokenResponse createTokensByLogin(InsertMemberResponse response) throws JsonProcessingException {
        Subject atkSubject = Subject.ac(
                response.getMemberId(),
                response.getEmail(),
                response.getNickname());
        Subject rtkSubject = Subject.re(
                response.getMemberId(),
                response.getEmail(),
                response.getNickname());
        String atk = createToken(atkSubject, acToken);
        String rtk = createToken(rtkSubject, reToken);
        redisDao.setValues(response.getEmail(), rtk, Duration.ofMillis(reToken));
        log.info("refreshToken = {}", redisDao.getValues(response.getEmail()));
        return new TokenResponse(atk, rtk);
    }



    private String createToken(Subject subject, Long tokenLive) throws JsonProcessingException {
        String subjectStr = objectMapper.writeValueAsString(subject);
        Claims claims = Jwts.claims()
                .setSubject(subjectStr);
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + tokenLive))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }


    public Subject getSubject(String ac) throws JsonProcessingException {
        String subjectStr = Jwts.parser().setSigningKey(key).parseClaimsJws(ac).getBody().getSubject();
        return objectMapper.readValue(subjectStr, Subject.class);
    }

    public TokenResponse reissueAtk(InsertMemberResponse response, String rtk) throws JsonProcessingException {
        String rtkInRedis = redisDao.getValues(response.getEmail());
        if(!Objects.isNull(rtkInRedis) && rtkInRedis.equals(rtk)) {
            Subject atkSubject = Subject.ac(
                    response.getMemberId(),
                    response.getEmail(),
                    response.getNickname());
            String atk = createToken(atkSubject, acToken);
            log.info("atk : {}", atk);
            return new TokenResponse(atk, null);
        } else throw new JwtException("인증 정보가 만료되었습니다.");
    }
}

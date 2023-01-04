package TDT.backend.service;

import TDT.backend.dto.InsertMemberReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void loginMember(InsertMemberReq req) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        Map<String,String> map = new HashMap();
        map.put("name", req.getName());
        map.put("email", req.getEmail());
        map.put("nickname", req.getNickname());
        map.put("picture", req.getPicture());
        hashOperations.putAll(req.getEmail(), map);
    }
}

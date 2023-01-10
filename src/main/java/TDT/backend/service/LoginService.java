package TDT.backend.service;

import TDT.backend.dto.member.InsertMemberReq;
import TDT.backend.entity.Member;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.service.member.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private final MemberRepository memberRepository;

    /**
     * 안써도 됨
     * @param req
     */
    public void loginMember(InsertMemberReq req) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Map<String,String> map = new HashMap();
        map.put("name", req.getName());
        map.put("email", req.getEmail());
        map.put("nickname", req.getNickname());
        map.put("picture", req.getPicture());
        hashOperations.putAll(req.getEmail(), map);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new MemberDetails(member);
    }

}

package TDT.backend.service;

import TDT.backend.dao.FCMTokenDao;
import TDT.backend.dto.schedule.ScheduleRequestDto;
import TDT.backend.entity.TeamMember;
import TDT.backend.repository.teamMember.TeamMemberRepository;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FCMService {

    private final FCMTokenDao fcmTokenDao;
    private final TeamMemberRepository teamMemberRepository;


    public void send(Message message) {
        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void sendNotification(final ScheduleRequestDto dto) {
        List<TeamMember> allByTeamId = teamMemberRepository.findAllByTeamId(dto.getStudyId());

        allByTeamId.forEach(teamMember -> {
            if (!teamMember.getIsLeader()) {
                String email = teamMember.getMember().getEmail();
                System.out.println(email);
                String token = fcmTokenDao.getToken(email);

                System.out.println("b" + token);
                Message message = Message.builder()
                        .putData("title", "스터디 알림")
                        .putData("content", "스터디 일정 추가 알림")
                        .setToken(token)
                        .build();
                send(message);
            }
        });
    }
}



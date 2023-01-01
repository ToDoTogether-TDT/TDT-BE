package TDT.backend.service;

import TDT.backend.dao.FCMTokenDao;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Service
@RequiredArgsConstructor
public class FCMService {

    private final FCMTokenDao fcmTokenDao;

    public void sendNotification(String email) {
        if (!fcmTokenDao.hasKey(email)) return;

    String token = fcmTokenDao.getToken(email);
    Message message = Message.builder()
            .putData("title", "스터디 알림")
            .putData("content", "스터디 일정 추가 알림")
            .setToken(token)
            .build();
    {
        try {
            String response = FirebaseMessaging.getInstance().send(message);

        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

}

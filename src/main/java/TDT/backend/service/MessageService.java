package TDT.backend.service;


import TDT.backend.dao.FCMTokenDao;
import TDT.backend.dto.message.MessageRequestDto;
import TDT.backend.dto.message.MessageResponseDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.Message;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {


    private final FCMTokenDao fcmTokenDao;
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;

    public Long sendMessage(MessageRequestDto dto, Long receiverId, Long senderId) {

        Member sender = memberRepository.findById(senderId).orElseThrow(()
                -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));
        Member receiver = memberRepository.findById(receiverId).orElseThrow(()
                -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));
        Message message = Message.of(dto, sender, receiver);
        messageRepository.save(message);
        return message.getId();
    }

    public Page<MessageResponseDto> getMessages(Long id, Pageable pageable) {
        return messageRepository.findAllByIdAndPageable(id, pageable);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }


}

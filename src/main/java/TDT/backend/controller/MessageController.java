package TDT.backend.controller;

import TDT.backend.dto.message.MessageRequestDto;
import TDT.backend.dto.message.MessageResponseDto;
import TDT.backend.service.MessageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    public final MessageService service;


    //메시지보내기
    @ApiOperation(value = "메시지 보내기")
    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestParam("receiverId") Long receiverId,
                                         @RequestParam("senderId") Long senderId,
                                         @RequestBody MessageRequestDto dto) {
        return ResponseEntity.ok(service.sendMessage(dto, receiverId, senderId));
    }

    //메시지 보관함
    @ApiOperation(value = "메시지 보관함 확인")
    @GetMapping
    public ResponseEntity<Page<MessageResponseDto>> getMessages(@RequestParam Long id,
                                                                @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.getMessages(id, pageable));
    }

    //메시지 보관함에서 메시지삭제

    @ApiOperation(value = "메시지 보관함에서 메시지 삭제")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteMessages(@RequestParam Long id) {
        service.deleteMessage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //보낸 메시지 삭제

    /**
     * 알람 컨트롤러
     * 메시지, 팀 가입 요청, 댓글 알림이 있을시
     * 그 내용 전송
     */
}

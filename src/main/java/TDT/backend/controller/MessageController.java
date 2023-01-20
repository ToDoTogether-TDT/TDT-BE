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

    @ApiOperation(value = "메시지 보내기")
    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestParam("receiverId") Long receiverId,
                                         @RequestParam("senderId") Long senderId,
                                         @RequestBody MessageRequestDto dto) {
        return ResponseEntity.ok(service.sendMessage(dto, receiverId, senderId));
    }

    @ApiOperation(value = "메시지 보관함 확인")
    @GetMapping
    public ResponseEntity<Page<MessageResponseDto>> getMessages(@RequestParam Long id,
                                                                @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.getMessages(id, pageable));
    }

    @ApiOperation(value = "메시지 보관함에서 메시지 삭제")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteMessages(@RequestParam Long id) {
        service.deleteMessage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

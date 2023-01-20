package TDT.backend.controller;


import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.service.NoticeService;
import TDT.backend.common.auth.MemberDetails;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @ApiOperation(value = "알림창 수신", notes = "게시판, 스터디댓글 알림, 스터디참여 요청 알림, 스터디일정 알림")
    @GetMapping
    public ResponseEntity<List<NoticeResponseDto>> getNotice(@AuthenticationPrincipal MemberDetails member) {
        List<NoticeResponseDto> notice = noticeService.getNotice(member);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @ApiOperation(value = "알림 삭제", notes = "하나의 알림 삭제")
    @DeleteMapping("/{noticeId}")
    public ResponseEntity deleteNotice(@PathVariable Long noticeId,
                                       @AuthenticationPrincipal MemberDetails memberDetails) {
        noticeService.deleteNotice(noticeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

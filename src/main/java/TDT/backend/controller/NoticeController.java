package TDT.backend.controller;


import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.entity.Member;
import TDT.backend.service.NoticeService;
import TDT.backend.service.member.MemberDetails;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @ApiOperation(value = "알림창 수신",notes = "게시판, 스터디댓글 알림, 스터디참여 요청 알림, 스터디일정 알림")
    @GetMapping
    public ResponseEntity<NoticeResponseDto> getNotice(@AuthenticationPrincipal MemberDetails member) {
        noticeService.getNotice(member);
        return new ResponseEntity<>(null);
    }
}

package TDT.backend.controller;

import TDT.backend.dto.schedule.TodoCheckRequestDto;
import TDT.backend.dto.team.StudyJoinReqMemberDto;
import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.dto.team.StudyRequestDto;
import TDT.backend.dto.team.StudyResponseDto;
import TDT.backend.service.NoticeService;
import TDT.backend.service.ScheduleService;
import TDT.backend.common.auth.MemberDetails;
import TDT.backend.service.TeamService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class TeamController {

    private final TeamService teamService;
    private final ScheduleService scheduleService;
    private final NoticeService noticeService;


    @ApiOperation(value = "모든 스터디 조회", notes = "##추가 -> 모든 스터디 조회")
    @GetMapping
    public ResponseEntity<Page<StudyListResponseDto>> getAllKindOfStudy(@RequestParam(value = "page", defaultValue = "1") int page) {
        return ResponseEntity.ok(teamService.getAllKindOfStudy(page));
    }

    @ApiOperation(value = "특정 카테고리 스터디 조회", notes = "##추가 -> 특정 카테고리 스터디 조회")
    @GetMapping("/{category}")
    public ResponseEntity<Page<StudyListResponseDto>> getAllStudy(@PathVariable(value = "category") String category,
                                                                  @RequestParam(value = "page", defaultValue = "1") int page) {
        return ResponseEntity.ok(teamService.getAllStudy(category, page));
    }

    @ApiOperation(value = "스터디 추가", notes = "하나의 스터디 추가")
    @PostMapping
    public ResponseEntity<Long> addStudy(@RequestBody StudyRequestDto params,
                                         @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity.ok(teamService.addTeam(params, memberDetails.getMember()));
    }

    @ApiOperation(value = "스터디 상세정보 조회")
    @GetMapping("/{category}/{studyId}")
    public ResponseEntity<StudyResponseDto> getStudy(@PathVariable("category") String category,
                                                     @PathVariable("studyId") Long studyId) {
        return ResponseEntity.ok(teamService.getStudy(studyId));
    }

    @ApiOperation(value = "스터디 참여 요청")
    @PostMapping("/join/{studyId}")
    public ResponseEntity joinStudy(@PathVariable Long studyId,
                                    @AuthenticationPrincipal MemberDetails memberDetails) {
        teamService.joinTeam(studyId, memberDetails.getMember());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "스터디 참석 요청 확인", notes = "현재 멤버가 스터디장일때만 response 있음")
    @GetMapping("/{category}/{studyId}/notice")
    public ResponseEntity getRequestToAttendStudyMemberList(@PathVariable("category") String category,
                                                            @PathVariable("studyId") Long studyId,
                                                            @AuthenticationPrincipal MemberDetails memberDetails) {
        List<StudyJoinReqMemberDto> joinReqMembers = teamService.getJoinReqMembers(studyId, memberDetails.getMember());
        return new ResponseEntity<>(joinReqMembers, HttpStatus.OK);
    }

    @ApiOperation(value = "스터디 참여 수락", notes = "한명의 신청자 참여 수락")
    @PostMapping("/accept")
    public ResponseEntity acceptJoinStudy(@RequestParam Long studyId,
                                          @RequestParam Long memberId,
                                          @AuthenticationPrincipal MemberDetails memberDetails) {

        teamService.acceptJoinStudy(studyId, memberId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "스터디 삭제")
    @DeleteMapping("/{studyId}")
    public ResponseEntity<Boolean> deleteStudy(@PathVariable("studyId") Long studyId,
                                               @AuthenticationPrincipal MemberDetails memberDetails) {
        teamService.deleteStudy(studyId, memberDetails.getMember());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "Todo 시행 여부 확인")
    @PostMapping("/{category}/{id}")
    public ResponseEntity<Boolean> isDoneTodo(@PathVariable("category") String category,
                                              @PathVariable("id") Long studyId,
                                              @RequestBody TodoCheckRequestDto dto) {
        return ResponseEntity.ok(scheduleService.isDoneTodo(dto));
    }

}

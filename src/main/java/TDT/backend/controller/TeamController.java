package TDT.backend.controller;

import TDT.backend.common.utils.CategoryClassifier;
import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.dto.schedule.TodoCheckRequestDto;
import TDT.backend.dto.team.StudyJoinReqDto;
import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.dto.team.StudyRequestDto;
import TDT.backend.dto.team.StudyResponseDto;
import TDT.backend.entity.NoticeCategory;
import TDT.backend.service.NoticeService;
import TDT.backend.service.ScheduleService;
import TDT.backend.service.member.MemberDetails;
import TDT.backend.service.team.TeamService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

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
    public ResponseEntity<Page<StudyListResponseDto>> getAllKindOfStudy(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(teamService.getAllKindOfStudy(pageable));
    }

    @ApiOperation(value = "특정 카테고리 스터디 조회", notes = "##추가 -> 특정 카테고리 스터디 조회")
    @GetMapping("/{category}")
    public ResponseEntity<Page<StudyListResponseDto>> getAllStudy(@PathVariable(value = "category", required = false) String category,
                                                                     @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(teamService.getAllStudy(category, pageable));
    }

    @ApiOperation(value = "스터디 추가",notes = "하나의 스터디 추가")
    @PostMapping
    public ResponseEntity<Long> addStudy(@RequestBody StudyRequestDto params,
                                         @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity.ok(teamService.addTeam(params, memberDetails.getMember()));
    }

    /**
     * TodoList
     * 스터디 참여요청받으면 요청 확인해서 스터디 참여시키기
     */




    @ApiOperation(value = "스터디 상세정보 조회")
    @GetMapping("/{category}/{id}")
    public ResponseEntity<StudyResponseDto> getStudy(@PathVariable("category") String category,
                                                     @PathVariable("id") Long studyId
    ) {
        /**Todo
         * memberId를 통해 내 스터디인지 확인
         */
        return ResponseEntity.ok(teamService.getStudy(studyId));
    }

    @ApiOperation(value = "스터디 참여 요청")
    @PostMapping("/{category}/join/{studyId}")
    public ResponseEntity joinStudy(@PathVariable("category") String category,
                                    @PathVariable Long studyId,
                                    @AuthenticationPrincipal MemberDetails memberDetails) {
        teamService.joinTeam(studyId, memberDetails.getMember());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "스터디 참석 요청 확인", notes = "현재 멤버가 스터디장일때만 response 있음")
    @GetMapping("/{category}/{id}/notice")
    public ResponseEntity<List<NoticeResponseDto>> getRequestToAttendStudyMemberList(@PathVariable("category") String category,
                                                                                     @PathVariable("id") Long studyId,
                                                                                     @AuthenticationPrincipal MemberDetails memberDetails) {
        /**
         *
         * 멤버가 스터디 장일때만 리턴값이 있음
         */
        NoticeCategory noticeCategory = CategoryClassifier.classifier(category);
        System.out.println(noticeCategory);
        return ResponseEntity.ok(noticeService.getStudyNotice(studyId, noticeCategory, memberDetails.getMember()));
    }

    @ApiOperation(value = "스터디 삭제")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteStudy(@RequestParam Long studyId,
                                               @RequestParam Long id) {
        return ResponseEntity.ok(teamService.deleteStudy(studyId, id));
    }


    /**
     * @return Todo를 했으면 True 안했으면 False
     */
    @ApiOperation(value = "Todo 시행 여부 확인")
    @PostMapping("/{category}/{id}")
    public ResponseEntity<Boolean> isDoneTodo(@PathVariable("category") String category,
                                        @PathVariable("id") Long studyId,
                                        @RequestBody TodoCheckRequestDto dto) {
        return ResponseEntity.ok(scheduleService.isDoneTodo(dto));
    }

}

package TDT.backend.controller;

import TDT.backend.dto.schedule.ScheduleRequestDto;
import TDT.backend.dto.schedule.TodoCheckRequestDto;
import TDT.backend.dto.team.StudyJoinReqDto;
import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.dto.team.StudyRequestDto;
import TDT.backend.dto.team.StudyResponseDto;
import TDT.backend.entity.Category;
import TDT.backend.service.ScheduleService;
import TDT.backend.service.team.TeamService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class TeamController {

    private final TeamService teamService;
    private final ScheduleService scheduleService;

    @ApiOperation(value = "스터디 조회",notes = "모든 스터디 조회")
    @GetMapping("/{category}")
    public ResponseEntity<Page<StudyListResponseDto>> getAllStudy(@PathVariable(value = "category", required = false) String category,
                                                                  @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(teamService.getAllStudy(category, pageable));
    }

    @ApiOperation(value = "스터디 추가",notes = "하나의 스터디 추가")
    @PostMapping("/addition")
    public ResponseEntity<Long> addStudy(@RequestBody StudyRequestDto params) {
        return ResponseEntity.ok(teamService.addTeam(params));
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
        return ResponseEntity.ok(teamService.getStudy(category, studyId));
    }

    @ApiOperation(value = "스터디 참여 수락")
    @PostMapping("/{category}/{id}/join")
    public ResponseEntity joinStudy(@PathVariable("category") String category,
                                    @PathVariable("id") Long studyId,
                                    @RequestParam("memberId") Long memberId) {
        teamService.joinTeam(studyId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    @PostMapping("/{studyId}")
//    public ResponseEntity<Boolean> updateStudy(@PathVariable("sutdyId") Long studyId,
//                                               @RequestParam Long id) {
//        return ResponseEntity.ok(teamService.updateStudy(studyId, id));
//    }
    @ApiOperation(value = "스터디 삭제")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteStudy(@RequestParam Long studyId,
                                               @RequestParam Long id) {
        return ResponseEntity.ok(teamService.deleteStudy(studyId, id));
    }

    @ApiOperation(value = "Todo 시행 여부 확인")
    @PostMapping("/{category}/{id}")
    public ResponseEntity<?> isDoneTodo(@PathVariable("category") String category,
                                        @PathVariable("id") Long studyId,
                                        @RequestBody TodoCheckRequestDto dto) {
        scheduleService.isDoneTodo(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package TDT.backend.controller;

import TDT.backend.dto.schedule.ScheduleAddReqDto;
import TDT.backend.dto.schedule.ScheduleEditReqDto;
import TDT.backend.service.ScheduleService;
import TDT.backend.service.member.MemberDetails;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study/todo")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiOperation(value = "Todo 추가")
    @PostMapping("/{studyId}")
    public ResponseEntity<?> addSchedule(@PathVariable Long studyId,
                                         @RequestBody ScheduleAddReqDto dto,
                                         @AuthenticationPrincipal MemberDetails memberDetails) {
        scheduleService.addSchedule(studyId, dto, memberDetails.getMember());
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @ApiOperation(value = "사용 안함")
//    @GetMapping
//    public ResponseEntity getSchedulesForMemberOrTeam(@RequestParam(name = "memberId", required = false) Long memberId,
//                                                      @RequestParam(name = "studyId", required = false) Long studyId) {
//
//        if (memberId != null) {
//            List<ScheduleResForMember> schedules = scheduleService.getSchedulesForMember(memberId);
//            return new ResponseEntity<>(schedules, HttpStatus.OK);
//        } else {
//            List<ScheduleResForTeam> schedules = scheduleService.getSchedulesForTeam(studyId);
//            return new ResponseEntity<>(schedules, HttpStatus.OK);
//        }
//    }

    @ApiOperation(value = "Todo 수정")
    @PutMapping("/{scheduleId}")
    public ResponseEntity editSchedule(@PathVariable Long scheduleId,
                                       @RequestBody ScheduleEditReqDto requestDto,
                                       @AuthenticationPrincipal MemberDetails memberDetails) {

        scheduleService.editSchedule(scheduleId, requestDto, memberDetails.getMember());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Todo 삭제")
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity deleteSchedule(@PathVariable Long scheduleId,
                                         @RequestParam(name = "studyId") Long studyId,
                                         @AuthenticationPrincipal MemberDetails memberDetails) {

        scheduleService.deleteSchedule(scheduleId, memberDetails.getMember(), studyId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

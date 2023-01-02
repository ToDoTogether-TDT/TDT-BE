package TDT.backend.controller;

import TDT.backend.dto.schedule.ScheduleRequestDto;
import TDT.backend.dto.schedule.ScheduleResForMember;
import TDT.backend.dto.schedule.ScheduleResForTeam;
import TDT.backend.service.FCMService;
import TDT.backend.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/study/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final FCMService fcmService;

    @PostMapping
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequestDto dto) {
        scheduleService.addSchedule(dto);
        fcmService.sendNotification(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getSchedulesForMemberOrTeam(@RequestParam(name = "memberId", required = false) Long memberId,
                                                      @RequestParam(name = "studyId", required = false) Long studyId) {

        if(memberId != null) {
            List<ScheduleResForMember> schedules = scheduleService.getSchedulesForMember(memberId);
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        }
        else {
            List<ScheduleResForTeam> schedules = scheduleService.getSchedulesForTeam(studyId);
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        }
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity editSchedule(@PathVariable Long scheduleId,
                                       @RequestBody ScheduleRequestDto requestDto) {

        scheduleService.editSchedule(scheduleId, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity deleteSchedule(@PathVariable Long scheduleId,
                                         @RequestParam(name = "memberId") Long memberId,
                                         @RequestParam(name = "studyId") Long studyId) {

        scheduleService.deleteSchedule(scheduleId, memberId, studyId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

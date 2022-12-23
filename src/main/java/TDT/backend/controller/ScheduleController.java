package TDT.backend.controller;

import TDT.backend.dto.schedule.ScheduleRequestDto;
import TDT.backend.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private ScheduleService scheduleService;
    @PostMapping
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequestDto dto) {
        System.out.println(dto.getContents());
        return scheduleService.addSchedule(dto);
    }
}

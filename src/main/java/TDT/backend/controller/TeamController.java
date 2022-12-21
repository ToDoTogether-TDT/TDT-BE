package TDT.backend.controller;

import TDT.backend.dto.team.StudyRequestDto;
import TDT.backend.dto.team.StudyListRes;
import TDT.backend.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/category")
    public ResponseEntity<Page<StudyListRes>> getStudy(@RequestParam String category,
                                                       @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        Page<StudyListRes> study = teamService.getStudy(category, pageable);
        return ResponseEntity.ok(teamService.getStudy(category, pageable));
    }

//    @PostMapping("/posting-study")
    public ResponseEntity<Long> addStudy(@RequestBody StudyRequestDto params) {
        return ResponseEntity.ok(teamService.addTeam(params));
    }


}

package TDT.backend.controller;

import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.dto.team.StudyRequestDto;
import TDT.backend.dto.team.StudyResponseDto;
import TDT.backend.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/")
    public ResponseEntity<Page<StudyListResponseDto>> getAllStudy(@RequestParam String category,
                                                               @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        Page<StudyListResponseDto> study = teamService.getStudy(category, pageable);
        return ResponseEntity.ok(teamService.getAllStudy(category, pageable));
    }
    @PostMapping("/posting-study")
    public ResponseEntity<Long> addStudy(@RequestBody StudyRequestDto params) {
        return ResponseEntity.ok(teamService.addTeam(params));
    }

    @GetMapping("/{category}")
    public ResponseEntity<StudyResponseDto> getStudy(@PathVariable("category") String category,
                                                     @RequestParam Integer studyId) {
        System.out.println(studyId);
        return ResponseEntity.ok(teamService.getStudy(category, studyId));
    }
    @DeleteMapping("/{category}")
    public ResponseEntity<Boolean> deleteStudy(@PathVariable("category") String category,
                                         @RequestParam Long studyId,
                                         @RequestParam Long id) {
        return ResponseEntity.ok(teamService.deleteStudy(studyId, id));
    }
}

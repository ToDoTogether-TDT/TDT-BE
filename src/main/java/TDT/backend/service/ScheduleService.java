package TDT.backend.service;

import TDT.backend.dto.schedule.ScheduleRequestDto;
import TDT.backend.entity.Team;
import TDT.backend.entity.TeamSchedule;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.schedule.ScheduleRepository;
import TDT.backend.repository.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public ResponseEntity<?> addSchedule(ScheduleRequestDto dto) {
        Team team = teamRepository.findById(dto.getStudyId()).orElseThrow(
                () -> new BusinessException(ExceptionCode.TEAM_NOT_EXISTS)
        );
        TeamSchedule teamSchedule = TeamSchedule.of(team, dto);
        scheduleRepository.save(teamSchedule);
        return null;
    }
}

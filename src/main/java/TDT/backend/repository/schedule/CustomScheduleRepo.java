package TDT.backend.repository.schedule;

import TDT.backend.dto.schedule.ScheduleDto;

import java.util.List;

public interface CustomScheduleRepo {
    List<ScheduleDto> findScheduleByStudyId(Long studyId);
}

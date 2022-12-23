package TDT.backend.repository.schedule;

import TDT.backend.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

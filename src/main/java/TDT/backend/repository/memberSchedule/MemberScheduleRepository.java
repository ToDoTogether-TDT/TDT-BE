package TDT.backend.repository.memberSchedule;

import TDT.backend.entity.MemberSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberScheduleRepository extends JpaRepository<MemberSchedule, Long>, CustomMemberScheduleRepo {


}

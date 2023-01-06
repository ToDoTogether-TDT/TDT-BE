package TDT.backend.repository.notice;

import TDT.backend.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long >, CustomNoticeRepository {
}

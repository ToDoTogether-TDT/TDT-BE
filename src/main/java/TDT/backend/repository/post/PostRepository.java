package TDT.backend.repository.post;

import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{
}

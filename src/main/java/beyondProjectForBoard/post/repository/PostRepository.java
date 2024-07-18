package beyondProjectForBoard.post.repository;

import beyondProjectForBoard.post.domain.Post;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface PostRepository extends JpaRepository<Post,Long> {
}

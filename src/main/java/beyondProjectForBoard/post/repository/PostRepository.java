package beyondProjectForBoard.post.repository;

import beyondProjectForBoard.post.domain.Post;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Registered
public interface PostRepository extends JpaRepository<Post,Long> {

//    findAllFetch() : jpql을 적용하여 네이밍룰을 통한 방식이 아닌 메서드 생성
//    select p.*, a.* from post p left join author a on p.author_id=a.id;
//         >>> ⭐ p.*, a.* 가 함께 보일 수 있게 되는게 [fetch] 기법!! ⭐
    @Query("select p from Post p left join fetch p.author") // 해당 쿼리는 jpql의 쿼리언어
    List<Post> findAllFetch(); // N+1 오류 발생 X, select문도 1개만 발송

//    select p.* from post p left join author a on p.author_id=a.id
//    아래의 join문은 author객체를 통한 조건문으로 post를 filtering 할 때 사용
    @Query("select p from Post p left join p.author") // 해당 쿼리는 jpql의 쿼리언어
    List<Post> findAllLeftJoin(); // N+1 오류 발생

}

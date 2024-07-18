package beyondProjectForBoard.author.repository;

import beyondProjectForBoard.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
//    findBy컬럼명의 규칙으로 자동으로 where조건문을 사용한 쿼리 생성
//    그 외 : findBy 컬럼1 And 컬럼2 -> A and B  /  findBy 컬럼1 OR 컬럼2 -> A or B
//            findByNameAndEmail / findByNameOrEmail
//            findByAgeBetween(int start, int end)
//            findByAgeLessThan(int age) // ~보다 작은   |   findByAgeGreaterThan(int age) // ~보다 큰
//            findByAgeIsNull, findByAgeIsNotNull;
//            findAllOrderByEmail();
    Optional<Author> findByEmail(String email);

}

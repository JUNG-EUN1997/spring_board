package beyondProjectForBoard.author;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.author.domain.Role;
import beyondProjectForBoard.author.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 롤백처리를 위해 @Transactional 어노테이션 처리 : 실제 DB에 넣는용도가 아니기 때문
public class AuthorRepositoryTest { // 코드레벨에서 클린함이 중요하지 않음!
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void authorSaveTest(){
//        테스트 원리 : save > 재조회 > 저장할 때 만든 객체와 비교

//        given - when - then 패턴
//        준비(prepare, given) 단계
        Author author = Author.builder()
                .name("hong2")
                .email("hong2@naver.com")
                .password("12345612346123")
                .role(Role.ADMIN)
                .build();

//        실행(excute, when) 단계
        authorRepository.save(author);
        Author savedAuthor = authorRepository.findByEmail("hong1@naver.com").orElse(null);

//        검증(then) 단계
        Assertions.assertEquals(author.getEmail(),savedAuthor.getEmail());
    }

}

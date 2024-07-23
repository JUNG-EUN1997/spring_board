package beyondProjectForBoard.author;


import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.author.domain.Role;
import beyondProjectForBoard.author.dto.AuthorDetailResDto;
import beyondProjectForBoard.author.dto.AuthorSaveReqDto;
import beyondProjectForBoard.author.repository.AuthorRepository;
import beyondProjectForBoard.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

//Mock은 가짜객체를 만든다
@SpringBootTest
@Transactional
public class AuthorServiceMockTest {

    @Autowired
    private AuthorService authorService;

//    @Autowired
//    private AuthorRepository authorRepository;

//    ⭐가짜객체⭐를 만드는 작업인 ⭐목킹⭐
    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void findAuthorDetailService(){
        AuthorSaveReqDto saveReqDto = new AuthorSaveReqDto("test1","test1@google.com","12312312", Role.ADMIN);
        Author author = authorService.authorCreate(saveReqDto);
//        Author myAuthor = Author.builder().id(1L).name("test").email("test@aa.com").build();
//        이런 방식으로 가짜 객체 생성 후 Mockito의 호출자리에 선언 가능

        AuthorDetailResDto authorDetailResDto = authorService.authorDetail(author.getId());
//        Author savedAuthor = authorRepository.findById(author.getId())
//                .orElseThrow(() -> new EntityNotFoundException("not found"));
        Mockito.when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
//        ⭐ 만약, authorRepository.findById(author.getId()) 하면, 가짜객체를 return해 주겠다.

        Author savedAuthor = authorRepository.findById(author.getId()).orElseThrow(() -> new EntityNotFoundException("not found"));
        Assertions.assertEquals(authorDetailResDto.getEmail(), savedAuthor.getEmail());

    }
//    저장 및 detail 조회

//    update 검증

//    findAll 검증

}

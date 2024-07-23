package beyondProjectForBoard.author;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.author.domain.Role;
import beyondProjectForBoard.author.dto.AuthorSaveReqDto;
import beyondProjectForBoard.author.dto.AuthorUpdateReqDto;
import beyondProjectForBoard.author.repository.AuthorRepository;
import beyondProjectForBoard.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
@Transactional
//@Rollback
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;
//    저장 및 detail 조회
    @Test
    void saveAndFind(){
        AuthorSaveReqDto saveReqDto = new AuthorSaveReqDto("happymoo","moo@mail.com","123123", Role.ADMIN);
        Author author = authorService.authorCreate(saveReqDto);
        Author authorDetail = authorRepository.findById(author.getId())
                // Service를 테스트하려고 하는데, Repository로 인해 집중할 수 없다. > 추후에 Mocking 으로 수정
                .orElseThrow(() -> new EntityNotFoundException("없는 유저입니다!"));
        Assertions.assertEquals(authorDetail.getEmail(),saveReqDto.getEmail());
    }

//    update 검증
    @Test
    void updateAndFind(){
//    객체 create
        AuthorSaveReqDto saveReqDto = new AuthorSaveReqDto("happymoo","moo@mail.com","123123", Role.ADMIN);
        Author author = authorService.authorCreate(saveReqDto);
//    수정 작업(name, password)
        String updateName = "happymoo2";
        String updatePw = "777777";
        AuthorUpdateReqDto updateReqDto = new AuthorUpdateReqDto(updateName,updatePw);
        authorService.authorUpdate(author.getId(),updateReqDto);

//    수정 후 재조회 : name과 password가 맞는지 각각 검증
        Author authorGet = authorRepository.findById(author.getId())
                .orElseThrow(() -> new EntityNotFoundException("없는 유저입니다!"));
        Assertions.assertEquals(updateName,authorGet.getName());
        Assertions.assertEquals(updatePw,authorGet.getPassword());
    }



//    findAll 검증
    @Test
    public void findAllTest(){
        int sizeBefore = authorService.authorList().size();
//        3개 Author 객체 저장
        AuthorSaveReqDto saveReqDto1 = new AuthorSaveReqDto("happymoo1","moo1@mail.com","123123", Role.ADMIN);
        authorService.authorCreate(saveReqDto1);
        AuthorSaveReqDto saveReqDto2 = new AuthorSaveReqDto("happymoo2","moo2@mail.com","123123", Role.ADMIN);
        authorService.authorCreate(saveReqDto2);
        AuthorSaveReqDto saveReqDto3 = new AuthorSaveReqDto("happymoo2","moo3@mail.com","123123", Role.ADMIN);
        authorService.authorCreate(saveReqDto3);
//        authorList 조회 후 저장한 개수와 저장된 개수 비교
        int sizeAfter = authorService.authorList().size()-3;

        Assertions.assertEquals(sizeBefore,sizeAfter);


    }

}

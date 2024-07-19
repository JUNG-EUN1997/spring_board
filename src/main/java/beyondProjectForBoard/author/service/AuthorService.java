package beyondProjectForBoard.author.service;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.author.dto.AuthorDetailResDto;
import beyondProjectForBoard.author.dto.AuthorListResDto;
import beyondProjectForBoard.author.dto.AuthorSaveReqDto;
import beyondProjectForBoard.author.dto.AuthorUpdateReqDto;
import beyondProjectForBoard.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//조회작업 시 readOnly 설정하면 성능 향상
//다만, 저장 작업 시, 별도로 해당 메소드 위에 @Transactional 선언 필요
//@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public String authorCreate(AuthorSaveReqDto dto){
        if(authorRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 email 입니다.");
        }

        Author author = dto.toEntity();
        System.out.println(author);
        authorRepository.save(author);
        return "ok";
    }

    public List<AuthorListResDto> authorList(){
        List<AuthorListResDto> authorListResDtos = new ArrayList<>();
        List<Author> listAuthor = authorRepository.findAll();
        for (Author author : listAuthor){
            authorListResDtos.add(author.listFromEntity());
        }
        return authorListResDtos;
    }

    public AuthorDetailResDto authorDetail(Long id){
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        Author author = optionalAuthor.orElseThrow(() -> new EntityNotFoundException("member is not found"));

        AuthorDetailResDto authorDetailResDto = new AuthorDetailResDto();
        return authorDetailResDto.detailFromEntity(author); // AuthorDetailResDto 객체에 fromEntity 제작하는 법
    }

    public Author authorFindByEmail(String email){
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("해당 email의 사용자는 없습니다."));
        return author;
    }

    @Transactional
    public String authorDelete(Long id){
        authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 사용자는 없습니다."));
        try{
            authorRepository.deleteById(id);
            return "ok";
        }catch ( EntityNotFoundException e ){
            return e.getMessage();
        }
    }

    public String authorUpdate(Long id, AuthorUpdateReqDto dto){
//         업데이트는 원본을 찾아야한다.
//        그리고 원본을 수정해야한다!
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("해당 사용자는 없습니다.")
        );

        author.updateAuthor(dto); // Author 객체 메모리 주소로 가서 직접 원본으로 가서 수정
        authorRepository.save(author);
        return "ok";
    }

}

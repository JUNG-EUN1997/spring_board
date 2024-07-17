package beyondProjectForBoard.author.service;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.author.dto.AuthorDetailResDto;
import beyondProjectForBoard.author.dto.AuthorListResDto;
import beyondProjectForBoard.author.dto.AuthorReqDto;
import beyondProjectForBoard.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public String authorCreate(AuthorReqDto authorReqDto){
        Author author = authorReqDto.toEntity();
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
        Optional<Author> optAuthor = authorRepository.findById(id);
        Author author = optAuthor.orElseThrow(() -> new EntityNotFoundException("없는 유저 입니다."));

        AuthorDetailResDto authorDetailResDto = author.detailFromEntity();
        return authorDetailResDto;
    }
}

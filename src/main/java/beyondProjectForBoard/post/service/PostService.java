package beyondProjectForBoard.post.service;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.author.service.AuthorService;
import beyondProjectForBoard.post.domain.Post;
import beyondProjectForBoard.post.dto.PostDetailResDto;
import beyondProjectForBoard.post.dto.PostListResDto;
import beyondProjectForBoard.post.dto.PostSaveReqDto;
import beyondProjectForBoard.post.dto.PostUpdateReqDto;
import beyondProjectForBoard.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final AuthorService authorService; // 여러개의 싱글톤 객체를 생성하다보면 순환참조가 걸릴 수 있음.

//    sevice 또는 repository를 autowired할지는 상황에 따라 다르나,
//    service레벨에서 코드가 고도화 되어있고 코드의 중복이 심할경우, service 레이어를 autowired
//    그러나, 순환참조가 발생할 경우 repository를 autowired

    @Autowired
    public PostService(PostRepository postRepository, AuthorService authorService){
        this.postRepository = postRepository;
        this.authorService = authorService;
    }

//    authorservice에서 author객체를 찾아 post의 toEntity에 넘기고
//    jpa가 author객체에서 author_id를 찾아 db에는 author_id가 들어감
    public Post postCreate(PostSaveReqDto dto){
        Author author = authorService.authorFindByEmail(dto.getEmail());
        Post post = dto.toEntity(author);
        postRepository.save(post);
        return post;
    }

    public List<PostListResDto> postList(){
        List<Post> posts = postRepository.findAll();
        List<PostListResDto> postListResDtos = new ArrayList<>();
        for (Post p : posts){
            postListResDtos.add(p.listFromEntity());
        }
        return postListResDtos;
    }

    public PostDetailResDto postDetail(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 게시글 입니다."));
        PostDetailResDto postDetailResDto = post.detailFromEntity();
        return postDetailResDto;
    }

    public String postDelete(Long id){
        postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 게시글 입니다."));
        try{
            postRepository.deleteById(id);
            return "ok";
        }catch ( EntityNotFoundException e ){
            return e.getMessage();
        }
    }

    @Transactional
    public String postUpdate(Long id, PostUpdateReqDto dto){
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 게시글 입니다."));
        post.updatePost(dto);

        postRepository.save(post);
        return "ok";
    }


}

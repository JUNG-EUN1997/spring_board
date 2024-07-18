package beyondProjectForBoard.post.controller;

import beyondProjectForBoard.post.dto.PostDetailResDto;
import beyondProjectForBoard.post.dto.PostListResDto;
import beyondProjectForBoard.post.dto.PostSaveReqDto;
import beyondProjectForBoard.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RestController // RestBody 전체
@RequestMapping("/post")
public class PostRestController {
    private final PostService postService;

    @Autowired
    public PostRestController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/create")
    public String postCreate(@RequestBody PostSaveReqDto postSaveReqDto){
        postService.postCreate(postSaveReqDto);
        return "ok";
    }

    @GetMapping("/list")
    public List<PostListResDto> postList(){
        return postService.postList();
    }

    @GetMapping("/detail/{id}")
    public PostDetailResDto postList(@PathVariable(value = "id") Long id){
        return postService.postDetail(id);
    }

}

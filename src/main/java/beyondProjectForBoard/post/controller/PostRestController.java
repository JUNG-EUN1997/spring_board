package beyondProjectForBoard.post.controller;

import beyondProjectForBoard.post.dto.PostListResDto;
import beyondProjectForBoard.post.dto.PostSaveReqDto;
import beyondProjectForBoard.post.dto.PostUpdateReqDto;
import beyondProjectForBoard.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController // RestBody 전체
@RequestMapping("/post")
@Slf4j
public class PostRestController {
    private final PostService postService;

    @Autowired
    public PostRestController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/create")
    public String postCreateView(){
        return "post/post_register";
    }


    @PostMapping("/create")
    public String postCreate(PostSaveReqDto postSaveReqDto){
        postService.postCreate(postSaveReqDto);
        return "redirect:/post/list";
    }

    @GetMapping("/list")
    public String postList(Model model,
                           @PageableDefault(size=10, sort = "createdTime", direction = Sort.Direction.DESC )
                           Pageable pageable){
        model.addAttribute("postList", postService.postList(pageable));
        return "/post/post_list";
    }

    @GetMapping("/list/page")
    @ResponseBody
//    Pageable 요청 방법 : localhost:8080/post/list/page?size=10&page=0
//    public Page<List<PostListResDto>> postListPage(){
    public Page<PostListResDto> postListPage(
            @PageableDefault(size=10, sort = "createdTime", direction = Sort.Direction.DESC )
            Pageable pageable){
//        page 처리를 하려면, Page 객체로 감싸주면 된다.
        return postService.postListPage(pageable);
    }


    @GetMapping("/detail/{id}")
    public String postDetail(@PathVariable(value = "id") Long id, Model model){
//        log.info("method명 : postDetail");
//        log.info("get요청의 parameter : "+id);

        model.addAttribute("post",postService.postDetail(id));
        return "/post/post_detail";
    }



    @GetMapping("/delete/{id}")
    public String postDelete(@PathVariable(value = "id") Long id){
        postService.postDelete(id);
        return "redirect:/post/list";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, @ModelAttribute PostUpdateReqDto dto){
        postService.postUpdate(id, dto);
        return "redirect:/post/detail/"+id;
    }

}

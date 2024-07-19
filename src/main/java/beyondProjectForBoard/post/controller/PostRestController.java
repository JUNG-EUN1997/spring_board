package beyondProjectForBoard.post.controller;

import beyondProjectForBoard.post.dto.PostSaveReqDto;
import beyondProjectForBoard.post.dto.PostUpdateReqDto;
import beyondProjectForBoard.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Service
//@RestController // RestBody 전체
@RequestMapping("/post")
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
    public String postList(Model model){
        model.addAttribute("postList", postService.postList());
        return "/post/post_list";
    }

    @GetMapping("/detail/{id}")
    public String postDetail(@PathVariable(value = "id") Long id, Model model){
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

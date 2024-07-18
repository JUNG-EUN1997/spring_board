package beyondProjectForBoard.author.controller;


import beyondProjectForBoard.author.dto.AuthorDetailResDto;
import beyondProjectForBoard.author.dto.AuthorListResDto;
import beyondProjectForBoard.author.dto.AuthorSaveReqDto;
import beyondProjectForBoard.author.repository.AuthorRepository;
import beyondProjectForBoard.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorRestController {
    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/register")
    public String autorCreateView(){
        return "/author/author_register";
    }

    @PostMapping("/create")
    public String authorCreate(@ModelAttribute AuthorSaveReqDto dto, Model model){
        try {
            authorService.authorCreate(dto);
            return "redirect:/author/list"; // 화면 return이 아닌 url 재호출
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return "redirect:/";
        }

    }

    @GetMapping("/list")
    public String authorList(Model model){
        model.addAttribute("authorList",authorService.authorList());
        return "/author/author_list";
    }

    @GetMapping("/detail/{id}")
//    public AuthorDetailResDto authorDetail(@PathVariable(value="id") Long id){
    public String authorDetail(@PathVariable Long id,Model model){
        model.addAttribute("author",authorService.authorDetail(id));
        return "/author/author_detail";
    }


}

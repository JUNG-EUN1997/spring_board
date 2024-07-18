package beyondProjectForBoard.author.controller;


import beyondProjectForBoard.author.dto.AuthorDetailResDto;
import beyondProjectForBoard.author.dto.AuthorListResDto;
import beyondProjectForBoard.author.dto.AuthorSaveReqDto;
import beyondProjectForBoard.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/author")
public class AuthorRestController {
    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public String authorCreate(@RequestBody AuthorSaveReqDto dto){
        authorService.authorCreate(dto);
        return "ok";
    }

    @GetMapping("/list")
    public List<AuthorListResDto> authorList(){
        return authorService.authorList();
    }

    @GetMapping("/detail/{id}")
//    public AuthorDetailResDto authorDetail(@PathVariable(value="id") Long id){
    public AuthorDetailResDto authorDetail(@PathVariable Long id){
        return authorService.authorDetail(id);
    }


}

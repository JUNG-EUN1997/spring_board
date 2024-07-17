package beyondProjectForBoard.author.controller;


import beyondProjectForBoard.author.dto.AuthorDetailResDto;
import beyondProjectForBoard.author.dto.AuthorListResDto;
import beyondProjectForBoard.author.dto.AuthorReqDto;
import beyondProjectForBoard.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public String AuthorCreate(@RequestBody AuthorReqDto authorReqDto){
        authorService.authorCreate(authorReqDto);
        return "ok";
    }

    @GetMapping("/list")
    public List<AuthorListResDto> AuthorList(){
        return authorService.authorList();
    }

    @GetMapping("/detail/{id}")
    public AuthorDetailResDto AuthorDetail(@PathVariable(value="id") Long id){
        return authorService.authorDetail(id);
    }


}

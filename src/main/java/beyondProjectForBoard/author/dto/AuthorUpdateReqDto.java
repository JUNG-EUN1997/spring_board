package beyondProjectForBoard.author.dto;

import beyondProjectForBoard.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorUpdateReqDto {
    private String name;
    private String password;

    public Author toEntity(){
        Author author = Author.builder()
                .password(this.password)
                .name(this.name)
                .build();
        return author;
    }
}

package beyondProjectForBoard.author.dto;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorReqDto {
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Author toEntity(){
        return new Author(this.name,this.email,this.password,this.role);
    }
}

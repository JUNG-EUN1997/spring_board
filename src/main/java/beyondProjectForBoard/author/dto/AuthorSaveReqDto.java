package beyondProjectForBoard.author.dto;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Data // Save에서 Data를 쓰지 않으면 form에서 저장이 되지 않는다!
// Entity 만 Setter 조심하면 된다... Entity가 뭐징...
public class AuthorSaveReqDto {
    private String name;
    private String email;
    private String password;
    private Role role; // 사용자가 String으로 요청해도 Role클래스로 자동 형변환

    public Author toEntity(){
        Author author = Author.builder()
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .role(this.role)
                .build();
        return author;
    }
}

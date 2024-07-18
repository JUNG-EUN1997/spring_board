package beyondProjectForBoard.author.dto;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.author.domain.Role;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDetailResDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private int postCounts;
    private LocalDateTime created_time;

    public AuthorDetailResDto detailFromEntity(Author author) {
        return AuthorDetailResDto.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .password(author.getPassword())
                .role(author.getRole())
                .postCounts(author.getPosts().size()) //이걸 쓸 수 있는 건 Author에 oneToMany가 걸려있어서
                .created_time(author.getCreated_time())
                .build();
    }
}

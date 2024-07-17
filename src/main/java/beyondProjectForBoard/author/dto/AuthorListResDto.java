package beyondProjectForBoard.author.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorListResDto {
    private Long id;
    private String name;
    private String email;
}

package beyondProjectForBoard.author.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorListResDto {
    private Long id;
    private String name;
    private String email;
}

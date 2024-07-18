package beyondProjectForBoard.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailResDto {
    private Long id;
    private String title;
    private String contents;
    private String author_email;
    private LocalDateTime created_time;
    private LocalDateTime updated_time;
}

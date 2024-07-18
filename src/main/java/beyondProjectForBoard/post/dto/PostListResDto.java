package beyondProjectForBoard.post.dto;

import beyondProjectForBoard.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResDto {
    private Long id;
    private String title;

//    Author 객체 그 자체를 return 시, Author 안에 Post가 재참조되어
//    🚨순환참조 이슈 발생.🚨
    private String author_email;

}

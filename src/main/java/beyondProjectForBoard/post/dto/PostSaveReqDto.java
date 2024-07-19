package beyondProjectForBoard.post.dto;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.post.domain.Post;
import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSaveReqDto {
    private String title;
    private String contents;
    private String email; // 글쓴이가 누구인지 알기 위해
//    추후 로그인 기능 이후에는 없어질 dto

    public Post toEntity (Author author){
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .author(author)
                .build();
    }
}

package beyondProjectForBoard.post.dto;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSaveReqDto {
    private String title;
    private String contents;
    private String email; // 글쓴이가 누구인지 알기 위해
//    추후 로그인 기능 이후에는 없어질 dto
    private String appointment;
    private String appointmentTime;

    public Post toEntity (Author author, LocalDateTime localDateTime){
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .appointment(this.appointment)
                .appointmentTime(localDateTime)
                .author(author)
                .build();
    }
}

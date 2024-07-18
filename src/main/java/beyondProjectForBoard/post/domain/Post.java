package beyondProjectForBoard.post.domain;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.post.dto.PostDetailResDto;
import beyondProjectForBoard.post.dto.PostListResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
//    @Column(columnDefinition = "TEXT")
    @Column(length = 3000)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    @CreationTimestamp
    private LocalDateTime created_time;
    @UpdateTimestamp
    private LocalDateTime updated_time;

    public Post(String title, String contents) {

    }

    public PostListResDto listFromEntity(){
        PostListResDto postListResDto = PostListResDto.builder()
                .id(this.id)
                .title(this.title)
                .author_email(this.author.getEmail())
                .build();
        return postListResDto;
    }


    public PostDetailResDto detailFromEntity(){
        PostDetailResDto postDetailResDto = PostDetailResDto.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .author_email(this.author.getEmail())
                .created_time(this.created_time)
                .updated_time(this.updated_time)
                .build();
        return postDetailResDto;
    }

}

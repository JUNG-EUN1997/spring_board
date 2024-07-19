package beyondProjectForBoard.post.domain;

import beyondProjectForBoard.author.domain.Author;
import beyondProjectForBoard.common.BaseTimeEntity;
import beyondProjectForBoard.post.dto.PostDetailResDto;
import beyondProjectForBoard.post.dto.PostListResDto;
import beyondProjectForBoard.post.dto.PostUpdateReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
//    @Column(columnDefinition = "TEXT")
    @Column(length = 3000)
    private String contents;

//    연관관계의 주인은 fk가 있는 post
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;


    public Post(String title, String contents) {

    }

    public PostListResDto listFromEntity(){
        PostListResDto postListResDto = PostListResDto.builder()
                .id(this.id)
                .title(this.title)
//                .author(this.author) // 🚨 순환참조 이슈 발생
                .author_email(this.author.getEmail()) // fetch 참조 발생
                .build();
        return postListResDto;
    }


    public PostDetailResDto detailFromEntity(){
        PostDetailResDto postDetailResDto = PostDetailResDto.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .author_email(this.author.getEmail())
                .created_time(this.getCreated_time())
                .updated_time(this.getUpdated_time())
                .build();
        return postDetailResDto;
    }

    public void updatePost(PostUpdateReqDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

}

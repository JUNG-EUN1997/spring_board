package beyondProjectForBoard.author.domain;

import beyondProjectForBoard.author.dto.AuthorListResDto;
import beyondProjectForBoard.author.dto.AuthorUpdateReqDto;
import beyondProjectForBoard.common.BaseTimeEntity;
import beyondProjectForBoard.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter

// builder 어노테이션을 통해 매개변수의 순서 매개변수 수의 개수 등을 유연하게 세팅하여 생성자로서 활용
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 30, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING) // 이걸 붙이지 않으면, Enum이 숫자인 0과 1로 만들어진다.
    private Role role;

//    일반적으로 부모엔티티(자식객체에 영향을 끼칠 수 있는 엔티티)에 cascade옵션 설정
//    FK는 보통 자식에게 걸린다.
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL) // Post domain의 대상
//     이렇게 설정하면, 회원 삭제 시, CASCASDE로 게시글도 함께 삭제된다

    private List<Post> posts;


    public Author(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AuthorListResDto listFromEntity() {
        AuthorListResDto authorListResDto = AuthorListResDto.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .build();
        return authorListResDto;
    }

    public void updateAuthor(AuthorUpdateReqDto dto) {
        this.name = dto.getName();
        this.password = dto.getPassword();
        /*
        * 원
        * */
    }

}


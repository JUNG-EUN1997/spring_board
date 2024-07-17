package beyondProjectForBoard.author.domain;

import beyondProjectForBoard.author.dto.AuthorDetailResDto;
import beyondProjectForBoard.author.dto.AuthorListResDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public Author(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AuthorListResDto listFromEntity() {
        return new AuthorListResDto(this.id, this.name, this.email);
    }

    public AuthorDetailResDto detailFromEntity() {
        return new AuthorDetailResDto(this.id, this.name, this.email, this.password, this.role);
    }
}


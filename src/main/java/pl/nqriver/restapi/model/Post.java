package pl.nqriver.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {
    @Id
    private Long id;
    private String title;
    private LocalDateTime created;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comments;
}

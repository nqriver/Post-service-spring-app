package pl.nqriver.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    private Long id;
    private String content;
    private LocalDateTime created;
}

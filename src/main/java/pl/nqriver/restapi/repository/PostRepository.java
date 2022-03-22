package pl.nqriver.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nqriver.restapi.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}

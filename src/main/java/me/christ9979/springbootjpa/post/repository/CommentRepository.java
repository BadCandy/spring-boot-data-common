package me.christ9979.springbootjpa.post.repository;

import me.christ9979.springbootjpa.post.Comment;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/*
    JpaRepository에서 구현되는 모든 메소드가 부담스럽다면,
    @RepositoryDefinition을 이용해 직접 필요한 쿼리 메소드만 정의할수 있다.
 */
@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository {

    Comment save(Comment comment);
    List<Comment> findAll();
}

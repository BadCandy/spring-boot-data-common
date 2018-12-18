package me.christ9979.springbootjpa.common.repository;

import me.christ9979.springbootjpa.post.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

/*
 * flush() (DB로 쿼리 직접 날림)를 직접 호출하기 위해서는 JpaRepository를 사용해야 한다.
 *
 */
public interface JpaCommentRepository extends JpaRepository<Comment, Long> {

    /*
        비동기 쿼리. 비추
     */
    @Async
    ListenableFuture<List<Comment>> findByCommentContainsIgnoreCase(String comment);
}

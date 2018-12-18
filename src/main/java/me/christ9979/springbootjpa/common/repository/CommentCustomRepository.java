package me.christ9979.springbootjpa.common.repository;

import me.christ9979.springbootjpa.post.Comment;
import me.christ9979.springbootjpa.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

public interface CommentCustomRepository extends MyRepository<Comment, Long> {

    /*
        @Query : 기본적으로 JPQL을 이용하여 쿼리를 선언한다.
     */
    @Query(value = "SELECT c FROM Comment AS c")
    List<Comment> findByTitleContains(String keyword);

    /*
        쿼리를 메소드 이름에 따라 자동생성해준다.
        -- 조합방법 --
        리턴타입 (접두어)(도입부)By(프로퍼티 표현식)(조건식)(AND|OR)(프로퍼티 표현식)(조건식)(정렬 조건) 파라미터
     */
    Page<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);

    Page<Comment> findByCommentContainsIgnoreCase(String comment, Pageable pageable);

    Stream<Comment> findByCommentContainsIgnoreCase(String comment);

}

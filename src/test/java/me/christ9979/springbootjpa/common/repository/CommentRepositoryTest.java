package me.christ9979.springbootjpa.common.repository;

import me.christ9979.springbootjpa.post.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentCustomRepository commentRepository;

    @Test
    public void customQueryTest() {

        Comment comment = new Comment();
        comment.setComment("hello comment");
        commentRepository.save(comment);

        List<Comment> all = commentRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

        long count = commentRepository.count();
        assertThat(count).isEqualTo(1);
    }

    /*
        Optional을 쓰지 않을경우 값이 없을때 null을 반환한다.
        Collection을 리턴할경우 null이 아닌 Empty Collection을 리턴한다.

     */
    @Test
    public void nullTest() {

        Optional<Comment> byId = commentRepository.findById(100l);
        assertThat(byId).isEmpty();

        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).isEmpty();
    }

    @Test
    public void autoCreatedQueryTest() {

        createComment(100, "spring data jpa");
        createComment(55, "HIBERNATE SPRING");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "likeCount"));

        Page<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest);

        assertThat(comments.getNumberOfElements()).isEqualTo(2);
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 55);


    }

    @Test
    public void streamTest() {

        createComment(100, "spring data jpa");
        createComment(55, "HIBERNATE SPRING");

        try(Stream<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("spring")) {
            Comment firstComment = comments.findFirst().get();
            assertThat(firstComment.getLikeCount()).isEqualTo(100);
        }
    }

    private void createComment(int likeCount, String comment) {

        Comment newComment = new Comment();
        newComment.setLikeCount(likeCount);
        newComment.setComment(comment);
        commentRepository.save(newComment);
    }
}
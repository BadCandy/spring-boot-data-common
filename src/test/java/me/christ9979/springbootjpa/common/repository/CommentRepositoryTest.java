package me.christ9979.springbootjpa.common.repository;

import me.christ9979.springbootjpa.post.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

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
}
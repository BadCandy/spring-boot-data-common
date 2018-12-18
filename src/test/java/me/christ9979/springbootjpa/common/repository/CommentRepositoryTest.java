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
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentCustomRepository commentRepository;

    @Autowired
    private JpaCommentRepository jpaCommentRepository;

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

    /*
     * 가급적이면 비동기 쿼리는 쓰지말자.
     * 대부분의 성능 문제는 DB 및 외부 커넥션에서 발생하는데, DB의 커넥션 풀이 제한되어 있는 이상
     * 비동기로 변경해도 성능이 그렇게 크게 좋아지지 않는다.
     * 그리고 비동기로 호출할경우 다음의 두가지 문제가 있다.
     *
     * 1. 기본적으로 SpringBootTest는 @Rollback이 활성화 되어있어,
     * 비동기 쿼리일 경우 JpaRepository에 있는 flush() 메소드를 호출해야한다.
     * (호출하지 않을경우, 궁극적으로 데이터를 사용하지 않을거라고 판단해서 Jpa가 insert 쿼리를 호출하지 않는다.
     *
     * 2. 테스트 메소드에서
     * 특정 쓰레드의 작업을 다른 쓰레드의 작업으로 위임하므로 Callback 메소드를 작성해도
     * get 쿼리의 결과를 정상적으로 얻어오지 못한다.
     * 왜냐하면 위임받은 다른 쓰레드는 특정 쓰레드에서의 데이터 변화를 감지하지 못하기 때문이다.
     *
     * 비동기가 필요하다면 Webflux를 사용하자.
     * Webflux의 단점은 현재까지 Webflux를 지원하는 RDB가 없다는건데,
     * Nosql인 MongoDB를 사용하면 Webflux를 지원한다.
     *
     *
     */
    @Test
    public void asyncTest() throws InterruptedException {

        createComment(100, "spring data jpa");
        createComment(55, "HIBERNATE SPRING");

        jpaCommentRepository.flush();
        assertThat(jpaCommentRepository.findAll().size()).isEqualTo(2);

        ListenableFuture<List<Comment>> future =
                jpaCommentRepository.findByCommentContainsIgnoreCase("spring");

        future.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable);
            }

            /*
                2번의 이유로 실제 얻어온 데이터 개수는 0이다.
             */
            @Override
            public void onSuccess(List<Comment> comments) {
                System.out.println("=============");
                System.out.println(comments.size());

            }
        });

        Thread.sleep(5000);
    }

    private void createComment(int likeCount, String comment) {

        Comment newComment = new Comment();
        newComment.setLikeCount(likeCount);
        newComment.setComment(comment);
        commentRepository.save(newComment);
    }

    private void createJpaComment(int likeCount, String comment) {

        Comment newComment = new Comment();
        newComment.setLikeCount(likeCount);
        newComment.setComment(comment);
        jpaCommentRepository.save(newComment);
    }
}
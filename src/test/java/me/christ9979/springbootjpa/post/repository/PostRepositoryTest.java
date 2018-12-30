package me.christ9979.springbootjpa.post.repository;

import me.christ9979.springbootjpa.post.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    @DataJpaTest
    - 스프링 Jpa에 관련된 빈만 주입해준다. (SliceTest)
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    /*
        기본적으로 Spring Test에는 @Transactional과 @Rollback이 자동으로 설정되어 있다.
        @Rollback을 끄지 않으면 영리한 하이버네이트는 어차피 롤백될건데 insert 쿼리를 실행하지 않아도 된다고 판단하기 때문에
        실질적인 테스트가 불가능하다.
        insert 쿼리가 수행되는지 테스트를 하기위해서는 @Rollback(false)로 설정해야 한다.
     */
    @Test
    @Rollback(false)
    public void crudRepository() {
        // Given
        Post post = new Post();
        post.setTitle("hello spring boot common");
        assertThat(post.getId()).isNull();

        // When
        Post newPost = postRepository.save(post);

        // Then
        assertThat(newPost.getId()).isNotNull();

        // When
        List<Post> posts = postRepository.findAll();

        // Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        // When : Pageable Request
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));

        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);


        // When : Custom Pageable Request
        page = postRepository.findByTitleContains("spring", PageRequest.of(0, 10));

        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When : Custom Count Request
        Long springCount = postRepository.countByTitleContains("spring");
        assertThat(springCount).isEqualTo(1);
    }

    @Test
    public void crud() {
        Post post = new Post();
        post.setTitle("hibernate");
        postRepository.save(post);
        postRepository.findMyPost();

        /*
            Spring Jpa delete를 사용하지 않고,
            커스텀한 delete를 사용한다.
         */
        postRepository.delete(post);
        postRepository.flush();

    }
}
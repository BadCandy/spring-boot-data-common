package me.christ9979.springbootjpa.post.repository;

import me.christ9979.springbootjpa.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    자동으로 bean으로 주입되어 기본쿼리들을 사용할수 있게 해준다.
    원래는 @EnableJpaRepositories라는 어노테이션을 스프링 설정 클래스위에 설정해야하지만
    스프링 부트에서는 jpa 의존성 사용시 자동으로 설정해준다.

    @EnableJpaRepositories에는 @Import(JpaRepositoriesRegistrar.class)가 있는데,
    이 부분에서 JpaRepository 인터페이스를 상속받은 인터페이스들을 자동으로 구현하여 빈으로 등록해준다.
 */
/*
    PostCustomRepository를 상속함으로써 우리가 커스텀으로 정의한 레파지토리를 Jpa와 같이 사용할수 있다.
 */
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post> {

    /*
        식별자가 아닌 title을 이용하여 page를 얻어오는 커스텀 쿼리 호출 메소드
     */
    Page<Post> findByTitleContains(String title, Pageable pageable);

    Long countByTitleContains(String title);
}

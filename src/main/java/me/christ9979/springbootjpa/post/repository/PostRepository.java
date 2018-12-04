package me.christ9979.springbootjpa.post.repository;

import me.christ9979.springbootjpa.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    자동으로 bean으로 주입되어 기본쿼리들을 사용할수 있게 해준다.
    원래는 @EnableJpaRepositories라는 어노테이션을 스프링 설정 클래스위에 설정해야하지만
    스프링 부트에서는 jpa 의존성 사용시 자동으로 설정해준다.

    @EnableJpaRepositories에는 @Import(JpaRepositoriesRegistrar.class)가 있는데,
    이 부분에서 JpaRepository 인터페이스를 상속받은 인터페이스들을 자동으로 구현하여 빈으로 등록해준다.
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}

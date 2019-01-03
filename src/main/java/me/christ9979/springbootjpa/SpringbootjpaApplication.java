package me.christ9979.springbootjpa;

import me.christ9979.springbootjpa.common.repository.BaseCommonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
/*
	@Async를 사용하기 위한 어노테이션
 */
@EnableAsync
/*
	@EnableJpaRepositories은 Spring Jpa 라이브러리가 있으면 사용하지 않아도
	자동설정 된다.

	기본적으로 Spring Data Common에서 쿼리를 만드는 방식은 세가지가 있다.
	1. (기본값) QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND : 커스텀한 쿼리를 직접 정의하지 않으면 메소드 이름으로 만들어준다.
	2. QueryLookupStrategy.Key.CREATE : 메소드 이름으로 쿼리를 만든다.
	3. QueryLookupStrategy.Key.USE_DECLARED_QUERY : 커스텀한 선언된 쿼리를 이용한다.

	Custom Repository를 만들때 Postfix를 Impl로 설정하고 싶지 않다면
	repositoryImplementationPostfix를 사용한다.

	모든 레파지토리에서 공통으로 사용하는 레파지토리를 정의하고 싶다면, repositoryBaseClass에 공통 레파지토리 구현체를 정의한다.
	그리고 공통 레파지토리 인터페이스를 상속받아 다른 레파지토리에서 사용한다.
 */
@EnableJpaRepositories(
//		queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND, repositoryImplementationPostfix = "default",
		repositoryBaseClass = BaseCommonRepository.class)
public class SpringbootjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
	}
}

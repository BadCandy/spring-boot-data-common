package me.christ9979.springbootjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

/*
	기본적으로 Spring Data Common에서 쿼리를 만드는 방식은 세가지가 있다.
	1. (기본값) QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND : 커스텀한 쿼리를 직접 정의하지 않으면 메소드 이름으로 만들어준다.
	2. QueryLookupStrategy.Key.CREATE : 메소드 이름으로 쿼리를 만든다.
	3. QueryLookupStrategy.Key.USE_DECLARED_QUERY : 커스텀한 선언된 쿼리를 이용한다.
 */
@SpringBootApplication
//@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public class SpringbootjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
	}
}

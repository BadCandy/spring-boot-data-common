package me.christ9979.springbootjpa.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/*
    모든 레파지토리에 공통으로 추가하고 싶은 기능이 있거나,
    덮어쓰고 싶은 기능이 있으면 다음과 같이 레파지토리를 공통으로 만들고
    이것의 구현체를 만들어 @EnableJpaRepository에 속성으로 등록한다.
 */
@NoRepositoryBean
public interface CommonRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /*
        새로운 공통 기능을 추가한다.
     */
    boolean contains(T entity);
}

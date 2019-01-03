package me.christ9979.springbootjpa.common.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/*
    모든 레파지토리에 공통으로 사용할 구현체를 만든다.
 */
public class BaseCommonRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CommonRepository<T, ID> {

    private EntityManager entityManager;

    /*
        기본적으로 있어야 하는 생성자이다.
        Runtime시 생성자의 인자들은 빈으로 이미 등록된것을 주입받는다.

     */
    public BaseCommonRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    /*
        공통으로 사용할 기능을 정의한다.
     */
    @Override
    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }

    /*
        다른 상위 인터페이스 및 클래스의 메소드도
        오버라이딩 가능하다.
     */
}

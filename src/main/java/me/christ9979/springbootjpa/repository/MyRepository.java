package me.christ9979.springbootjpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/*
    공통 쿼리 메소드를 중복되게 작성하기 번거롭다면,
    @NoRepositoryBean과 Repository를 상속해서 공통 쿼리 메소드를 작성한다.
    이 인터페이스를 상속받아 하위 인터페이스에서 공통으로 사용할수 있다.
 */
@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends Repository<T, ID> {

    <E extends T> E save(E entity);

    List<T> findAll();

    long count();
}

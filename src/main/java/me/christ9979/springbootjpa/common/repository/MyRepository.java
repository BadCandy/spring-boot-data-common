package me.christ9979.springbootjpa.common.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
    공통 쿼리 메소드를 중복되게 작성하기 번거롭다면,
    @NoRepositoryBean과 Repository를 상속해서 공통 쿼리 메소드를 작성한다.
    이 인터페이스를 상속받아 하위 인터페이스에서 공통으로 사용할수 있다.
 */
@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends Repository<T, ID> {

    /*
        스프링 프레임워크 5.0부터 지원하는 Null 체크 어노테이션 지원.
        @NonNullApi : 패키지에 사용
        @NonNull, @Nullable
     */
    <E extends T> E save(@NonNull E entity);

    List<T> findAll();

    long count();

    <E extends T> Optional<E> findById(ID id);
}

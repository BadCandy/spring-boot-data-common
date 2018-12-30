package me.christ9979.springbootjpa.post.repository;

import me.christ9979.springbootjpa.post.Post;

import java.util.List;

/*
    커스텀한 레파지토리를 만들고 싶을때 원하는 이름의 인터페이스를 정의한다.
    단, 이 인터페이스를 구현하는 클래스는 인터페이스 이름 + Impl이라는 네이밍 규칙을
    지켜야한다.
 */
public interface PostCustomRepository<T> {

    List<Post> findMyPost();

    /*
        해당 메소드는 JpaRepository와 중복이 되는 메소드이다.
        JpaRepository와 같이 사용할 경우 Custom한 레파지토리의 메소드를 사용하게 된다.
        즉, 아래의 메소드로 덮어씌워지게 되는것이다.

        여담으로 JpaRepository의 delete의 동작 방식은 해당 도메인을 EntityManager가 관리하게끔
        merge(Detached -> Persistance 상태로 변경)하고 remove 상태로 변경한 후 삭제한다.
        성능적으로 덜 좋을텐데도 이렇게 동작하는 이유는 Jpa에서 Cascade 삭제를 구현하기 위함인데,
        연쇄적으로 삭제하기 위해서는 EntityManager가 다시 관리할수 있도록 해당 도메인들을 로딩하여
        마킹해야하기 때문이다.

        여기서는 이러한 성능 손실이 마음에 들지 않는다고하여 예시로 delete를 재정의 해보자.
     */
    void delete(T entity);
}

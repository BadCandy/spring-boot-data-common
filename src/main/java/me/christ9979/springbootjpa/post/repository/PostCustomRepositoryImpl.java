package me.christ9979.springbootjpa.post.repository;

import me.christ9979.springbootjpa.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/*
    커스텀한 레파지토리를 만들기 위한 구현 클래스.
    이 클래스는 순수 어느 기술에도 종속되지 않는 POJO 클래스이다.
 */
@Repository
@Transactional
public class PostCustomRepositoryImpl implements PostCustomRepository<Post> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Post> findMyPost() {
        System.out.println("custom findMyPost");
        return entityManager.createQuery("SELECT p FROM Post AS p")
                .getResultList();
    }

//    @Override
//    public void delete(Post entity) {
//        System.out.println("custom delete");
//        entityManager.remove(entity);
//    }
}

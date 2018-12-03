package me.christ9979.springbootjpa;

import me.christ9979.springbootjpa.account.Account;
import me.christ9979.springbootjpa.account.Address;
import me.christ9979.springbootjpa.account.Study;
import me.christ9979.springbootjpa.post.Comment;
import me.christ9979.springbootjpa.post.Post;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Component
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        doAccount();
        doPost();

    }

    private void doPost() {

        Post post = new Post();
        post.setTitle("제목입니다.");

        Comment comment1 = new Comment();
        comment1.setComment("댓글1 입니다.");
        post.addComment(comment1);

        Comment comment2 = new Comment();
        comment2.setComment("댓글2 입니다.");
        post.addComment(comment2);

        Session session = entityManager.unwrap(Session.class);

        /*
            persist가 수행된다고 하더라도 트랜잭션이 끝나기 직전까진 쿼리가 실행되지 않는다.
            Persistent Context에 캐싱된다.
            (1차 캐시)
         */
        session.persist(post);

        /*
            추후 persist(insert) 이후 업데이트 쿼리가 발생한다.
         */
        post.setTitle("바뀐 제목");

        /*
            하지만 이전과 같은 값으로 다시 바뀌게 된다면,
            위의 업데이트 쿼리는 발생하지 않는다.
            (Dirty check)
         */
        post.setTitle("제목입니다.");

        session.delete(post);
    }

    private void doAccount() {

        // set account
        Account account = new Account();
        account.setUsername("christ99791s");
        account.setPassword("pass1w3");

        // set composite relation
        Address address = new Address();
        address.setCity("seoul");
        address.setState("seoul");
        address.setStreet("str");
        address.setZipCode("zip");
        account.setAddress(address);

        // set one to many relation
        Study study = new Study();
        study.setName("spring jpa");
        account.addStudy(study);

        entityManager.persist(study);
        entityManager.persist(account);

        //        entityManager.remove(account);
//        entityManager.remove(study);
    }
}

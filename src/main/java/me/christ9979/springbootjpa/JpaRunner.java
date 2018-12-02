package me.christ9979.springbootjpa;

import me.christ9979.springbootjpa.account.Account;
import me.christ9979.springbootjpa.account.Address;
import me.christ9979.springbootjpa.account.Study;
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

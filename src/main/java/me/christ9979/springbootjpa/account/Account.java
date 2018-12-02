package me.christ9979.springbootjpa.account;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {

    /*
        식별자를 자동생성함.
        GeneratedValue에는 4가지 전략이 있음.
        GenerationType.AUTO : DB 밴더마다 기본 설정을 사용
        GenerationType.TABLE : 시퀀스 테이블을 만들어 시퀀스를 유사 흉내
        GenerationType.IDENTITY : MYSQL의 auto_increment 유사 (DB 종속적)
        GenerationType.SEQUENCE : 오라클 DB의 시퀀스 유사 (DB 종속적)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /*
        Column의 속성을 지정가능
     */
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    /*
        TemporalType.TIME : Only 시간
        TemporalType.DATE : Only 날짜
        TemporalType.TIMESTAMP : 날짜 + 시간
     */
    @Temporal(TemporalType.DATE)
    private Date created = new Date();

    private String yes;

    @Transient
    private String no;

    /*
        Composite Value 타입을 DB 스키마에 매핑할수 있도록함.
        @AttributeOverrides를 사용할경우 컬럼이름 변경가능
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    })
    private Address address;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

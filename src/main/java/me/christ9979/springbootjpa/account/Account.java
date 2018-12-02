package me.christ9979.springbootjpa.account;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    /*
        일대다 관계를 정의하는 어노테이션.
        현재 클래스가 owned 클래스가 된다. 꼭 mappedBy로 owning 클래스의 매핑 필드를 명시해야한다.
     */
    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getYes() {
        return yes;
    }

    public void setYes(String yes) {
        this.yes = yes;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

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

    /*
        다대다 관계를 표현하기 위해
        관례적으로 작성하는 메소드
     */
    public void addStudy(Study study) {

        study.setOwner(this);
        // 사실상 아래의 코드는 빠져도 상관없지만
        // 객체지향적으로 다대다 관계를 표현하기 위해 작성하자.
        this.getStudies().add(study);
    }

    /*
        다대다 관계를 삭제하기 위해
        관례적으로 작성하는 메소드
     */
    public void removeStudy(Study study) {

        study.setOwner(null);
        this.getStudies().remove(study);
    }
}

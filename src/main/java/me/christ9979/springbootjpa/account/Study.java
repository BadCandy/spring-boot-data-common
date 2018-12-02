package me.christ9979.springbootjpa.account;

import javax.persistence.*;

@Entity
public class Study {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /*
        다대일 관계에서 사용하는 어노테이션
        현재클래스가 Owning(주인)이 된다.
     */
    @ManyToOne
    private Account owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }
}

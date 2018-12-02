package me.christ9979.springbootjpa.account;

import javax.persistence.Embeddable;

/*
    Entity 타입안에서 쓰일수 있도록 Composite 타입을 매핑.
    (Entity 타입과는 다르게 단독으로 쓰일수 없음.)
 */
@Embeddable
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

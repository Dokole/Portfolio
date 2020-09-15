package com.producingservice.app.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Embeddable
public class Address implements Serializable {
    @NotNull
    @Column(name = "country", nullable = false, length = 30)
    protected String country;
    @NotNull
    @Column(name = "city", nullable = false, length = 30)
    protected String city;
    @NotNull
    @Column(name = "street", nullable = false, length = 30)
    protected String street;
    @NotNull
    @Column(name = "house", nullable = false, length = 10)
    protected String house;

    public Address() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                '}';
    }
}

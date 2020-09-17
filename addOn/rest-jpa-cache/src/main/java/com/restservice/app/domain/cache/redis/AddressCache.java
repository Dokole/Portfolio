package com.restservice.app.domain.cache.redis;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class AddressCache {

    protected String country;
    protected String city;
    protected String street;
    protected String house;

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
}

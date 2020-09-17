package com.soapdataservice.app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Entity
@Table(name = "manufacturers")
public class Manufacturer extends DomainSuperclass implements MetadataContainer {

    @NotNull
    @Column(name = "name", nullable = false, length = 30, unique = true)
    protected String name;

    @Embedded
    protected Address address;

    @ManyToMany(mappedBy = "manufacturers", fetch = FetchType.LAZY)
    protected Set<Brand> brands = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer")
    protected Set<Item> items = new HashSet<>();

    public Manufacturer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Brand> getBrands() {
        return brands;
    }

    public void setBrands(Set<Brand> brands) {
        this.brands = brands;
    }


    public void addBrand(Brand brand) {
        this.brands.add(brand);
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", id=" + id +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

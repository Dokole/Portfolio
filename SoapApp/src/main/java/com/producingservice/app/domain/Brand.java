package com.producingservice.app.domain;

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
@Table(name = "brands")
public class Brand extends DomainSuperclass implements MetadataContainer {

    @NotNull
    @Column(name = "name", nullable = false, length = 30, unique = true)
    protected String name;

    @Embedded
    protected Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "brand_manufacturer",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "manufacturer_id"))
    protected Set<Manufacturer> manufacturers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brandDetails")
    protected Set<Item> items = new HashSet<>();

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getOfficeAddress() {
        return address;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.address = officeAddress;
    }

    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(Set<Manufacturer> manufactures) {
        this.manufacturers = manufactures;
    }


    public void addManufacturer(Manufacturer manufacturer) {
        this.manufacturers.add(manufacturer);
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", officeAddress=" + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return name.equals(brand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

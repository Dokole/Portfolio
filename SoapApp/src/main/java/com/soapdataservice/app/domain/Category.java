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
@Table(name = "categories")
public class Category extends DomainSuperclass implements MetadataContainer {

    @NotNull
    @Column(name = "name", nullable = false, length = 20, unique = true)
    protected String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    protected Set<Item> items = new HashSet<>();


    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

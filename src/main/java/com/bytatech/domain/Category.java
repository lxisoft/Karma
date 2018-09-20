package com.bytatech.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Category entity.
 * @author Dheeraj das.
 */
@ApiModel(description = "Category entity. @author Dheeraj das.")
@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sub_category")
    private String subCategory;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<Need> needs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public Category subCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Set<Need> getNeeds() {
        return needs;
    }

    public Category needs(Set<Need> needs) {
        this.needs = needs;
        return this;
    }

    public Category addNeeds(Need need) {
        this.needs.add(need);
        need.getCategories().add(this);
        return this;
    }

    public Category removeNeeds(Need need) {
        this.needs.remove(need);
        need.getCategories().remove(this);
        return this;
    }

    public void setNeeds(Set<Need> needs) {
        this.needs = needs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        if (category.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", subCategory='" + getSubCategory() + "'" +
            "}";
    }
}

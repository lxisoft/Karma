package com.bytatech.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Publisher entity.
 */
public class PublisherDTO implements Serializable {

    private Long id;

    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PublisherDTO publisherDTO = (PublisherDTO) o;
        if(publisherDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), publisherDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PublisherDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

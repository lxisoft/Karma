package com.lxisoft.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Severity entity.
 */
public class SeverityDTO implements Serializable {

    private Long id;

    private String severityLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SeverityDTO severityDTO = (SeverityDTO) o;
        if(severityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), severityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SeverityDTO{" +
            "id=" + getId() +
            ", severityLevel='" + getSeverityLevel() + "'" +
            "}";
    }
}

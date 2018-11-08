package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * @Author Anjali
 */
@ApiModel(description = "@Author Anjali")
@Entity
@Table(name = "severity")
public class Severity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "severity_level")
    private String severityLevel;

    @OneToMany(mappedBy = "severity")
    private Set<Need> needs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public Severity severityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
        return this;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public Set<Need> getNeeds() {
        return needs;
    }

    public Severity needs(Set<Need> needs) {
        this.needs = needs;
        return this;
    }

    public Severity addNeeds(Need need) {
        this.needs.add(need);
        need.setSeverity(this);
        return this;
    }

    public Severity removeNeeds(Need need) {
        this.needs.remove(need);
        need.setSeverity(null);
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
        Severity severity = (Severity) o;
        if (severity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), severity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Severity{" +
            "id=" + getId() +
            ", severityLevel='" + getSeverityLevel() + "'" +
            "}";
    }
}

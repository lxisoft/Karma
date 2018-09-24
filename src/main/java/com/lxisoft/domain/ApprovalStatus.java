package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * ApprovalStatus entity.
 * @author Sanil kumar
 */
@ApiModel(description = "ApprovalStatus entity. @author Sanil kumar")
@Entity
@Table(name = "approval_status")
public class ApprovalStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "approvalStatus")
    private Set<Need> needs = new HashSet<>();

    @OneToMany(mappedBy = "approvalStatus")
    private Set<Help> helps = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public ApprovalStatus status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Need> getNeeds() {
        return needs;
    }

    public ApprovalStatus needs(Set<Need> needs) {
        this.needs = needs;
        return this;
    }

    public ApprovalStatus addNeeds(Need need) {
        this.needs.add(need);
        need.setApprovalStatus(this);
        return this;
    }

    public ApprovalStatus removeNeeds(Need need) {
        this.needs.remove(need);
        need.setApprovalStatus(null);
        return this;
    }

    public void setNeeds(Set<Need> needs) {
        this.needs = needs;
    }

    public Set<Help> getHelps() {
        return helps;
    }

    public ApprovalStatus helps(Set<Help> helps) {
        this.helps = helps;
        return this;
    }

    public ApprovalStatus addHelps(Help help) {
        this.helps.add(help);
        help.setApprovalStatus(this);
        return this;
    }

    public ApprovalStatus removeHelps(Help help) {
        this.helps.remove(help);
        help.setApprovalStatus(null);
        return this;
    }

    public void setHelps(Set<Help> helps) {
        this.helps = helps;
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
        ApprovalStatus approvalStatus = (ApprovalStatus) o;
        if (approvalStatus.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), approvalStatus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApprovalStatus{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}

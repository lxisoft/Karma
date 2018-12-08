package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * @Author Sarangi Balu
 */
@ApiModel(description = "@Author Sarangi Balu")
@Entity
@Table(name = "verification_team")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VerificationTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approval_level")
    private String approvalLevel;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "verification_team_approving_users",
               joinColumns = @JoinColumn(name = "verification_teams_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "approving_users_id", referencedColumnName = "id"))
    private Set<RegisteredUser> approvingUsers = new HashSet<>();

    @OneToMany(mappedBy = "verificationTeam")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Need> needs = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApprovalLevel() {
        return approvalLevel;
    }

    public VerificationTeam approvalLevel(String approvalLevel) {
        this.approvalLevel = approvalLevel;
        return this;
    }

    public void setApprovalLevel(String approvalLevel) {
        this.approvalLevel = approvalLevel;
    }

    public Set<RegisteredUser> getApprovingUsers() {
        return approvingUsers;
    }

    public VerificationTeam approvingUsers(Set<RegisteredUser> registeredUsers) {
        this.approvingUsers = registeredUsers;
        return this;
    }

    public VerificationTeam addApprovingUsers(RegisteredUser registeredUser) {
        this.approvingUsers.add(registeredUser);
        registeredUser.getVerificationTeams().add(this);
        return this;
    }

    public VerificationTeam removeApprovingUsers(RegisteredUser registeredUser) {
        this.approvingUsers.remove(registeredUser);
        registeredUser.getVerificationTeams().remove(this);
        return this;
    }

    public void setApprovingUsers(Set<RegisteredUser> registeredUsers) {
        this.approvingUsers = registeredUsers;
    }

    public Set<Need> getNeeds() {
        return needs;
    }

    public VerificationTeam needs(Set<Need> needs) {
        this.needs = needs;
        return this;
    }

    public VerificationTeam addNeeds(Need need) {
        this.needs.add(need);
        need.setVerificationTeam(this);
        return this;
    }

    public VerificationTeam removeNeeds(Need need) {
        this.needs.remove(need);
        need.setVerificationTeam(null);
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
        VerificationTeam verificationTeam = (VerificationTeam) o;
        if (verificationTeam.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), verificationTeam.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VerificationTeam{" +
            "id=" + getId() +
            ", approvalLevel='" + getApprovalLevel() + "'" +
            "}";
    }
}

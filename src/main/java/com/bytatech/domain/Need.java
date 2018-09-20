package com.bytatech.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Need entity
 * @author Balagopal v
 */
@ApiModel(description = "Need entity @author Balagopal v")
@Entity
@Table(name = "need")
public class Need implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "beneficiary_type")
    private String beneficiaryType;

    @Column(name = "jhi_date")
    private Instant date;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @ManyToOne
    private LoggedUser loggedUser;

    @OneToMany(mappedBy = "need")
    @JsonIgnore
    private Set<Media> proofs = new HashSet<>();

    @OneToMany(mappedBy = "need")
    @JsonIgnore
    private Set<UserCheck> userChecks = new HashSet<>();

    @OneToMany(mappedBy = "need")
    @JsonIgnore
    private Set<Service> services = new HashSet<>();

    @ManyToOne
    private Severity severity;

    @ManyToOne
    private VerificationTeam verificationTeam;

    @ManyToOne
    private ApprovalStatus approvalStatus;

    @ManyToMany
    @JoinTable(name = "need_categories",
               joinColumns = @JoinColumn(name="needs_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="categories_id", referencedColumnName="id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "need_willing_users",
               joinColumns = @JoinColumn(name="needs_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="willing_users_id", referencedColumnName="id"))
    private Set<LoggedUser> willingUsers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Need description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeneficiaryType() {
        return beneficiaryType;
    }

    public Need beneficiaryType(String beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
        return this;
    }

    public void setBeneficiaryType(String beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public Instant getDate() {
        return date;
    }

    public Need date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Boolean isIsApproved() {
        return isApproved;
    }

    public Need isApproved(Boolean isApproved) {
        this.isApproved = isApproved;
        return this;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public LoggedUser getLoggedUser() {
        return loggedUser;
    }

    public Need loggedUser(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
        return this;
    }

    public void setLoggedUser(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Set<Media> getProofs() {
        return proofs;
    }

    public Need proofs(Set<Media> media) {
        this.proofs = media;
        return this;
    }

    public Need addProofs(Media media) {
        this.proofs.add(media);
        media.setNeed(this);
        return this;
    }

    public Need removeProofs(Media media) {
        this.proofs.remove(media);
        media.setNeed(null);
        return this;
    }

    public void setProofs(Set<Media> media) {
        this.proofs = media;
    }

    public Set<UserCheck> getUserChecks() {
        return userChecks;
    }

    public Need userChecks(Set<UserCheck> userChecks) {
        this.userChecks = userChecks;
        return this;
    }

    public Need addUserChecks(UserCheck userCheck) {
        this.userChecks.add(userCheck);
        userCheck.setNeed(this);
        return this;
    }

    public Need removeUserChecks(UserCheck userCheck) {
        this.userChecks.remove(userCheck);
        userCheck.setNeed(null);
        return this;
    }

    public void setUserChecks(Set<UserCheck> userChecks) {
        this.userChecks = userChecks;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Need services(Set<Service> services) {
        this.services = services;
        return this;
    }

    public Need addServices(Service service) {
        this.services.add(service);
        service.setNeed(this);
        return this;
    }

    public Need removeServices(Service service) {
        this.services.remove(service);
        service.setNeed(null);
        return this;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Need severity(Severity severity) {
        this.severity = severity;
        return this;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public VerificationTeam getVerificationTeam() {
        return verificationTeam;
    }

    public Need verificationTeam(VerificationTeam verificationTeam) {
        this.verificationTeam = verificationTeam;
        return this;
    }

    public void setVerificationTeam(VerificationTeam verificationTeam) {
        this.verificationTeam = verificationTeam;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public Need approvalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
        return this;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Need categories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Need addCategories(Category category) {
        this.categories.add(category);
        category.getNeeds().add(this);
        return this;
    }

    public Need removeCategories(Category category) {
        this.categories.remove(category);
        category.getNeeds().remove(this);
        return this;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<LoggedUser> getWillingUsers() {
        return willingUsers;
    }

    public Need willingUsers(Set<LoggedUser> loggedUsers) {
        this.willingUsers = loggedUsers;
        return this;
    }

    public Need addWillingUsers(LoggedUser loggedUser) {
        this.willingUsers.add(loggedUser);
        loggedUser.getNeeds().add(this);
        return this;
    }

    public Need removeWillingUsers(LoggedUser loggedUser) {
        this.willingUsers.remove(loggedUser);
        loggedUser.getNeeds().remove(this);
        return this;
    }

    public void setWillingUsers(Set<LoggedUser> loggedUsers) {
        this.willingUsers = loggedUsers;
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
        Need need = (Need) o;
        if (need.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), need.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Need{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", beneficiaryType='" + getBeneficiaryType() + "'" +
            ", date='" + getDate() + "'" +
            ", isApproved='" + isIsApproved() + "'" +
            "}";
    }
}

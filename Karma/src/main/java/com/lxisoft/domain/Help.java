package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Service entity
 * @Author Sooraj Pn
 */
@ApiModel(description = "Service entity @Author Sooraj Pn")
@Entity
@Table(name = "help")
public class Help implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_time")
    private Instant time;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "help")
    @JsonIgnore
    private Set<Media> proofs = new HashSet<>();

    @OneToMany(mappedBy = "help")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    private ApprovalStatus approvalStatus;

    @ManyToOne
    private LoggedUser providedUser;

    @ManyToOne
    private Need fulfilledNeed;

    @OneToMany(mappedBy = "checkedHelp")
    @JsonIgnore
    private Set<UserCheck> userChecks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public Help time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public Help description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Media> getProofs() {
        return proofs;
    }

    public Help proofs(Set<Media> media) {
        this.proofs = media;
        return this;
    }

    public Help addProofs(Media media) {
        this.proofs.add(media);
        media.setHelp(this);
        return this;
    }

    public Help removeProofs(Media media) {
        this.proofs.remove(media);
        media.setHelp(null);
        return this;
    }

    public void setProofs(Set<Media> media) {
        this.proofs = media;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Help comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Help addComments(Comment comment) {
        this.comments.add(comment);
        comment.setHelp(this);
        return this;
    }

    public Help removeComments(Comment comment) {
        this.comments.remove(comment);
        comment.setHelp(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public Help approvalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
        return this;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public LoggedUser getProvidedUser() {
        return providedUser;
    }

    public Help providedUser(LoggedUser loggedUser) {
        this.providedUser = loggedUser;
        return this;
    }

    public void setProvidedUser(LoggedUser loggedUser) {
        this.providedUser = loggedUser;
    }

    public Need getFulfilledNeed() {
        return fulfilledNeed;
    }

    public Help fulfilledNeed(Need need) {
        this.fulfilledNeed = need;
        return this;
    }

    public void setFulfilledNeed(Need need) {
        this.fulfilledNeed = need;
    }

    public Set<UserCheck> getUserChecks() {
        return userChecks;
    }

    public Help userChecks(Set<UserCheck> userChecks) {
        this.userChecks = userChecks;
        return this;
    }

    public Help addUserChecks(UserCheck userCheck) {
        this.userChecks.add(userCheck);
        userCheck.setCheckedHelp(this);
        return this;
    }

    public Help removeUserChecks(UserCheck userCheck) {
        this.userChecks.remove(userCheck);
        userCheck.setCheckedHelp(null);
        return this;
    }

    public void setUserChecks(Set<UserCheck> userChecks) {
        this.userChecks = userChecks;
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
        Help help = (Help) o;
        if (help.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), help.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Help{" +
            "id=" + getId() +
            ", time='" + getTime() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
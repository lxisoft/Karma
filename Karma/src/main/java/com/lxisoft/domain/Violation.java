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
 * Violation entity
 * @author Deepthi E
 */
@ApiModel(description = "Violation entity @author Deepthi E")
@Entity
@Table(name = "violation")
public class Violation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "is_anonymous")
    private Boolean isAnonymous;

    @Column(name = "jhi_date")
    private Instant date;

    @OneToMany(mappedBy = "violation")
    private Set<Media> proofs = new HashSet<>();

    @OneToMany(mappedBy = "violation")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "violation")
    private Set<UserCheck> userChecks = new HashSet<>();

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

    public Violation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIsAnonymous() {
        return isAnonymous;
    }

    public Violation isAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
        return this;
    }

    public void setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Instant getDate() {
        return date;
    }

    public Violation date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Set<Media> getProofs() {
        return proofs;
    }

    public Violation proofs(Set<Media> media) {
        this.proofs = media;
        return this;
    }

    public Violation addProofs(Media media) {
        this.proofs.add(media);
        media.setViolation(this);
        return this;
    }

    public Violation removeProofs(Media media) {
        this.proofs.remove(media);
        media.setViolation(null);
        return this;
    }

    public void setProofs(Set<Media> media) {
        this.proofs = media;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Violation comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Violation addComments(Comment comment) {
        this.comments.add(comment);
        comment.setViolation(this);
        return this;
    }

    public Violation removeComments(Comment comment) {
        this.comments.remove(comment);
        comment.setViolation(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<UserCheck> getUserChecks() {
        return userChecks;
    }

    public Violation userChecks(Set<UserCheck> userChecks) {
        this.userChecks = userChecks;
        return this;
    }

    public Violation addUserChecks(UserCheck userCheck) {
        this.userChecks.add(userCheck);
        userCheck.setViolation(this);
        return this;
    }

    public Violation removeUserChecks(UserCheck userCheck) {
        this.userChecks.remove(userCheck);
        userCheck.setViolation(null);
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
        Violation violation = (Violation) o;
        if (violation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), violation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Violation{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", isAnonymous='" + isIsAnonymous() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}

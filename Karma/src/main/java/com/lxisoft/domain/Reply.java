package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Reply  entity
 * @author  Deepthi E
 */
@ApiModel(description = "Reply  entity @author  Deepthi E")
@Entity
@Table(name = "reply")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "jhi_date")
    private Instant date;

    @ManyToOne
    @JsonIgnoreProperties("replies")
    private Comment comment;

    @ManyToOne
    @JsonIgnoreProperties("")
    private LoggedUser repliedUser;

    @OneToMany(mappedBy = "reply")
    private Set<UserCheck> userChecks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public Reply message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getDate() {
        return date;
    }

    public Reply date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Comment getComment() {
        return comment;
    }

    public Reply comment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public LoggedUser getRepliedUser() {
        return repliedUser;
    }

    public Reply repliedUser(LoggedUser loggedUser) {
        this.repliedUser = loggedUser;
        return this;
    }

    public void setRepliedUser(LoggedUser loggedUser) {
        this.repliedUser = loggedUser;
    }

    public Set<UserCheck> getUserChecks() {
        return userChecks;
    }

    public Reply userChecks(Set<UserCheck> userChecks) {
        this.userChecks = userChecks;
        return this;
    }

    public Reply addUserChecks(UserCheck userCheck) {
        this.userChecks.add(userCheck);
        userCheck.setReply(this);
        return this;
    }

    public Reply removeUserChecks(UserCheck userCheck) {
        this.userChecks.remove(userCheck);
        userCheck.setReply(null);
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
        Reply reply = (Reply) o;
        if (reply.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reply.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reply{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}

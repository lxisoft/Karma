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
 * Comment entity
 * @author Deepthi E
 */
@ApiModel(description = "Comment entity @author Deepthi E")
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "jhi_date")
    private Instant date;

    @ManyToOne
    @JsonIgnoreProperties("comments")
    private Need need;

    @ManyToOne
    @JsonIgnoreProperties("comments")
    private Help help;

    @ManyToOne
    @JsonIgnoreProperties("comments")
    private NewsFeed newsFeed;

    @OneToMany(mappedBy = "comment")
    private Set<Reply> replies = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("")
    private LoggedUser commentedUser;

    @OneToMany(mappedBy = "comment")
    private Set<UserCheck> userChecks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("comments")
    private Violation violation;

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

    public Comment message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getDate() {
        return date;
    }

    public Comment date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Need getNeed() {
        return need;
    }

    public Comment need(Need need) {
        this.need = need;
        return this;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public Help getHelp() {
        return help;
    }

    public Comment help(Help help) {
        this.help = help;
        return this;
    }

    public void setHelp(Help help) {
        this.help = help;
    }

    public NewsFeed getNewsFeed() {
        return newsFeed;
    }

    public Comment newsFeed(NewsFeed newsFeed) {
        this.newsFeed = newsFeed;
        return this;
    }

    public void setNewsFeed(NewsFeed newsFeed) {
        this.newsFeed = newsFeed;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public Comment replies(Set<Reply> replies) {
        this.replies = replies;
        return this;
    }

    public Comment addReplies(Reply reply) {
        this.replies.add(reply);
        reply.setComment(this);
        return this;
    }

    public Comment removeReplies(Reply reply) {
        this.replies.remove(reply);
        reply.setComment(null);
        return this;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public LoggedUser getCommentedUser() {
        return commentedUser;
    }

    public Comment commentedUser(LoggedUser loggedUser) {
        this.commentedUser = loggedUser;
        return this;
    }

    public void setCommentedUser(LoggedUser loggedUser) {
        this.commentedUser = loggedUser;
    }

    public Set<UserCheck> getUserChecks() {
        return userChecks;
    }

    public Comment userChecks(Set<UserCheck> userChecks) {
        this.userChecks = userChecks;
        return this;
    }

    public Comment addUserChecks(UserCheck userCheck) {
        this.userChecks.add(userCheck);
        userCheck.setComment(this);
        return this;
    }

    public Comment removeUserChecks(UserCheck userCheck) {
        this.userChecks.remove(userCheck);
        userCheck.setComment(null);
        return this;
    }

    public void setUserChecks(Set<UserCheck> userChecks) {
        this.userChecks = userChecks;
    }

    public Violation getViolation() {
        return violation;
    }

    public Comment violation(Violation violation) {
        this.violation = violation;
        return this;
    }

    public void setViolation(Violation violation) {
        this.violation = violation;
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
        Comment comment = (Comment) o;
        if (comment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Comment{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}

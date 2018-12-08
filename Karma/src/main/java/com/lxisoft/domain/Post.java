package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Post entity
 * @author Deepthi E
 */
@ApiModel(description = "Post entity @author Deepthi E")
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_date")
    private Instant date;

    @OneToMany(mappedBy = "post")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Media> attachments = new HashSet<>();
    @OneToMany(mappedBy = "post")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comment> comments = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("posts")
    private RegisteredUser registeredUser;

    @OneToMany(mappedBy = "post")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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

    public Post description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDate() {
        return date;
    }

    public Post date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Set<Media> getAttachments() {
        return attachments;
    }

    public Post attachments(Set<Media> media) {
        this.attachments = media;
        return this;
    }

    public Post addAttachments(Media media) {
        this.attachments.add(media);
        media.setPost(this);
        return this;
    }

    public Post removeAttachments(Media media) {
        this.attachments.remove(media);
        media.setPost(null);
        return this;
    }

    public void setAttachments(Set<Media> media) {
        this.attachments = media;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Post comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Post addComments(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
        return this;
    }

    public Post removeComments(Comment comment) {
        this.comments.remove(comment);
        comment.setPost(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public Post registeredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
        return this;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public Set<UserCheck> getUserChecks() {
        return userChecks;
    }

    public Post userChecks(Set<UserCheck> userChecks) {
        this.userChecks = userChecks;
        return this;
    }

    public Post addUserChecks(UserCheck userCheck) {
        this.userChecks.add(userCheck);
        userCheck.setPost(this);
        return this;
    }

    public Post removeUserChecks(UserCheck userCheck) {
        this.userChecks.remove(userCheck);
        userCheck.setPost(null);
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
        Post post = (Post) o;
        if (post.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}

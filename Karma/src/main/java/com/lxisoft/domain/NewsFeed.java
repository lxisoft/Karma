package com.lxisoft.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

/**
 * NewsFeed entity
 * 
 * @author Deepthi E
 */
@ApiModel(description = "NewsFeed entity @author Deepthi E")
@Entity
@Table(name = "news_feed")
public class NewsFeed implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "jhi_date")
	private Instant date;

	@OneToMany(mappedBy = "newsFeed", fetch = FetchType.EAGER)
	private Set<Media> attachments = new HashSet<>();

	@OneToMany(mappedBy = "newsFeed")
	private Set<Comment> comments = new HashSet<>();

	@ManyToOne
	@JsonIgnoreProperties("newsFeeds")
	private LoggedUser loggedUser;

	@OneToMany(mappedBy = "newsFeed")
	private Set<UserCheck> userChecks = new HashSet<>();

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public NewsFeed description(String description) {
		this.description = description;
		return this;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getDate() {
		return date;
	}

	public NewsFeed date(Instant date) {
		this.date = date;
		return this;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Set<Media> getAttachments() {
		return attachments;
	}

	public NewsFeed attachments(Set<Media> media) {
		this.attachments = media;
		return this;
	}

	public NewsFeed addAttachments(Media media) {
		this.attachments.add(media);
		media.setNewsFeed(this);
		return this;
	}

	public NewsFeed removeAttachments(Media media) {
		this.attachments.remove(media);
		media.setNewsFeed(null);
		return this;
	}

	public void setAttachments(Set<Media> media) {
		this.attachments = media;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public NewsFeed comments(Set<Comment> comments) {
		this.comments = comments;
		return this;
	}

	public NewsFeed addComments(Comment comment) {
		this.comments.add(comment);
		comment.setNewsFeed(this);
		return this;
	}

	public NewsFeed removeComments(Comment comment) {
		this.comments.remove(comment);
		comment.setNewsFeed(null);
		return this;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public LoggedUser getLoggedUser() {
		return loggedUser;
	}

	public NewsFeed loggedUser(LoggedUser loggedUser) {
		this.loggedUser = loggedUser;
		return this;
	}

	public void setLoggedUser(LoggedUser loggedUser) {
		this.loggedUser = loggedUser;
	}

	public Set<UserCheck> getUserChecks() {
		return userChecks;
	}

	public NewsFeed userChecks(Set<UserCheck> userChecks) {
		this.userChecks = userChecks;
		return this;
	}

	public NewsFeed addUserChecks(UserCheck userCheck) {
		this.userChecks.add(userCheck);
		userCheck.setNewsFeed(this);
		return this;
	}

	public NewsFeed removeUserChecks(UserCheck userCheck) {
		this.userChecks.remove(userCheck);
		userCheck.setNewsFeed(null);
		return this;
	}

	public void setUserChecks(Set<UserCheck> userChecks) {
		this.userChecks = userChecks;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters
	// and setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		NewsFeed newsFeed = (NewsFeed) o;
		if (newsFeed.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), newsFeed.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "NewsFeed{" + "id=" + getId() + ", description='" + getDescription() + "'" + ", date='" + getDate() + "'"
				+ "}";
	}
}

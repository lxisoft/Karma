package com.lxisoft.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

/**
 * A DTO for the Post entity.
 */
public class PostDTO implements Serializable {

	private Long id;

	private String description;

	private Instant date;

	private Long registeredUserId;

	private String dateInString;

	private String postedBefore;

	private Long totalComments;

	private Long totalLikes;

	private Long totalDislikes;

	private MultipartFile[] attachedFiles;

	private Set<String> attachedFilesUrls;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Long getRegisteredUserId() {
		return registeredUserId;
	}

	public void setRegisteredUserId(Long registeredUserId) {
		this.registeredUserId = registeredUserId;
	}

	public String getDateInString() {
		return dateInString;
	}

	public void setDateInString(String dateInString) {
		this.dateInString = dateInString;
	}

	public String getPostedBefore() {
		return postedBefore;
	}

	public void setPostedBefore(String postedBefore) {
		this.postedBefore = postedBefore;
	}

	public Long getTotalComments() {
		return totalComments;
	}

	public void setTotalComments(Long totalComments) {
		this.totalComments = totalComments;
	}

	public Long getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(Long totalLikes) {
		this.totalLikes = totalLikes;
	}

	public Long getTotalDislikes() {
		return totalDislikes;
	}

	public void setTotalDislikes(Long totalDislikes) {
		this.totalDislikes = totalDislikes;
	}

	public MultipartFile[] getAttachedFiles() {
		return attachedFiles;
	}

	public void setAttachedFiles(MultipartFile[] attachedFiles) {
		this.attachedFiles = attachedFiles;
	}

	public Set<String> getAttachedFilesUrls() {
		return attachedFilesUrls;
	}

	public void setAttachedFilesUrls(Set<String> attachedFilesUrls) {
		this.attachedFilesUrls = attachedFilesUrls;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PostDTO postDTO = (PostDTO) o;
		if (postDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), postDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", description=" + description + ", date=" + date + ", registeredUserId="
				+ registeredUserId + ", dateInString=" + dateInString + ", postedBefore=" + postedBefore
				+ ", totalComments=" + totalComments + ", totalLikes=" + totalLikes + ", totalDislikes=" + totalDislikes
				+ ", attachedFilesUrls=" + attachedFilesUrls + "]";
	}

}

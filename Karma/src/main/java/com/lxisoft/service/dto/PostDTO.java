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

	private String postedUserName;

	private MultipartFile[] attachedFiles;

	private Set<String> attachedFilesUrls;

	/**
	 * @return the dateInString
	 */
	public String getDateInString() {
		return dateInString;
	}

	/**
	 * @param dateInString
	 *            the dateInString to set
	 */
	public void setDateInString(String dateInString) {
		this.dateInString = dateInString;
	}

	/**
	 * @return the postedBefore
	 */
	public String getPostedBefore() {
		return postedBefore;
	}

	/**
	 * @param postedBefore
	 *            the postedBefore to set
	 */
	public void setPostedBefore(String postedBefore) {
		this.postedBefore = postedBefore;
	}

	/**
	 * @return the totalComments
	 */
	public Long getTotalComments() {
		return totalComments;
	}

	/**
	 * @param totalComments
	 *            the totalComments to set
	 */
	public void setTotalComments(Long totalComments) {
		this.totalComments = totalComments;
	}

	/**
	 * @return the totalLikes
	 */
	public Long getTotalLikes() {
		return totalLikes;
	}

	/**
	 * @param totalLikes
	 *            the totalLikes to set
	 */
	public void setTotalLikes(Long totalLikes) {
		this.totalLikes = totalLikes;
	}

	/**
	 * @return the totalDislikes
	 */
	public Long getTotalDislikes() {
		return totalDislikes;
	}

	/**
	 * @param totalDislikes
	 *            the totalDislikes to set
	 */
	public void setTotalDislikes(Long totalDislikes) {
		this.totalDislikes = totalDislikes;
	}

	/**
	 * @return the attachedFiles
	 */
	public MultipartFile[] getAttachedFiles() {
		return attachedFiles;
	}

	/**
	 * @param attachedFiles
	 *            the attachedFiles to set
	 */
	public void setAttachedFiles(MultipartFile[] attachedFiles) {
		this.attachedFiles = attachedFiles;
	}

	public String getPostedUserName() {
		return postedUserName;
	}

	public void setPostedUserName(String postedUserName) {
		this.postedUserName = postedUserName;
	}

	public Set<String> getAttachedFilesUrls() {
		return attachedFilesUrls;
	}

	/**
	 * @param attachedFilesUrls
	 *            the attachedFilesUrls to set
	 */
	public void setAttachedFilesUrls(Set<String> attachedFilesUrls) {
		this.attachedFilesUrls = attachedFilesUrls;
	}

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
		return "PostDTO{" + "id=" + getId() + ", description='" + getDescription() + "'" + ", date='" + getDate() + "'"
				+ ", registeredUser=" + getRegisteredUserId() + "}";
	}
}

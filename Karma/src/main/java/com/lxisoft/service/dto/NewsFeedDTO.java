package com.lxisoft.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

/**
 * A DTO for the NewsFeed entity.
 */
public class NewsFeedDTO implements Serializable {

	private Long id;

	private String description;

	private Instant date;

	private Long loggedUserId;

	private String dateInString;

	private String postedBefore;

	private MultipartFile[] attachedFiles;

	private Set<String> attachedFilesUrls;

	public Set<String> getAttachedFilesUrls() {
		return attachedFilesUrls;
	}

	public void setAttachedFilesUrls(Set<String> attachedFilesUrls) {
		this.attachedFilesUrls = attachedFilesUrls;
	}

	public MultipartFile[] getAttachedFiles() {
		return attachedFiles;
	}

	public void setAttachedFiles(MultipartFile[] attachedFiles) {
		this.attachedFiles = attachedFiles;
	}

	public String[] getAttachedFilesURLs() {
		return attachedFilesURLs;
	}

	public void setAttachedFilesURLs(String[] attachedFilesURLs) {
		this.attachedFilesURLs = attachedFilesURLs;
	}

	private String[] attachedFilesURLs;

	public String getPostedBefore() {
		return postedBefore;
	}

	public void setPostedBefore(String postedBefore) {
		this.postedBefore = postedBefore;
	}

	public void setDateInString(String dateInString) {
		this.dateInString = dateInString;
	}

	public String getDateInString() {
		return dateInString;
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

	public Long getLoggedUserId() {
		return loggedUserId;
	}

	public void setLoggedUserId(Long loggedUserId) {
		this.loggedUserId = loggedUserId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		NewsFeedDTO newsFeedDTO = (NewsFeedDTO) o;
		if (newsFeedDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), newsFeedDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return String.format(
				"NewsFeedDTO [id=%s, description=%s, date=%s, loggedUserId=%s, dateInString=%s, postedBefore=%s]", id,
				description, date, loggedUserId, dateInString, postedBefore);
	}

}

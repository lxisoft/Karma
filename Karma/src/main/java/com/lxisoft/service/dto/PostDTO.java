package com.lxisoft.service.dto;

import java.time.Instant;
import java.io.Serializable;
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
    
    private String timeInString;

	private String timeElapsed;

	private Long noOfComments;

	private Long noOfLikes;

	private Long noOfDislikes;

	private MultipartFile[] attachedFiles;

	private Set<String> attachedFilesUrls;
	
	private String userName;
	

	/**
	 * @return the timeInString
	 */
	public String getTimeInString() {
		return timeInString;
	}

	/**
	 * @param timeInString the timeInString to set
	 */
	public void setTimeInString(String timeInString) {
		this.timeInString = timeInString;
	}

	/**
	 * @return the timeElapsed
	 */
	public String getTimeElapsed() {
		return timeElapsed;
	}

	/**
	 * @param timeElapsed the timeElapsed to set
	 */
	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	/**
	 * @return the noOfComments
	 */
	public Long getNoOfComments() {
		return noOfComments;
	}

	/**
	 * @param noOfComments the noOfComments to set
	 */
	public void setNoOfComments(Long noOfComments) {
		this.noOfComments = noOfComments;
	}

	/**
	 * @return the noOfLikes
	 */
	public Long getNoOfLikes() {
		return noOfLikes;
	}

	/**
	 * @param noOfLikes the noOfLikes to set
	 */
	public void setNoOfLikes(Long noOfLikes) {
		this.noOfLikes = noOfLikes;
	}

	/**
	 * @return the noOfDislikes
	 */
	public Long getNoOfDislikes() {
		return noOfDislikes;
	}

	/**
	 * @param noOfDislikes the noOfDislikes to set
	 */
	public void setNoOfDislikes(Long noOfDislikes) {
		this.noOfDislikes = noOfDislikes;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the attachedFiles
	 */
	public MultipartFile[] getAttachedFiles() {
		return attachedFiles;
	}

	/**
	 * @param attachedFiles the attachedFiles to set
	 */
	public void setAttachedFiles(MultipartFile[] attachedFiles) {
		this.attachedFiles = attachedFiles;
	}

	/**
	 * @return the attachedFilesUrls
	 */
	public Set<String> getAttachedFilesUrls() {
		return attachedFilesUrls;
	}

	/**
	 * @param attachedFilesUrls the attachedFilesUrls to set
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
        return "PostDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", date='" + getDate() + "'" +
            ", registeredUser=" + getRegisteredUserId() +
            "}";
    }
}

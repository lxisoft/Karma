package com.lxisoft.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * A DTO for the Help entity.
 */
public class HelpDTO implements Serializable {

    private Long id;

    private Instant time;

    private String description;

    private Long approvalStatusId;

    private Long providedUserId;

    private Long fulfilledNeedId;
    
    private String timeInString;
    
    private Long noOfComments;
    
    private MultipartFile[] files;
    
    private List<Resource> resourceFiles;
    
    private String timeElapsed;
    
    private Long noOfLikes;
    
    private Long noOfDisLikes;
    
    
    
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
	 * @return the noOfDisLikes
	 */
	public Long getNoOfDisLikes() {
		return noOfDisLikes;
	}

	/**
	 * @param noOfDisLikes the noOfDisLikes to set
	 */
	public void setNoOfDisLikes(Long noOfDisLikes) {
		this.noOfDisLikes = noOfDisLikes;
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
	 * @return the files
	 */
	public MultipartFile[] getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	/**
	 * @return the resourceFiles
	 */
	public List<Resource> getResourceFiles() {
		return resourceFiles;
	}

	/**
	 * @param resourceFiles the resourceFiles to set
	 */
	public void setResourceFiles(List<Resource> resourceFiles) {
		this.resourceFiles = resourceFiles;
	}

	

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

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getApprovalStatusId() {
        return approvalStatusId;
    }

    public void setApprovalStatusId(Long approvalStatusId) {
        this.approvalStatusId = approvalStatusId;
    }

    public Long getProvidedUserId() {
        return providedUserId;
    }

    public void setProvidedUserId(Long registeredUserId) {
        this.providedUserId = registeredUserId;
    }

    public Long getFulfilledNeedId() {
        return fulfilledNeedId;
    }

    public void setFulfilledNeedId(Long needId) {
        this.fulfilledNeedId = needId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HelpDTO helpDTO = (HelpDTO) o;
        if (helpDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), helpDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HelpDTO{" +
            "id=" + getId() +
            ", time='" + getTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", approvalStatus=" + getApprovalStatusId() +
            ", providedUser=" + getProvidedUserId() +
            ", fulfilledNeed=" + getFulfilledNeedId() +
            "}";
    }
}

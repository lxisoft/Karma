package com.lxisoft.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * A DTO for the Need entity.
 */
public class NeedDTO implements Serializable {

    private Long id;

    private String description;

    private String beneficiaryType;

    private Instant date;

    private Long severityId;

    private Long verificationTeamId;

    private Long approvalStatusId;

    private Set<CategoryDTO> categories = new HashSet<>();

    private Long postedUserId;
    
    private List<CategoryDTO> categoryList = new ArrayList<>();

    private String dateInString;

    private Long percentageOfGenuineness;
    
    private List<String> fileNameList=new ArrayList<>();
    
    private String timeElapsed;
    
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
	 * @return the commentList
	 */
	public List<CommentDTO> getCommentList() {
		return commentList;
	}

	/**
	 * @param commentList the commentList to set
	 */
	public void setCommentList(List<CommentDTO> commentList) {
		this.commentList = commentList;
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
	 * @return the noOfHelps
	 */
	public Long getNoOfHelps() {
		return noOfHelps;
	}

	/**
	 * @param noOfHelps the noOfHelps to set
	 */
	public void setNoOfHelps(Long noOfHelps) {
		this.noOfHelps = noOfHelps;
	}

	/**
	 * @return the severityLevel
	 */
	public String getSeverityLevel() {
		return severityLevel;
	}

	/**
	 * @param severityLevel the severityLevel to set
	 */
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
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

	private List<CommentDTO> commentList=new ArrayList<>();
    
    private Long noOfComments;
    
    private Long noOfHelps;
    
    private String severityLevel;
    
    private MultipartFile[] files;
    
    private List<Resource> resourceFiles;
    
    //private Resource File;

    /**
	 * @return the categoryList
	 */
	public List<CategoryDTO> getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList the categoryList to set
	 */
	public void setCategoryList(List<CategoryDTO> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * @return the dateInString
	 */
	public String getDateInString() {
		return dateInString;
	}

	/**
	 * @param dateInString the dateInString to set
	 */
	public void setDateInString(String dateInString) {
		this.dateInString = dateInString;
	}

	/**
	 * @return the percentageOfGenuineness
	 */
	public Long getPercentageOfGenuineness() {
		return percentageOfGenuineness;
	}

	/**
	 * @param percentageOfGenuineness the percentageOfGenuineness to set
	 */
	public void setPercentageOfGenuineness(Long percentageOfGenuineness) {
		this.percentageOfGenuineness = percentageOfGenuineness;
	}

	/**
	 * @return the fileNameList
	 */
	public List<String> getFileNameList() {
		return fileNameList;
	}

	/**
	 * @param fileNameList the fileNameList to set
	 */
	public void setFileNameList(List<String> fileNameList) {
		this.fileNameList = fileNameList;
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

    public String getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(String beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getSeverityId() {
        return severityId;
    }

    public void setSeverityId(Long severityId) {
        this.severityId = severityId;
    }

    public Long getVerificationTeamId() {
        return verificationTeamId;
    }

    public void setVerificationTeamId(Long verificationTeamId) {
        this.verificationTeamId = verificationTeamId;
    }

    public Long getApprovalStatusId() {
        return approvalStatusId;
    }

    public void setApprovalStatusId(Long approvalStatusId) {
        this.approvalStatusId = approvalStatusId;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    public Long getPostedUserId() {
        return postedUserId;
    }

    public void setPostedUserId(Long registeredUserId) {
        this.postedUserId = registeredUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NeedDTO needDTO = (NeedDTO) o;
        if (needDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), needDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NeedDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", beneficiaryType='" + getBeneficiaryType() + "'" +
            ", date='" + getDate() + "'" +
            ", severity=" + getSeverityId() +
            ", verificationTeam=" + getVerificationTeamId() +
            ", approvalStatus=" + getApprovalStatusId() +
            ", postedUser=" + getPostedUserId() +
            "}";
    }
}

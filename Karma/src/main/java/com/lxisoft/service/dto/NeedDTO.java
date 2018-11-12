package com.lxisoft.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
    private String timeElapsed;
    
    private List<String> fileNameList=new ArrayList<>();
    
    private MultipartFile[] files;
    
    private List<CommentDTO> commentList=new ArrayList<>();
    
    private Long noOfComments;
    
    
    
    public void setNoOfComments(Long noOfComments)
    {
    	
    	this.noOfComments=noOfComments;
    }
    
    public Long getNoOfComments()
    {
    	return noOfComments;
    }
    
    public void  setCommentList(List<CommentDTO> commentList)
    {
    	this.commentList=commentList;
    }
    
    public List<CommentDTO> getCommentList()
    {
    	return commentList;
    }

    public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
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

    public void setPostedUserId(Long loggedUserId) {
        this.postedUserId = loggedUserId;
    }
    
    
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

	public String getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	
}

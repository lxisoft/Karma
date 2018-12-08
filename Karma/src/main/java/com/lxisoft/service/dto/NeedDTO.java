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

    private Long personInChargeId;

    private Set<CategoryDTO> categories = new HashSet<>();

    private Long postedUserId;
    
    private List<CategoryDTO> categoryList = new ArrayList<>();

    private String dateInString;

    private Long percentageOfGenuineness;
    
    private List<String> attachmentUrls=new ArrayList<>();
    
    private String timeElapsed;
           
    private Long noOfComments;
    
    private Long noOfHelps;
    
    private String severityLevel;
        
    private Long noOfRecommendations;
    
    private String userName;
      
    
    public Long getId() {
        return id;
    }

   
	public void setId(Long id) {
        this.id = id;
    }

	
    /**
	 * @return the attachmentUrls
	 */
	public List<String> getAttachmentUrls() {
		return attachmentUrls;
	}


	/**
	 * @param attachmentUrls the attachmentUrls to set
	 */
	public void setAttachmentUrls(List<String> attachmentUrls) {
		this.attachmentUrls = attachmentUrls;
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

    public Long getPersonInChargeId() {
        return personInChargeId;
    }

    public void setPersonInChargeId(Long registeredUserId) {
        this.personInChargeId = registeredUserId;
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
	 * @return the noOfRecommendations
	 */
	public Long getNoOfRecommendations() {
		return noOfRecommendations;
	}

	/**
	 * @param noOfRecommendations the noOfRecommendations to set
	 */
	public void setNoOfRecommendations(Long noOfRecommendations) {
		this.noOfRecommendations = noOfRecommendations;
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
            ", personInCharge=" + getPersonInChargeId() +
            ", postedUser=" + getPostedUserId() +
            "}";
    }
}

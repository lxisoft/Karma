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
 * A DTO for the Help entity.
 */
public class HelpDTO implements Serializable {

    private Long id;

    private Instant time;

    private String description;

    private Long approvalStatusId;

    private Long providedUserId;

    private Long fulfilledNeedId;
    
    private Long noOfComments;

    private String timeInString;

    private String timeElapsed;



    
    
    public Long getNoOfComments() {
		return noOfComments;
	}

	public void setNoOfComments(Long noOfComments) {
		this.noOfComments = noOfComments;
	}

	private MultipartFile[] files;

    private List<String> fileNameList=new ArrayList<>();

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

    public void setProvidedUserId(Long loggedUserId) {
        this.providedUserId = loggedUserId;
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
        if(helpDTO.getId() == null || getId() == null) {
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
            "}";
    }

	public String getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	
}

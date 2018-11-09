package com.lxisoft.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

/**
 * A DTO for the Violation entity.
 */
public class ViolationDTO implements Serializable {

    private Long id;

    private String description;

    private Boolean isAnonymous;

    private Instant date;
    
    private String dateInString;
    
    private MultipartFile[] files;
    
    private Long numberOfViolationSupporters;
    
	private Long numberOfViolationUnSupporters;

    public Long getNumberOfViolationSupporters() {
		return numberOfViolationSupporters;
	}

	public void setNumberOfViolationSupporters(Long numberOfViolationSupporters) {
		this.numberOfViolationSupporters = numberOfViolationSupporters;
	}

	public Long getNumberOfViolationUnSupporters() {
		return numberOfViolationUnSupporters;
	}

	public void setNumberOfViolationUnSupporters(Long numberOfViolationUnSupporters) {
		this.numberOfViolationUnSupporters = numberOfViolationUnSupporters;
	}    

    public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public Long getId() {
        return id;
    }

    public String getDateInString() {
		return dateInString;
	}

	public void setDateInString(String dateInString) {
		this.dateInString = dateInString;
	}

	public Boolean getIsAnonymous() {
		return isAnonymous;
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

    public Boolean isIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ViolationDTO violationDTO = (ViolationDTO) o;
        if (violationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), violationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ViolationDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", isAnonymous='" + isIsAnonymous() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}

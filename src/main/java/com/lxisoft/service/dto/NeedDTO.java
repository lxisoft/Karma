package com.lxisoft.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NeedDTO needDTO = (NeedDTO) o;
        if(needDTO.getId() == null || getId() == null) {
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
            "}";
    }
}

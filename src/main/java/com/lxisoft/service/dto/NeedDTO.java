package com.lxisoft.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

	private List<CategoryDTO> categoryList = new ArrayList<>();

	private String dateInString;

	//

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
		return "NeedDTO{" + "id=" + getId() + ", description='" + getDescription() + "'" + ", beneficiaryType='"
				+ getBeneficiaryType() + "'" + ", date='" + getDate() + "'" + ", severity=" + getSeverityId()
				+ ", verificationTeam=" + getVerificationTeamId() + ", approvalStatus=" + getApprovalStatusId()
				+ ", postedUser=" + getPostedUserId() + ", categories" + getCategories().toString() + "}";
	}

	public List<CategoryDTO> getCategoriesAsList() {
		return new ArrayList<CategoryDTO>(categories);
	}

	public void setCategoriesAsList(List<CategoryDTO> categories) {

		this.categories.clear();
		for (CategoryDTO category : categories) {
			this.categories.add(category);
		}

	}

	/**
	 * @return the categoryList
	 */
	public List<CategoryDTO> getCategoryList() {
		return this.categoryList;
	}

	/**
	 * @param cats
	 *            the categoryList to set
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

}

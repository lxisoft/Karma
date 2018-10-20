package com.lxisoft.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UserCheck entity.
 */
public class UserCheckDTO implements Serializable {

    private Long id;

    private String voteType;

    private String category;

    private Long checkedNeedId;

    public Boolean getIsGenuine() {
		return isGenuine;
	}

	public void setIsGenuine(Boolean isGenuine) {
		this.isGenuine = isGenuine;
	}

	private Long checkedUserId;
 
    private Boolean isGenuine;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoteType() {
        return voteType;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCheckedNeedId() {
        return checkedNeedId;
    }

    public void setCheckedNeedId(Long needId) {
        this.checkedNeedId = needId;
    }

    public Long getCheckedUserId() {
        return checkedUserId;
    }

    public void setCheckedUserId(Long loggedUserId) {
        this.checkedUserId = loggedUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserCheckDTO userCheckDTO = (UserCheckDTO) o;
        if (userCheckDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userCheckDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserCheckDTO{" +
            "id=" + getId() +
            ", voteType='" + getVoteType() + "'" +
            ", category='" + getCategory() + "'" +
            ", checkedNeed=" + getCheckedNeedId() +
            ", checkedUser=" + getCheckedUserId() +
            "}";
    }
}

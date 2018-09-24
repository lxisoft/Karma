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

    private Long markedUserId;

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

    public Long getMarkedUserId() {
        return markedUserId;
    }

    public void setMarkedUserId(Long needId) {
        this.markedUserId = needId;
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
            ", markedUser=" + getMarkedUserId() +
            "}";
    }
}

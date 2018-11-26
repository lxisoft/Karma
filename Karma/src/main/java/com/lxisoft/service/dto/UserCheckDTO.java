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

    private Long checkedUserId;

    private Long commentId;

    private Long replyId;

    private Long postId;

    /**
	 * @return the isGenuine
	 */
	public Boolean getIsGenuine() {
		return isGenuine;
	}

	/**
	 * @param isGenuine the isGenuine to set
	 */
	public void setIsGenuine(Boolean isGenuine) {
		this.isGenuine = isGenuine;
	}

	private Long checkedHelpId;
    
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

    public void setCheckedUserId(Long registeredUserId) {
        this.checkedUserId = registeredUserId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCheckedHelpId() {
        return checkedHelpId;
    }

    public void setCheckedHelpId(Long helpId) {
        this.checkedHelpId = helpId;
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
            ", comment=" + getCommentId() +
            ", reply=" + getReplyId() +
            ", post=" + getPostId() +
            ", checkedHelp=" + getCheckedHelpId() +
            "}";
    }
}

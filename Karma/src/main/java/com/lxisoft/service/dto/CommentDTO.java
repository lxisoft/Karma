package com.lxisoft.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Comment entity.
 */
public class CommentDTO implements Serializable {

    private Long id;

    private String message;

    private Instant date;

    private Long needId;

    private Long helpId;

    private Long postId;

    private Long commentedUserId;
    
    private String dateInString;
    
    private Long noOfReplies;

    private String timeElapsed;
    
    private Long noOfLikes;
    
    private Long noOfDislikes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getNeedId() {
        return needId;
    }

    public void setNeedId(Long needId) {
        this.needId = needId;
    }

    public Long getHelpId() {
        return helpId;
    }

    public void setHelpId(Long helpId) {
        this.helpId = helpId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCommentedUserId() {
        return commentedUserId;
    }

    public void setCommentedUserId(Long registeredUserId) {
        this.commentedUserId = registeredUserId;
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
	 * @return the noOfReplies
	 */
	public Long getNoOfReplies() {
		return noOfReplies;
	}

	/**
	 * @param noOfReplies the noOfReplies to set
	 */
	public void setNoOfReplies(Long noOfReplies) {
		this.noOfReplies = noOfReplies;
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
	 * @return the noOfLikes
	 */
	public Long getNoOfLikes() {
		return noOfLikes;
	}

	/**
	 * @param noOfLikes the noOfLikes to set
	 */
	public void setNoOfLikes(Long noOfLikes) {
		this.noOfLikes = noOfLikes;
	}

	/**
	 * @return the noOfDislikes
	 */
	public Long getNoOfDislikes() {
		return noOfDislikes;
	}

	/**
	 * @param noOfDislikes the noOfDislikes to set
	 */
	public void setNoOfDislikes(Long noOfDislikes) {
		this.noOfDislikes = noOfDislikes;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommentDTO commentDTO = (CommentDTO) o;
        if (commentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", date='" + getDate() + "'" +
            ", need=" + getNeedId() +
            ", help=" + getHelpId() +
            ", post=" + getPostId() +
            ", commentedUser=" + getCommentedUserId() +
            "}";
    }
}

package com.lxisoft.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Reply entity.
 */
public class ReplyDTO implements Serializable {

    private Long id;

    private String message;

    private Instant date;

    private Long commentId;

    private Long repliedUserId;
    
    private String dateInString;
    
    private String timeElapsed;
    
    private Long noOfLikes;
    
    private Long noOfDislikes;
    
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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getRepliedUserId() {
        return repliedUserId;
    }

    public void setRepliedUserId(Long registeredUserId) {
        this.repliedUserId = registeredUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReplyDTO replyDTO = (ReplyDTO) o;
        if (replyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), replyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReplyDTO{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", date='" + getDate() + "'" +
            ", comment=" + getCommentId() +
            ", repliedUser=" + getRepliedUserId() +
            "}";
    }
}

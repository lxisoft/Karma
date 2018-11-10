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

    private Long newsFeedId;

    private Long commentedUserId;

    private Long violationId;
    
    private String dateInString;
    
    private String timeElapsed;

    
    private Long noOfLikes;
    
    private Long noOfDislikes;
    
    public void setNoOfLikes(Long noOfLikes){
    	
    	this.noOfLikes=noOfLikes;
    }
    
    public Long getNoOfLikes()
    {
    	return noOfLikes;
    }
    
 public void setNoOfDislikes(Long noOfDislikes){
    	
    	this.noOfDislikes=noOfDislikes;
    }
    
    public Long getNoOfDislikes()
    {
    	return noOfDislikes;
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

    public Long getNewsFeedId() {
        return newsFeedId;
    }

    public void setNewsFeedId(Long newsFeedId) {
        this.newsFeedId = newsFeedId;
    }

    public Long getCommentedUserId() {
        return commentedUserId;
    }

    public void setCommentedUserId(Long loggedUserId) {
        this.commentedUserId = loggedUserId;
    }

    public Long getViolationId() {
        return violationId;
    }

    public void setViolationId(Long violationId) {
        this.violationId = violationId;
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
            ", newsFeed=" + getNewsFeedId() +
            ", commentedUser=" + getCommentedUserId() +
            ", violation=" + getViolationId() +
            "}";
    }

	public String getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
}

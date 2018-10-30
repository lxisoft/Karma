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

    private Long violationId;

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

    public Long getViolationId() {
        return violationId;
    }

    public void setViolationId(Long violationId) {
        this.violationId = violationId;
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
            ", violation=" + getViolationId() +
            "}";
    }
}

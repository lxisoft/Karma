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
            "}";
    }
}

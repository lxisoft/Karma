package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ReplyDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")

public class ReplyDTO   {
  @JsonProperty("commentId")
  private Long commentId = null;

  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("dateInString")
  private String dateInString = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("repliedUserId")
  private Long repliedUserId = null;

  public ReplyDTO commentId(Long commentId) {
    this.commentId = commentId;
    return this;
  }

  /**
   * Get commentId
   * @return commentId
  **/
  @ApiModelProperty(value = "")


  public Long getCommentId() {
    return commentId;
  }

  public void setCommentId(Long commentId) {
    this.commentId = commentId;
  }

  public ReplyDTO date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public ReplyDTO dateInString(String dateInString) {
    this.dateInString = dateInString;
    return this;
  }

  /**
   * Get dateInString
   * @return dateInString
  **/
  @ApiModelProperty(value = "")


  public String getDateInString() {
    return dateInString;
  }

  public void setDateInString(String dateInString) {
    this.dateInString = dateInString;
  }

  public ReplyDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ReplyDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(value = "")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ReplyDTO repliedUserId(Long repliedUserId) {
    this.repliedUserId = repliedUserId;
    return this;
  }

  /**
   * Get repliedUserId
   * @return repliedUserId
  **/
  @ApiModelProperty(value = "")


  public Long getRepliedUserId() {
    return repliedUserId;
  }

  public void setRepliedUserId(Long repliedUserId) {
    this.repliedUserId = repliedUserId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReplyDTO replyDTO = (ReplyDTO) o;
    return Objects.equals(this.commentId, replyDTO.commentId) &&
        Objects.equals(this.date, replyDTO.date) &&
        Objects.equals(this.dateInString, replyDTO.dateInString) &&
        Objects.equals(this.id, replyDTO.id) &&
        Objects.equals(this.message, replyDTO.message) &&
        Objects.equals(this.repliedUserId, replyDTO.repliedUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentId, date, dateInString, id, message, repliedUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReplyDTO {\n");
    
    sb.append("    commentId: ").append(toIndentedString(commentId)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    dateInString: ").append(toIndentedString(dateInString)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    repliedUserId: ").append(toIndentedString(repliedUserId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


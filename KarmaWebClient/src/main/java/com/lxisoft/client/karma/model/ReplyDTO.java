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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-01-15T13:59:42.068056700+05:30[Asia/Calcutta]")

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

  @JsonProperty("noOfDislikes")
  private Long noOfDislikes = null;

  @JsonProperty("noOfLikes")
  private Long noOfLikes = null;

  @JsonProperty("repliedUserId")
  private Long repliedUserId = null;

  @JsonProperty("timeElapsed")
  private String timeElapsed = null;

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

  public ReplyDTO noOfDislikes(Long noOfDislikes) {
    this.noOfDislikes = noOfDislikes;
    return this;
  }

  /**
   * Get noOfDislikes
   * @return noOfDislikes
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfDislikes() {
    return noOfDislikes;
  }

  public void setNoOfDislikes(Long noOfDislikes) {
    this.noOfDislikes = noOfDislikes;
  }

  public ReplyDTO noOfLikes(Long noOfLikes) {
    this.noOfLikes = noOfLikes;
    return this;
  }

  /**
   * Get noOfLikes
   * @return noOfLikes
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfLikes() {
    return noOfLikes;
  }

  public void setNoOfLikes(Long noOfLikes) {
    this.noOfLikes = noOfLikes;
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

  public ReplyDTO timeElapsed(String timeElapsed) {
    this.timeElapsed = timeElapsed;
    return this;
  }

  /**
   * Get timeElapsed
   * @return timeElapsed
  **/
  @ApiModelProperty(value = "")


  public String getTimeElapsed() {
    return timeElapsed;
  }

  public void setTimeElapsed(String timeElapsed) {
    this.timeElapsed = timeElapsed;
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
        Objects.equals(this.noOfDislikes, replyDTO.noOfDislikes) &&
        Objects.equals(this.noOfLikes, replyDTO.noOfLikes) &&
        Objects.equals(this.repliedUserId, replyDTO.repliedUserId) &&
        Objects.equals(this.timeElapsed, replyDTO.timeElapsed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentId, date, dateInString, id, message, noOfDislikes, noOfLikes, repliedUserId, timeElapsed);
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
    sb.append("    noOfDislikes: ").append(toIndentedString(noOfDislikes)).append("\n");
    sb.append("    noOfLikes: ").append(toIndentedString(noOfLikes)).append("\n");
    sb.append("    repliedUserId: ").append(toIndentedString(repliedUserId)).append("\n");
    sb.append("    timeElapsed: ").append(toIndentedString(timeElapsed)).append("\n");
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


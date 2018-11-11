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
 * CommentDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T16:15:16.814+05:30[Asia/Calcutta]")

public class CommentDTO   {
  @JsonProperty("commentedUserId")
  private Long commentedUserId = null;

  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("dateInString")
  private String dateInString = null;

  @JsonProperty("helpId")
  private Long helpId = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("needId")
  private Long needId = null;

  @JsonProperty("newsFeedId")
  private Long newsFeedId = null;

  @JsonProperty("violationId")
  private Long violationId = null;

  public CommentDTO commentedUserId(Long commentedUserId) {
    this.commentedUserId = commentedUserId;
    return this;
  }

  /**
   * Get commentedUserId
   * @return commentedUserId
  **/
  @ApiModelProperty(value = "")


  public Long getCommentedUserId() {
    return commentedUserId;
  }

  public void setCommentedUserId(Long commentedUserId) {
    this.commentedUserId = commentedUserId;
  }

  public CommentDTO date(OffsetDateTime date) {
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

  public CommentDTO dateInString(String dateInString) {
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

  public CommentDTO helpId(Long helpId) {
    this.helpId = helpId;
    return this;
  }

  /**
   * Get helpId
   * @return helpId
  **/
  @ApiModelProperty(value = "")


  public Long getHelpId() {
    return helpId;
  }

  public void setHelpId(Long helpId) {
    this.helpId = helpId;
  }

  public CommentDTO id(Long id) {
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

  public CommentDTO message(String message) {
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

  public CommentDTO needId(Long needId) {
    this.needId = needId;
    return this;
  }

  /**
   * Get needId
   * @return needId
  **/
  @ApiModelProperty(value = "")


  public Long getNeedId() {
    return needId;
  }

  public void setNeedId(Long needId) {
    this.needId = needId;
  }

  public CommentDTO newsFeedId(Long newsFeedId) {
    this.newsFeedId = newsFeedId;
    return this;
  }

  /**
   * Get newsFeedId
   * @return newsFeedId
  **/
  @ApiModelProperty(value = "")


  public Long getNewsFeedId() {
    return newsFeedId;
  }

  public void setNewsFeedId(Long newsFeedId) {
    this.newsFeedId = newsFeedId;
  }

  public CommentDTO violationId(Long violationId) {
    this.violationId = violationId;
    return this;
  }

  /**
   * Get violationId
   * @return violationId
  **/
  @ApiModelProperty(value = "")


  public Long getViolationId() {
    return violationId;
  }

  public void setViolationId(Long violationId) {
    this.violationId = violationId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentDTO commentDTO = (CommentDTO) o;
    return Objects.equals(this.commentedUserId, commentDTO.commentedUserId) &&
        Objects.equals(this.date, commentDTO.date) &&
        Objects.equals(this.dateInString, commentDTO.dateInString) &&
        Objects.equals(this.helpId, commentDTO.helpId) &&
        Objects.equals(this.id, commentDTO.id) &&
        Objects.equals(this.message, commentDTO.message) &&
        Objects.equals(this.needId, commentDTO.needId) &&
        Objects.equals(this.newsFeedId, commentDTO.newsFeedId) &&
        Objects.equals(this.violationId, commentDTO.violationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentedUserId, date, dateInString, helpId, id, message, needId, newsFeedId, violationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentDTO {\n");
    
    sb.append("    commentedUserId: ").append(toIndentedString(commentedUserId)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    dateInString: ").append(toIndentedString(dateInString)).append("\n");
    sb.append("    helpId: ").append(toIndentedString(helpId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    needId: ").append(toIndentedString(needId)).append("\n");
    sb.append("    newsFeedId: ").append(toIndentedString(newsFeedId)).append("\n");
    sb.append("    violationId: ").append(toIndentedString(violationId)).append("\n");
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


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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-28T11:53:04.824+05:30[Asia/Calcutta]")

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

  @JsonProperty("noOfDislikes")
  private Long noOfDislikes = null;

  @JsonProperty("noOfLikes")
  private Long noOfLikes = null;

  @JsonProperty("noOfReplies")
  private Long noOfReplies = null;

  @JsonProperty("postId")
  private Long postId = null;

  @JsonProperty("timeElapsed")
  private String timeElapsed = null;

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

  public CommentDTO noOfDislikes(Long noOfDislikes) {
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

  public CommentDTO noOfLikes(Long noOfLikes) {
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

  public CommentDTO noOfReplies(Long noOfReplies) {
    this.noOfReplies = noOfReplies;
    return this;
  }

  /**
   * Get noOfReplies
   * @return noOfReplies
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfReplies() {
    return noOfReplies;
  }

  public void setNoOfReplies(Long noOfReplies) {
    this.noOfReplies = noOfReplies;
  }

  public CommentDTO postId(Long postId) {
    this.postId = postId;
    return this;
  }

  /**
   * Get postId
   * @return postId
  **/
  @ApiModelProperty(value = "")


  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public CommentDTO timeElapsed(String timeElapsed) {
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
    CommentDTO commentDTO = (CommentDTO) o;
    return Objects.equals(this.commentedUserId, commentDTO.commentedUserId) &&
        Objects.equals(this.date, commentDTO.date) &&
        Objects.equals(this.dateInString, commentDTO.dateInString) &&
        Objects.equals(this.helpId, commentDTO.helpId) &&
        Objects.equals(this.id, commentDTO.id) &&
        Objects.equals(this.message, commentDTO.message) &&
        Objects.equals(this.needId, commentDTO.needId) &&
        Objects.equals(this.noOfDislikes, commentDTO.noOfDislikes) &&
        Objects.equals(this.noOfLikes, commentDTO.noOfLikes) &&
        Objects.equals(this.noOfReplies, commentDTO.noOfReplies) &&
        Objects.equals(this.postId, commentDTO.postId) &&
        Objects.equals(this.timeElapsed, commentDTO.timeElapsed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentedUserId, date, dateInString, helpId, id, message, needId, noOfDislikes, noOfLikes, noOfReplies, postId, timeElapsed);
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
    sb.append("    noOfDislikes: ").append(toIndentedString(noOfDislikes)).append("\n");
    sb.append("    noOfLikes: ").append(toIndentedString(noOfLikes)).append("\n");
    sb.append("    noOfReplies: ").append(toIndentedString(noOfReplies)).append("\n");
    sb.append("    postId: ").append(toIndentedString(postId)).append("\n");
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


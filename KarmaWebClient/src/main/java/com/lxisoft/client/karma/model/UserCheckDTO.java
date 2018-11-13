package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserCheckDTO
 */
@Validated
<<<<<<< HEAD
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-09T14:03:13.373+05:30[Asia/Calcutta]")
=======
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3

public class UserCheckDTO   {
  @JsonProperty("category")
  private String category = null;

  @JsonProperty("checkedHelpId")
  private Long checkedHelpId = null;

  @JsonProperty("checkedNeedId")
  private Long checkedNeedId = null;

  @JsonProperty("checkedUserId")
  private Long checkedUserId = null;

  @JsonProperty("commentId")
  private Long commentId = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("isGenuine")
  private Boolean isGenuine = null;

  @JsonProperty("newsFeedId")
  private Long newsFeedId = null;

  @JsonProperty("replyId")
  private Long replyId = null;

  @JsonProperty("violationId")
  private Long violationId = null;

  @JsonProperty("voteType")
  private String voteType = null;

  public UserCheckDTO category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(value = "")


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public UserCheckDTO checkedHelpId(Long checkedHelpId) {
    this.checkedHelpId = checkedHelpId;
    return this;
  }

  /**
   * Get checkedHelpId
   * @return checkedHelpId
  **/
  @ApiModelProperty(value = "")


  public Long getCheckedHelpId() {
    return checkedHelpId;
  }

  public void setCheckedHelpId(Long checkedHelpId) {
    this.checkedHelpId = checkedHelpId;
  }

  public UserCheckDTO checkedNeedId(Long checkedNeedId) {
    this.checkedNeedId = checkedNeedId;
    return this;
  }

  /**
   * Get checkedNeedId
   * @return checkedNeedId
  **/
  @ApiModelProperty(value = "")


  public Long getCheckedNeedId() {
    return checkedNeedId;
  }

  public void setCheckedNeedId(Long checkedNeedId) {
    this.checkedNeedId = checkedNeedId;
  }

  public UserCheckDTO checkedUserId(Long checkedUserId) {
    this.checkedUserId = checkedUserId;
    return this;
  }

  /**
   * Get checkedUserId
   * @return checkedUserId
  **/
  @ApiModelProperty(value = "")


  public Long getCheckedUserId() {
    return checkedUserId;
  }

  public void setCheckedUserId(Long checkedUserId) {
    this.checkedUserId = checkedUserId;
  }

  public UserCheckDTO commentId(Long commentId) {
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

  public UserCheckDTO id(Long id) {
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

  public UserCheckDTO isGenuine(Boolean isGenuine) {
    this.isGenuine = isGenuine;
    return this;
  }

  /**
   * Get isGenuine
   * @return isGenuine
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsGenuine() {
    return isGenuine;
  }

  public void setIsGenuine(Boolean isGenuine) {
    this.isGenuine = isGenuine;
  }

  public UserCheckDTO newsFeedId(Long newsFeedId) {
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

  public UserCheckDTO replyId(Long replyId) {
    this.replyId = replyId;
    return this;
  }

  /**
   * Get replyId
   * @return replyId
  **/
  @ApiModelProperty(value = "")


  public Long getReplyId() {
    return replyId;
  }

  public void setReplyId(Long replyId) {
    this.replyId = replyId;
  }

  public UserCheckDTO violationId(Long violationId) {
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

  public UserCheckDTO voteType(String voteType) {
    this.voteType = voteType;
    return this;
  }

  /**
   * Get voteType
   * @return voteType
  **/
  @ApiModelProperty(value = "")


  public String getVoteType() {
    return voteType;
  }

  public void setVoteType(String voteType) {
    this.voteType = voteType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserCheckDTO userCheckDTO = (UserCheckDTO) o;
    return Objects.equals(this.category, userCheckDTO.category) &&
        Objects.equals(this.checkedHelpId, userCheckDTO.checkedHelpId) &&
        Objects.equals(this.checkedNeedId, userCheckDTO.checkedNeedId) &&
        Objects.equals(this.checkedUserId, userCheckDTO.checkedUserId) &&
        Objects.equals(this.commentId, userCheckDTO.commentId) &&
        Objects.equals(this.id, userCheckDTO.id) &&
        Objects.equals(this.isGenuine, userCheckDTO.isGenuine) &&
        Objects.equals(this.newsFeedId, userCheckDTO.newsFeedId) &&
        Objects.equals(this.replyId, userCheckDTO.replyId) &&
        Objects.equals(this.violationId, userCheckDTO.violationId) &&
        Objects.equals(this.voteType, userCheckDTO.voteType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, checkedHelpId, checkedNeedId, checkedUserId, commentId, id, isGenuine, newsFeedId, replyId, violationId, voteType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserCheckDTO {\n");
    
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    checkedHelpId: ").append(toIndentedString(checkedHelpId)).append("\n");
    sb.append("    checkedNeedId: ").append(toIndentedString(checkedNeedId)).append("\n");
    sb.append("    checkedUserId: ").append(toIndentedString(checkedUserId)).append("\n");
    sb.append("    commentId: ").append(toIndentedString(commentId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    isGenuine: ").append(toIndentedString(isGenuine)).append("\n");
    sb.append("    newsFeedId: ").append(toIndentedString(newsFeedId)).append("\n");
    sb.append("    replyId: ").append(toIndentedString(replyId)).append("\n");
    sb.append("    violationId: ").append(toIndentedString(violationId)).append("\n");
    sb.append("    voteType: ").append(toIndentedString(voteType)).append("\n");
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


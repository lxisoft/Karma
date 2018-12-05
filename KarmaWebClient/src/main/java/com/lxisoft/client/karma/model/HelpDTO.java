package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HelpDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-12-05T23:14:06.746998700+05:30[Asia/Calcutta]")

public class HelpDTO   {
  @JsonProperty("approvalStatusId")
  private Long approvalStatusId = null;

  @JsonProperty("attachmentUrls")
  @Valid
  private List<String> attachmentUrls = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("fulfilledNeedId")
  private Long fulfilledNeedId = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("noOfComments")
  private Long noOfComments = null;

  @JsonProperty("noOfDisLikes")
  private Long noOfDisLikes = null;

  @JsonProperty("noOfLikes")
  private Long noOfLikes = null;

  @JsonProperty("providedUserId")
  private Long providedUserId = null;

  @JsonProperty("time")
  private OffsetDateTime time = null;

  @JsonProperty("timeElapsed")
  private String timeElapsed = null;

  @JsonProperty("timeInString")
  private String timeInString = null;

  @JsonProperty("userName")
  private String userName = null;

  public HelpDTO approvalStatusId(Long approvalStatusId) {
    this.approvalStatusId = approvalStatusId;
    return this;
  }

  /**
   * Get approvalStatusId
   * @return approvalStatusId
  **/
  @ApiModelProperty(value = "")


  public Long getApprovalStatusId() {
    return approvalStatusId;
  }

  public void setApprovalStatusId(Long approvalStatusId) {
    this.approvalStatusId = approvalStatusId;
  }

  public HelpDTO attachmentUrls(List<String> attachmentUrls) {
    this.attachmentUrls = attachmentUrls;
    return this;
  }

  public HelpDTO addAttachmentUrlsItem(String attachmentUrlsItem) {
    if (this.attachmentUrls == null) {
      this.attachmentUrls = new ArrayList<String>();
    }
    this.attachmentUrls.add(attachmentUrlsItem);
    return this;
  }

  /**
   * Get attachmentUrls
   * @return attachmentUrls
  **/
  @ApiModelProperty(value = "")


  public List<String> getAttachmentUrls() {
    return attachmentUrls;
  }

  public void setAttachmentUrls(List<String> attachmentUrls) {
    this.attachmentUrls = attachmentUrls;
  }

  public HelpDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public HelpDTO fulfilledNeedId(Long fulfilledNeedId) {
    this.fulfilledNeedId = fulfilledNeedId;
    return this;
  }

  /**
   * Get fulfilledNeedId
   * @return fulfilledNeedId
  **/
  @ApiModelProperty(value = "")


  public Long getFulfilledNeedId() {
    return fulfilledNeedId;
  }

  public void setFulfilledNeedId(Long fulfilledNeedId) {
    this.fulfilledNeedId = fulfilledNeedId;
  }

  public HelpDTO id(Long id) {
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

  public HelpDTO noOfComments(Long noOfComments) {
    this.noOfComments = noOfComments;
    return this;
  }

  /**
   * Get noOfComments
   * @return noOfComments
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfComments() {
    return noOfComments;
  }

  public void setNoOfComments(Long noOfComments) {
    this.noOfComments = noOfComments;
  }

  public HelpDTO noOfDisLikes(Long noOfDisLikes) {
    this.noOfDisLikes = noOfDisLikes;
    return this;
  }

  /**
   * Get noOfDisLikes
   * @return noOfDisLikes
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfDisLikes() {
    return noOfDisLikes;
  }

  public void setNoOfDisLikes(Long noOfDisLikes) {
    this.noOfDisLikes = noOfDisLikes;
  }

  public HelpDTO noOfLikes(Long noOfLikes) {
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

  public HelpDTO providedUserId(Long providedUserId) {
    this.providedUserId = providedUserId;
    return this;
  }

  /**
   * Get providedUserId
   * @return providedUserId
  **/
  @ApiModelProperty(value = "")


  public Long getProvidedUserId() {
    return providedUserId;
  }

  public void setProvidedUserId(Long providedUserId) {
    this.providedUserId = providedUserId;
  }

  public HelpDTO time(OffsetDateTime time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getTime() {
    return time;
  }

  public void setTime(OffsetDateTime time) {
    this.time = time;
  }

  public HelpDTO timeElapsed(String timeElapsed) {
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

  public HelpDTO timeInString(String timeInString) {
    this.timeInString = timeInString;
    return this;
  }

  /**
   * Get timeInString
   * @return timeInString
  **/
  @ApiModelProperty(value = "")


  public String getTimeInString() {
    return timeInString;
  }

  public void setTimeInString(String timeInString) {
    this.timeInString = timeInString;
  }

  public HelpDTO userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  **/
  @ApiModelProperty(value = "")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HelpDTO helpDTO = (HelpDTO) o;
    return Objects.equals(this.approvalStatusId, helpDTO.approvalStatusId) &&
        Objects.equals(this.attachmentUrls, helpDTO.attachmentUrls) &&
        Objects.equals(this.description, helpDTO.description) &&
        Objects.equals(this.fulfilledNeedId, helpDTO.fulfilledNeedId) &&
        Objects.equals(this.id, helpDTO.id) &&
        Objects.equals(this.noOfComments, helpDTO.noOfComments) &&
        Objects.equals(this.noOfDisLikes, helpDTO.noOfDisLikes) &&
        Objects.equals(this.noOfLikes, helpDTO.noOfLikes) &&
        Objects.equals(this.providedUserId, helpDTO.providedUserId) &&
        Objects.equals(this.time, helpDTO.time) &&
        Objects.equals(this.timeElapsed, helpDTO.timeElapsed) &&
        Objects.equals(this.timeInString, helpDTO.timeInString) &&
        Objects.equals(this.userName, helpDTO.userName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(approvalStatusId, attachmentUrls, description, fulfilledNeedId, id, noOfComments, noOfDisLikes, noOfLikes, providedUserId, time, timeElapsed, timeInString, userName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HelpDTO {\n");
    
    sb.append("    approvalStatusId: ").append(toIndentedString(approvalStatusId)).append("\n");
    sb.append("    attachmentUrls: ").append(toIndentedString(attachmentUrls)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    fulfilledNeedId: ").append(toIndentedString(fulfilledNeedId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    noOfComments: ").append(toIndentedString(noOfComments)).append("\n");
    sb.append("    noOfDisLikes: ").append(toIndentedString(noOfDisLikes)).append("\n");
    sb.append("    noOfLikes: ").append(toIndentedString(noOfLikes)).append("\n");
    sb.append("    providedUserId: ").append(toIndentedString(providedUserId)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    timeElapsed: ").append(toIndentedString(timeElapsed)).append("\n");
    sb.append("    timeInString: ").append(toIndentedString(timeInString)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
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


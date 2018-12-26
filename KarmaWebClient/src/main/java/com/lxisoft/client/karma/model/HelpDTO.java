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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-12-26T14:20:55.246873800+05:30[Asia/Calcutta]")

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

  @JsonProperty("imageMedias")
  @Valid
  private List<String> imageMedias = null;

  @JsonProperty("imageUrls")
  @Valid
  private List<String> imageUrls = null;

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

  @JsonProperty("videoMedias")
  @Valid
  private List<String> videoMedias = null;

  @JsonProperty("videoUrls")
  @Valid
  private List<String> videoUrls = null;

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

  public HelpDTO imageMedias(List<String> imageMedias) {
    this.imageMedias = imageMedias;
    return this;
  }

  public HelpDTO addImageMediasItem(String imageMediasItem) {
    if (this.imageMedias == null) {
      this.imageMedias = new ArrayList<String>();
    }
    this.imageMedias.add(imageMediasItem);
    return this;
  }

  /**
   * Get imageMedias
   * @return imageMedias
  **/
  @ApiModelProperty(value = "")


  public List<String> getImageMedias() {
    return imageMedias;
  }

  public void setImageMedias(List<String> imageMedias) {
    this.imageMedias = imageMedias;
  }

  public HelpDTO imageUrls(List<String> imageUrls) {
    this.imageUrls = imageUrls;
    return this;
  }

  public HelpDTO addImageUrlsItem(String imageUrlsItem) {
    if (this.imageUrls == null) {
      this.imageUrls = new ArrayList<String>();
    }
    this.imageUrls.add(imageUrlsItem);
    return this;
  }

  /**
   * Get imageUrls
   * @return imageUrls
  **/
  @ApiModelProperty(value = "")


  public List<String> getImageUrls() {
    return imageUrls;
  }

  public void setImageUrls(List<String> imageUrls) {
    this.imageUrls = imageUrls;
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

  public HelpDTO videoMedias(List<String> videoMedias) {
    this.videoMedias = videoMedias;
    return this;
  }

  public HelpDTO addVideoMediasItem(String videoMediasItem) {
    if (this.videoMedias == null) {
      this.videoMedias = new ArrayList<String>();
    }
    this.videoMedias.add(videoMediasItem);
    return this;
  }

  /**
   * Get videoMedias
   * @return videoMedias
  **/
  @ApiModelProperty(value = "")


  public List<String> getVideoMedias() {
    return videoMedias;
  }

  public void setVideoMedias(List<String> videoMedias) {
    this.videoMedias = videoMedias;
  }

  public HelpDTO videoUrls(List<String> videoUrls) {
    this.videoUrls = videoUrls;
    return this;
  }

  public HelpDTO addVideoUrlsItem(String videoUrlsItem) {
    if (this.videoUrls == null) {
      this.videoUrls = new ArrayList<String>();
    }
    this.videoUrls.add(videoUrlsItem);
    return this;
  }

  /**
   * Get videoUrls
   * @return videoUrls
  **/
  @ApiModelProperty(value = "")


  public List<String> getVideoUrls() {
    return videoUrls;
  }

  public void setVideoUrls(List<String> videoUrls) {
    this.videoUrls = videoUrls;
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
        Objects.equals(this.imageMedias, helpDTO.imageMedias) &&
        Objects.equals(this.imageUrls, helpDTO.imageUrls) &&
        Objects.equals(this.noOfComments, helpDTO.noOfComments) &&
        Objects.equals(this.noOfDisLikes, helpDTO.noOfDisLikes) &&
        Objects.equals(this.noOfLikes, helpDTO.noOfLikes) &&
        Objects.equals(this.providedUserId, helpDTO.providedUserId) &&
        Objects.equals(this.time, helpDTO.time) &&
        Objects.equals(this.timeElapsed, helpDTO.timeElapsed) &&
        Objects.equals(this.timeInString, helpDTO.timeInString) &&
        Objects.equals(this.userName, helpDTO.userName) &&
        Objects.equals(this.videoMedias, helpDTO.videoMedias) &&
        Objects.equals(this.videoUrls, helpDTO.videoUrls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(approvalStatusId, attachmentUrls, description, fulfilledNeedId, id, imageMedias, imageUrls, noOfComments, noOfDisLikes, noOfLikes, providedUserId, time, timeElapsed, timeInString, userName, videoMedias, videoUrls);
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
    sb.append("    imageMedias: ").append(toIndentedString(imageMedias)).append("\n");
    sb.append("    imageUrls: ").append(toIndentedString(imageUrls)).append("\n");
    sb.append("    noOfComments: ").append(toIndentedString(noOfComments)).append("\n");
    sb.append("    noOfDisLikes: ").append(toIndentedString(noOfDisLikes)).append("\n");
    sb.append("    noOfLikes: ").append(toIndentedString(noOfLikes)).append("\n");
    sb.append("    providedUserId: ").append(toIndentedString(providedUserId)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    timeElapsed: ").append(toIndentedString(timeElapsed)).append("\n");
    sb.append("    timeInString: ").append(toIndentedString(timeInString)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    videoMedias: ").append(toIndentedString(videoMedias)).append("\n");
    sb.append("    videoUrls: ").append(toIndentedString(videoUrls)).append("\n");
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


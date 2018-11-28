package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PostDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-28T09:15:14.804+05:30[Asia/Calcutta]")

public class PostDTO   {
  @JsonProperty("attachedFiles")
  @Valid
  private List<Resource> attachedFiles = null;

  @JsonProperty("attachedFilesUrls")
  @Valid
  private List<String> attachedFilesUrls = null;

  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("dateInString")
  private String dateInString = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("postedBefore")
  private String postedBefore = null;

  @JsonProperty("registeredUserId")
  private Long registeredUserId = null;

  @JsonProperty("totalComments")
  private Long totalComments = null;

  @JsonProperty("totalDislikes")
  private Long totalDislikes = null;

  @JsonProperty("totalLikes")
  private Long totalLikes = null;

  public PostDTO attachedFiles(List<Resource> attachedFiles) {
    this.attachedFiles = attachedFiles;
    return this;
  }

  public PostDTO addAttachedFilesItem(Resource attachedFilesItem) {
    if (this.attachedFiles == null) {
      this.attachedFiles = new ArrayList<Resource>();
    }
    this.attachedFiles.add(attachedFilesItem);
    return this;
  }

  /**
   * Get attachedFiles
   * @return attachedFiles
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Resource> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(List<Resource> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }

  public PostDTO attachedFilesUrls(List<String> attachedFilesUrls) {
    this.attachedFilesUrls = attachedFilesUrls;
    return this;
  }

  public PostDTO addAttachedFilesUrlsItem(String attachedFilesUrlsItem) {
    if (this.attachedFilesUrls == null) {
      this.attachedFilesUrls = new ArrayList<String>();
    }
    this.attachedFilesUrls.add(attachedFilesUrlsItem);
    return this;
  }

  /**
   * Get attachedFilesUrls
   * @return attachedFilesUrls
  **/
  @ApiModelProperty(value = "")


  public List<String> getAttachedFilesUrls() {
    return attachedFilesUrls;
  }

  public void setAttachedFilesUrls(List<String> attachedFilesUrls) {
    this.attachedFilesUrls = attachedFilesUrls;
  }

  public PostDTO date(OffsetDateTime date) {
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

  public PostDTO dateInString(String dateInString) {
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

  public PostDTO description(String description) {
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

  public PostDTO id(Long id) {
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

  public PostDTO postedBefore(String postedBefore) {
    this.postedBefore = postedBefore;
    return this;
  }

  /**
   * Get postedBefore
   * @return postedBefore
  **/
  @ApiModelProperty(value = "")


  public String getPostedBefore() {
    return postedBefore;
  }

  public void setPostedBefore(String postedBefore) {
    this.postedBefore = postedBefore;
  }

  public PostDTO registeredUserId(Long registeredUserId) {
    this.registeredUserId = registeredUserId;
    return this;
  }

  /**
   * Get registeredUserId
   * @return registeredUserId
  **/
  @ApiModelProperty(value = "")


  public Long getRegisteredUserId() {
    return registeredUserId;
  }

  public void setRegisteredUserId(Long registeredUserId) {
    this.registeredUserId = registeredUserId;
  }

  public PostDTO totalComments(Long totalComments) {
    this.totalComments = totalComments;
    return this;
  }

  /**
   * Get totalComments
   * @return totalComments
  **/
  @ApiModelProperty(value = "")


  public Long getTotalComments() {
    return totalComments;
  }

  public void setTotalComments(Long totalComments) {
    this.totalComments = totalComments;
  }

  public PostDTO totalDislikes(Long totalDislikes) {
    this.totalDislikes = totalDislikes;
    return this;
  }

  /**
   * Get totalDislikes
   * @return totalDislikes
  **/
  @ApiModelProperty(value = "")


  public Long getTotalDislikes() {
    return totalDislikes;
  }

  public void setTotalDislikes(Long totalDislikes) {
    this.totalDislikes = totalDislikes;
  }

  public PostDTO totalLikes(Long totalLikes) {
    this.totalLikes = totalLikes;
    return this;
  }

  /**
   * Get totalLikes
   * @return totalLikes
  **/
  @ApiModelProperty(value = "")


  public Long getTotalLikes() {
    return totalLikes;
  }

  public void setTotalLikes(Long totalLikes) {
    this.totalLikes = totalLikes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostDTO postDTO = (PostDTO) o;
    return Objects.equals(this.attachedFiles, postDTO.attachedFiles) &&
        Objects.equals(this.attachedFilesUrls, postDTO.attachedFilesUrls) &&
        Objects.equals(this.date, postDTO.date) &&
        Objects.equals(this.dateInString, postDTO.dateInString) &&
        Objects.equals(this.description, postDTO.description) &&
        Objects.equals(this.id, postDTO.id) &&
        Objects.equals(this.postedBefore, postDTO.postedBefore) &&
        Objects.equals(this.registeredUserId, postDTO.registeredUserId) &&
        Objects.equals(this.totalComments, postDTO.totalComments) &&
        Objects.equals(this.totalDislikes, postDTO.totalDislikes) &&
        Objects.equals(this.totalLikes, postDTO.totalLikes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attachedFiles, attachedFilesUrls, date, dateInString, description, id, postedBefore, registeredUserId, totalComments, totalDislikes, totalLikes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostDTO {\n");
    
    sb.append("    attachedFiles: ").append(toIndentedString(attachedFiles)).append("\n");
    sb.append("    attachedFilesUrls: ").append(toIndentedString(attachedFilesUrls)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    dateInString: ").append(toIndentedString(dateInString)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    postedBefore: ").append(toIndentedString(postedBefore)).append("\n");
    sb.append("    registeredUserId: ").append(toIndentedString(registeredUserId)).append("\n");
    sb.append("    totalComments: ").append(toIndentedString(totalComments)).append("\n");
    sb.append("    totalDislikes: ").append(toIndentedString(totalDislikes)).append("\n");
    sb.append("    totalLikes: ").append(toIndentedString(totalLikes)).append("\n");
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


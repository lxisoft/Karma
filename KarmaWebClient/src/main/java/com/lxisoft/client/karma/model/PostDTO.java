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
 * PostDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-12-26T23:53:37.224618500+05:30[Asia/Calcutta]")

public class PostDTO   {
  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("noOfComments")
  private Long noOfComments = null;

  @JsonProperty("noOfDislikes")
  private Long noOfDislikes = null;

  @JsonProperty("noOfLikes")
  private Long noOfLikes = null;

  @JsonProperty("registeredUserId")
  private Long registeredUserId = null;

  @JsonProperty("timeElapsed")
  private String timeElapsed = null;

  @JsonProperty("timeInString")
  private String timeInString = null;

  @JsonProperty("userName")
  private String userName = null;

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

  public PostDTO noOfComments(Long noOfComments) {
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

  public PostDTO noOfDislikes(Long noOfDislikes) {
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

  public PostDTO noOfLikes(Long noOfLikes) {
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

  public PostDTO timeElapsed(String timeElapsed) {
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

  public PostDTO timeInString(String timeInString) {
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

  public PostDTO userName(String userName) {
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
    PostDTO postDTO = (PostDTO) o;
    return Objects.equals(this.date, postDTO.date) &&
        Objects.equals(this.description, postDTO.description) &&
        Objects.equals(this.id, postDTO.id) &&
        Objects.equals(this.noOfComments, postDTO.noOfComments) &&
        Objects.equals(this.noOfDislikes, postDTO.noOfDislikes) &&
        Objects.equals(this.noOfLikes, postDTO.noOfLikes) &&
        Objects.equals(this.registeredUserId, postDTO.registeredUserId) &&
        Objects.equals(this.timeElapsed, postDTO.timeElapsed) &&
        Objects.equals(this.timeInString, postDTO.timeInString) &&
        Objects.equals(this.userName, postDTO.userName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, description, id, noOfComments, noOfDislikes, noOfLikes, registeredUserId, timeElapsed, timeInString, userName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostDTO {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    noOfComments: ").append(toIndentedString(noOfComments)).append("\n");
    sb.append("    noOfDislikes: ").append(toIndentedString(noOfDislikes)).append("\n");
    sb.append("    noOfLikes: ").append(toIndentedString(noOfLikes)).append("\n");
    sb.append("    registeredUserId: ").append(toIndentedString(registeredUserId)).append("\n");
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


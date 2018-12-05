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
 * FeedDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-12-05T23:14:06.746998700+05:30[Asia/Calcutta]")

public class FeedDTO   {
  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("referenceId")
  private Long referenceId = null;

  @JsonProperty("registeredUserId")
  private Long registeredUserId = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("type")
  private String type = null;

  public FeedDTO date(OffsetDateTime date) {
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

  public FeedDTO id(Long id) {
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

  public FeedDTO referenceId(Long referenceId) {
    this.referenceId = referenceId;
    return this;
  }

  /**
   * Get referenceId
   * @return referenceId
  **/
  @ApiModelProperty(value = "")


  public Long getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(Long referenceId) {
    this.referenceId = referenceId;
  }

  public FeedDTO registeredUserId(Long registeredUserId) {
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

  public FeedDTO title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public FeedDTO type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeedDTO feedDTO = (FeedDTO) o;
    return Objects.equals(this.date, feedDTO.date) &&
        Objects.equals(this.id, feedDTO.id) &&
        Objects.equals(this.referenceId, feedDTO.referenceId) &&
        Objects.equals(this.registeredUserId, feedDTO.registeredUserId) &&
        Objects.equals(this.title, feedDTO.title) &&
        Objects.equals(this.type, feedDTO.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, id, referenceId, registeredUserId, title, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeedDTO {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    referenceId: ").append(toIndentedString(referenceId)).append("\n");
    sb.append("    registeredUserId: ").append(toIndentedString(registeredUserId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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


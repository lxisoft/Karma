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
 * ViolationDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")

public class ViolationDTO   {
  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("isAnonymous")
  private Boolean isAnonymous = null;

  public ViolationDTO date(OffsetDateTime date) {
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

  public ViolationDTO description(String description) {
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

  public ViolationDTO id(Long id) {
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

  public ViolationDTO isAnonymous(Boolean isAnonymous) {
    this.isAnonymous = isAnonymous;
    return this;
  }

  /**
   * Get isAnonymous
   * @return isAnonymous
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsAnonymous() {
    return isAnonymous;
  }

  public void setIsAnonymous(Boolean isAnonymous) {
    this.isAnonymous = isAnonymous;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViolationDTO violationDTO = (ViolationDTO) o;
    return Objects.equals(this.date, violationDTO.date) &&
        Objects.equals(this.description, violationDTO.description) &&
        Objects.equals(this.id, violationDTO.id) &&
        Objects.equals(this.isAnonymous, violationDTO.isAnonymous);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, description, id, isAnonymous);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ViolationDTO {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    isAnonymous: ").append(toIndentedString(isAnonymous)).append("\n");
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


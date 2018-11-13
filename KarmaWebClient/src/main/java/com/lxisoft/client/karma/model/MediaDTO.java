package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
<<<<<<< HEAD
import org.springframework.core.io.Resource;
=======
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MediaDTO
 */
@Validated
<<<<<<< HEAD
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-09T14:03:13.373+05:30[Asia/Calcutta]")
=======
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3

public class MediaDTO   {
  @JsonProperty("extension")
  private String extension = null;

<<<<<<< HEAD
  @JsonProperty("file")
  private Resource file = null;

=======
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
  @JsonProperty("fileName")
  private String fileName = null;

  @JsonProperty("helpId")
  private Long helpId = null;

  @JsonProperty("id")
  private Long id = null;

<<<<<<< HEAD
=======
  @JsonProperty("multipartFile")
  private org.springframework.core.io.Resource multipartFile = null;

>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
  @JsonProperty("needId")
  private Long needId = null;

  @JsonProperty("newsFeedId")
  private Long newsFeedId = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("violationId")
  private Long violationId = null;

  public MediaDTO extension(String extension) {
    this.extension = extension;
    return this;
  }

  /**
   * Get extension
   * @return extension
  **/
  @ApiModelProperty(value = "")


  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

<<<<<<< HEAD
  public MediaDTO file(Resource file) {
    this.file = file;
    return this;
  }

  /**
   * Get file
   * @return file
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Resource getFile() {
    return file;
  }

  public void setFile(Resource file) {
    this.file = file;
  }

=======
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
  public MediaDTO fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get fileName
   * @return fileName
  **/
  @ApiModelProperty(value = "")


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public MediaDTO helpId(Long helpId) {
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

  public MediaDTO id(Long id) {
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

<<<<<<< HEAD
=======
  public MediaDTO multipartFile(org.springframework.core.io.Resource multipartFile) {
    this.multipartFile = multipartFile;
    return this;
  }

  /**
   * Get multipartFile
   * @return multipartFile
  **/
  @ApiModelProperty(value = "")

  @Valid

  public org.springframework.core.io.Resource getMultipartFile() {
    return multipartFile;
  }

  public void setMultipartFile(org.springframework.core.io.Resource multipartFile) {
    this.multipartFile = multipartFile;
  }

>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
  public MediaDTO needId(Long needId) {
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

  public MediaDTO newsFeedId(Long newsFeedId) {
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

  public MediaDTO url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(value = "")


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public MediaDTO violationId(Long violationId) {
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
    MediaDTO mediaDTO = (MediaDTO) o;
    return Objects.equals(this.extension, mediaDTO.extension) &&
<<<<<<< HEAD
        Objects.equals(this.file, mediaDTO.file) &&
        Objects.equals(this.fileName, mediaDTO.fileName) &&
        Objects.equals(this.helpId, mediaDTO.helpId) &&
        Objects.equals(this.id, mediaDTO.id) &&
=======
        Objects.equals(this.fileName, mediaDTO.fileName) &&
        Objects.equals(this.helpId, mediaDTO.helpId) &&
        Objects.equals(this.id, mediaDTO.id) &&
        Objects.equals(this.multipartFile, mediaDTO.multipartFile) &&
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
        Objects.equals(this.needId, mediaDTO.needId) &&
        Objects.equals(this.newsFeedId, mediaDTO.newsFeedId) &&
        Objects.equals(this.url, mediaDTO.url) &&
        Objects.equals(this.violationId, mediaDTO.violationId);
  }

  @Override
  public int hashCode() {
<<<<<<< HEAD
    return Objects.hash(extension, file, fileName, helpId, id, needId, newsFeedId, url, violationId);
=======
    return Objects.hash(extension, fileName, helpId, id, multipartFile, needId, newsFeedId, url, violationId);
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediaDTO {\n");
    
    sb.append("    extension: ").append(toIndentedString(extension)).append("\n");
<<<<<<< HEAD
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    helpId: ").append(toIndentedString(helpId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
=======
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    helpId: ").append(toIndentedString(helpId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    multipartFile: ").append(toIndentedString(multipartFile)).append("\n");
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
    sb.append("    needId: ").append(toIndentedString(needId)).append("\n");
    sb.append("    newsFeedId: ").append(toIndentedString(newsFeedId)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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


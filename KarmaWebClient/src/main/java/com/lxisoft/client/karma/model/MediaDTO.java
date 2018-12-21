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
 * MediaDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-12-21T10:50:24.022075900+05:30[Asia/Calcutta]")

public class MediaDTO   {
  @JsonProperty("extension")
  private String extension = null;

  @JsonProperty("file")
  private byte[] file = null;

  @JsonProperty("fileContentType")
  private String fileContentType = null;

  @JsonProperty("fileName")
  private String fileName = null;

  @JsonProperty("helpId")
  private Long helpId = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("needId")
  private Long needId = null;

  @JsonProperty("postId")
  private Long postId = null;

  @JsonProperty("url")
  private String url = null;

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

  public MediaDTO file(byte[] file) {
    this.file = file;
    return this;
  }

  /**
   * Get file
   * @return file
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getFile() {
    return file;
  }

  public void setFile(byte[] file) {
    this.file = file;
  }

  public MediaDTO fileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
    return this;
  }

  /**
   * Get fileContentType
   * @return fileContentType
  **/
  @ApiModelProperty(value = "")


  public String getFileContentType() {
    return fileContentType;
  }

  public void setFileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
  }

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

  public MediaDTO postId(Long postId) {
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
        Objects.equals(this.file, mediaDTO.file) &&
        Objects.equals(this.fileContentType, mediaDTO.fileContentType) &&
        Objects.equals(this.fileName, mediaDTO.fileName) &&
        Objects.equals(this.helpId, mediaDTO.helpId) &&
        Objects.equals(this.id, mediaDTO.id) &&
        Objects.equals(this.needId, mediaDTO.needId) &&
        Objects.equals(this.postId, mediaDTO.postId) &&
        Objects.equals(this.url, mediaDTO.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extension, file, fileContentType, fileName, helpId, id, needId, postId, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediaDTO {\n");
    
    sb.append("    extension: ").append(toIndentedString(extension)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    fileContentType: ").append(toIndentedString(fileContentType)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    helpId: ").append(toIndentedString(helpId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    needId: ").append(toIndentedString(needId)).append("\n");
    sb.append("    postId: ").append(toIndentedString(postId)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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


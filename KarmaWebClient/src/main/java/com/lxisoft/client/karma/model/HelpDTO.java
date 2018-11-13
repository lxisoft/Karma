package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import org.springframework.core.io.Resource;
=======
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HelpDTO
 */
@Validated
<<<<<<< HEAD
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-09T14:03:13.373+05:30[Asia/Calcutta]")
=======
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3

public class HelpDTO   {
  @JsonProperty("approvalStatusId")
  private Long approvalStatusId = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("fileNameList")
  @Valid
  private List<String> fileNameList = null;

<<<<<<< HEAD
  @JsonProperty("files")
  @Valid
  private List<Resource> files = null;

=======
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
  @JsonProperty("fulfilledNeedId")
  private Long fulfilledNeedId = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("providedUserId")
  private Long providedUserId = null;

  @JsonProperty("time")
  private OffsetDateTime time = null;

  @JsonProperty("timeInString")
  private String timeInString = null;

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

  public HelpDTO fileNameList(List<String> fileNameList) {
    this.fileNameList = fileNameList;
    return this;
  }

  public HelpDTO addFileNameListItem(String fileNameListItem) {
    if (this.fileNameList == null) {
      this.fileNameList = new ArrayList<String>();
    }
    this.fileNameList.add(fileNameListItem);
    return this;
  }

  /**
   * Get fileNameList
   * @return fileNameList
  **/
  @ApiModelProperty(value = "")


  public List<String> getFileNameList() {
    return fileNameList;
  }

  public void setFileNameList(List<String> fileNameList) {
    this.fileNameList = fileNameList;
  }

<<<<<<< HEAD
  public HelpDTO files(List<Resource> files) {
    this.files = files;
    return this;
  }

  public HelpDTO addFilesItem(Resource filesItem) {
    if (this.files == null) {
      this.files = new ArrayList<Resource>();
    }
    this.files.add(filesItem);
    return this;
  }

  /**
   * Get files
   * @return files
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Resource> getFiles() {
    return files;
  }

  public void setFiles(List<Resource> files) {
    this.files = files;
  }

=======
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
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
        Objects.equals(this.description, helpDTO.description) &&
        Objects.equals(this.fileNameList, helpDTO.fileNameList) &&
<<<<<<< HEAD
        Objects.equals(this.files, helpDTO.files) &&
=======
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
        Objects.equals(this.fulfilledNeedId, helpDTO.fulfilledNeedId) &&
        Objects.equals(this.id, helpDTO.id) &&
        Objects.equals(this.providedUserId, helpDTO.providedUserId) &&
        Objects.equals(this.time, helpDTO.time) &&
        Objects.equals(this.timeInString, helpDTO.timeInString);
  }

  @Override
  public int hashCode() {
<<<<<<< HEAD
    return Objects.hash(approvalStatusId, description, fileNameList, files, fulfilledNeedId, id, providedUserId, time, timeInString);
=======
    return Objects.hash(approvalStatusId, description, fileNameList, fulfilledNeedId, id, providedUserId, time, timeInString);
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HelpDTO {\n");
    
    sb.append("    approvalStatusId: ").append(toIndentedString(approvalStatusId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    fileNameList: ").append(toIndentedString(fileNameList)).append("\n");
<<<<<<< HEAD
    sb.append("    files: ").append(toIndentedString(files)).append("\n");
=======
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3
    sb.append("    fulfilledNeedId: ").append(toIndentedString(fulfilledNeedId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    providedUserId: ").append(toIndentedString(providedUserId)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    timeInString: ").append(toIndentedString(timeInString)).append("\n");
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

package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.lxisoft.client.karma.model.CategoryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NeedDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-19T00:03:59.400+05:30[Asia/Calcutta]")

public class NeedDTO   {
  @JsonProperty("approvalStatusId")
  private Long approvalStatusId = null;

  @JsonProperty("beneficiaryType")
  private String beneficiaryType = null;

  @JsonProperty("categories")
  @Valid
  private List<CategoryDTO> categories = null;

  @JsonProperty("categoryList")
  @Valid
  private List<CategoryDTO> categoryList = null;

  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("dateInString")
  private String dateInString = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("fileNameList")
  @Valid
  private List<String> fileNameList = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("percentageOfGenuineness")
  private Long percentageOfGenuineness = null;

  @JsonProperty("postedUserId")
  private Long postedUserId = null;

  @JsonProperty("severityId")
  private Long severityId = null;

  @JsonProperty("verificationTeamId")
  private Long verificationTeamId = null;

  public NeedDTO approvalStatusId(Long approvalStatusId) {
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

  public NeedDTO beneficiaryType(String beneficiaryType) {
    this.beneficiaryType = beneficiaryType;
    return this;
  }

  /**
   * Get beneficiaryType
   * @return beneficiaryType
  **/
  @ApiModelProperty(value = "")


  public String getBeneficiaryType() {
    return beneficiaryType;
  }

  public void setBeneficiaryType(String beneficiaryType) {
    this.beneficiaryType = beneficiaryType;
  }

  public NeedDTO categories(List<CategoryDTO> categories) {
    this.categories = categories;
    return this;
  }

  public NeedDTO addCategoriesItem(CategoryDTO categoriesItem) {
    if (this.categories == null) {
      this.categories = new ArrayList<CategoryDTO>();
    }
    this.categories.add(categoriesItem);
    return this;
  }

  /**
   * Get categories
   * @return categories
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CategoryDTO> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryDTO> categories) {
    this.categories = categories;
  }

  public NeedDTO categoryList(List<CategoryDTO> categoryList) {
    this.categoryList = categoryList;
    return this;
  }

  public NeedDTO addCategoryListItem(CategoryDTO categoryListItem) {
    if (this.categoryList == null) {
      this.categoryList = new ArrayList<CategoryDTO>();
    }
    this.categoryList.add(categoryListItem);
    return this;
  }

  /**
   * Get categoryList
   * @return categoryList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CategoryDTO> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<CategoryDTO> categoryList) {
    this.categoryList = categoryList;
  }

  public NeedDTO date(OffsetDateTime date) {
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

  public NeedDTO dateInString(String dateInString) {
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

  public NeedDTO description(String description) {
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

  public NeedDTO fileNameList(List<String> fileNameList) {
    this.fileNameList = fileNameList;
    return this;
  }

  public NeedDTO addFileNameListItem(String fileNameListItem) {
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

  public NeedDTO id(Long id) {
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

  public NeedDTO percentageOfGenuineness(Long percentageOfGenuineness) {
    this.percentageOfGenuineness = percentageOfGenuineness;
    return this;
  }

  /**
   * Get percentageOfGenuineness
   * @return percentageOfGenuineness
  **/
  @ApiModelProperty(value = "")


  public Long getPercentageOfGenuineness() {
    return percentageOfGenuineness;
  }

  public void setPercentageOfGenuineness(Long percentageOfGenuineness) {
    this.percentageOfGenuineness = percentageOfGenuineness;
  }

  public NeedDTO postedUserId(Long postedUserId) {
    this.postedUserId = postedUserId;
    return this;
  }

  /**
   * Get postedUserId
   * @return postedUserId
  **/
  @ApiModelProperty(value = "")


  public Long getPostedUserId() {
    return postedUserId;
  }

  public void setPostedUserId(Long postedUserId) {
    this.postedUserId = postedUserId;
  }

  public NeedDTO severityId(Long severityId) {
    this.severityId = severityId;
    return this;
  }

  /**
   * Get severityId
   * @return severityId
  **/
  @ApiModelProperty(value = "")


  public Long getSeverityId() {
    return severityId;
  }

  public void setSeverityId(Long severityId) {
    this.severityId = severityId;
  }

  public NeedDTO verificationTeamId(Long verificationTeamId) {
    this.verificationTeamId = verificationTeamId;
    return this;
  }

  /**
   * Get verificationTeamId
   * @return verificationTeamId
  **/
  @ApiModelProperty(value = "")


  public Long getVerificationTeamId() {
    return verificationTeamId;
  }

  public void setVerificationTeamId(Long verificationTeamId) {
    this.verificationTeamId = verificationTeamId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NeedDTO needDTO = (NeedDTO) o;
    return Objects.equals(this.approvalStatusId, needDTO.approvalStatusId) &&
        Objects.equals(this.beneficiaryType, needDTO.beneficiaryType) &&
        Objects.equals(this.categories, needDTO.categories) &&
        Objects.equals(this.categoryList, needDTO.categoryList) &&
        Objects.equals(this.date, needDTO.date) &&
        Objects.equals(this.dateInString, needDTO.dateInString) &&
        Objects.equals(this.description, needDTO.description) &&
        Objects.equals(this.fileNameList, needDTO.fileNameList) &&
        Objects.equals(this.id, needDTO.id) &&
        Objects.equals(this.percentageOfGenuineness, needDTO.percentageOfGenuineness) &&
        Objects.equals(this.postedUserId, needDTO.postedUserId) &&
        Objects.equals(this.severityId, needDTO.severityId) &&
        Objects.equals(this.verificationTeamId, needDTO.verificationTeamId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(approvalStatusId, beneficiaryType, categories, categoryList, date, dateInString, description, fileNameList, id, percentageOfGenuineness, postedUserId, severityId, verificationTeamId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NeedDTO {\n");
    
    sb.append("    approvalStatusId: ").append(toIndentedString(approvalStatusId)).append("\n");
    sb.append("    beneficiaryType: ").append(toIndentedString(beneficiaryType)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    categoryList: ").append(toIndentedString(categoryList)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    dateInString: ").append(toIndentedString(dateInString)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    fileNameList: ").append(toIndentedString(fileNameList)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    percentageOfGenuineness: ").append(toIndentedString(percentageOfGenuineness)).append("\n");
    sb.append("    postedUserId: ").append(toIndentedString(postedUserId)).append("\n");
    sb.append("    severityId: ").append(toIndentedString(severityId)).append("\n");
    sb.append("    verificationTeamId: ").append(toIndentedString(verificationTeamId)).append("\n");
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


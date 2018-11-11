package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LoggedUserDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T16:15:16.814+05:30[Asia/Calcutta]")

public class LoggedUserDTO   {
  @JsonProperty("bloodGroup")
  private String bloodGroup = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("dob")
  private LocalDate dob = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("emotionalQuotient")
  private Long emotionalQuotient = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("gender")
  private String gender = null;

  @JsonProperty("happinessIndex")
  private Long happinessIndex = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("profession")
  private String profession = null;

  @JsonProperty("profilePicId")
  private Long profilePicId = null;

  @JsonProperty("rating")
  private Long rating = null;

  @JsonProperty("socialQuotient")
  private Long socialQuotient = null;

  public LoggedUserDTO bloodGroup(String bloodGroup) {
    this.bloodGroup = bloodGroup;
    return this;
  }

  /**
   * Get bloodGroup
   * @return bloodGroup
  **/
  @ApiModelProperty(value = "")


  public String getBloodGroup() {
    return bloodGroup;
  }

  public void setBloodGroup(String bloodGroup) {
    this.bloodGroup = bloodGroup;
  }

  public LoggedUserDTO description(String description) {
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

  public LoggedUserDTO dob(LocalDate dob) {
    this.dob = dob;
    return this;
  }

  /**
   * Get dob
   * @return dob
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public LoggedUserDTO email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LoggedUserDTO emotionalQuotient(Long emotionalQuotient) {
    this.emotionalQuotient = emotionalQuotient;
    return this;
  }

  /**
   * Get emotionalQuotient
   * @return emotionalQuotient
  **/
  @ApiModelProperty(value = "")


  public Long getEmotionalQuotient() {
    return emotionalQuotient;
  }

  public void setEmotionalQuotient(Long emotionalQuotient) {
    this.emotionalQuotient = emotionalQuotient;
  }

  public LoggedUserDTO firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public LoggedUserDTO gender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
  **/
  @ApiModelProperty(value = "")


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LoggedUserDTO happinessIndex(Long happinessIndex) {
    this.happinessIndex = happinessIndex;
    return this;
  }

  /**
   * Get happinessIndex
   * @return happinessIndex
  **/
  @ApiModelProperty(value = "")


  public Long getHappinessIndex() {
    return happinessIndex;
  }

  public void setHappinessIndex(Long happinessIndex) {
    this.happinessIndex = happinessIndex;
  }

  public LoggedUserDTO id(Long id) {
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

  public LoggedUserDTO lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(value = "")


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LoggedUserDTO profession(String profession) {
    this.profession = profession;
    return this;
  }

  /**
   * Get profession
   * @return profession
  **/
  @ApiModelProperty(value = "")


  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public LoggedUserDTO profilePicId(Long profilePicId) {
    this.profilePicId = profilePicId;
    return this;
  }

  /**
   * Get profilePicId
   * @return profilePicId
  **/
  @ApiModelProperty(value = "")


  public Long getProfilePicId() {
    return profilePicId;
  }

  public void setProfilePicId(Long profilePicId) {
    this.profilePicId = profilePicId;
  }

  public LoggedUserDTO rating(Long rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Get rating
   * @return rating
  **/
  @ApiModelProperty(value = "")


  public Long getRating() {
    return rating;
  }

  public void setRating(Long rating) {
    this.rating = rating;
  }

  public LoggedUserDTO socialQuotient(Long socialQuotient) {
    this.socialQuotient = socialQuotient;
    return this;
  }

  /**
   * Get socialQuotient
   * @return socialQuotient
  **/
  @ApiModelProperty(value = "")


  public Long getSocialQuotient() {
    return socialQuotient;
  }

  public void setSocialQuotient(Long socialQuotient) {
    this.socialQuotient = socialQuotient;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoggedUserDTO loggedUserDTO = (LoggedUserDTO) o;
    return Objects.equals(this.bloodGroup, loggedUserDTO.bloodGroup) &&
        Objects.equals(this.description, loggedUserDTO.description) &&
        Objects.equals(this.dob, loggedUserDTO.dob) &&
        Objects.equals(this.email, loggedUserDTO.email) &&
        Objects.equals(this.emotionalQuotient, loggedUserDTO.emotionalQuotient) &&
        Objects.equals(this.firstName, loggedUserDTO.firstName) &&
        Objects.equals(this.gender, loggedUserDTO.gender) &&
        Objects.equals(this.happinessIndex, loggedUserDTO.happinessIndex) &&
        Objects.equals(this.id, loggedUserDTO.id) &&
        Objects.equals(this.lastName, loggedUserDTO.lastName) &&
        Objects.equals(this.profession, loggedUserDTO.profession) &&
        Objects.equals(this.profilePicId, loggedUserDTO.profilePicId) &&
        Objects.equals(this.rating, loggedUserDTO.rating) &&
        Objects.equals(this.socialQuotient, loggedUserDTO.socialQuotient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bloodGroup, description, dob, email, emotionalQuotient, firstName, gender, happinessIndex, id, lastName, profession, profilePicId, rating, socialQuotient);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoggedUserDTO {\n");
    
    sb.append("    bloodGroup: ").append(toIndentedString(bloodGroup)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    emotionalQuotient: ").append(toIndentedString(emotionalQuotient)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    happinessIndex: ").append(toIndentedString(happinessIndex)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    profession: ").append(toIndentedString(profession)).append("\n");
    sb.append("    profilePicId: ").append(toIndentedString(profilePicId)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    socialQuotient: ").append(toIndentedString(socialQuotient)).append("\n");
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


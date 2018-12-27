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
 * RegisteredUserDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-12-27T14:19:04.189059+05:30[Asia/Calcutta]")

public class RegisteredUserDTO   {
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

  @JsonProperty("idProofId")
  private Long idProofId = null;

  @JsonProperty("imageMedia")
  private String imageMedia = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("noOfHelps")
  private Long noOfHelps = null;

  @JsonProperty("noOfNeeds")
  private Long noOfNeeds = null;

  @JsonProperty("profession")
  private String profession = null;

  @JsonProperty("profilePicId")
  private Long profilePicId = null;

  @JsonProperty("rating")
  private Long rating = null;

  @JsonProperty("socialQuotient")
  private Long socialQuotient = null;

  public RegisteredUserDTO bloodGroup(String bloodGroup) {
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

  public RegisteredUserDTO description(String description) {
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

  public RegisteredUserDTO dob(LocalDate dob) {
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

  public RegisteredUserDTO email(String email) {
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

  public RegisteredUserDTO emotionalQuotient(Long emotionalQuotient) {
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

  public RegisteredUserDTO firstName(String firstName) {
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

  public RegisteredUserDTO gender(String gender) {
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

  public RegisteredUserDTO happinessIndex(Long happinessIndex) {
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

  public RegisteredUserDTO id(Long id) {
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

  public RegisteredUserDTO idProofId(Long idProofId) {
    this.idProofId = idProofId;
    return this;
  }

  /**
   * Get idProofId
   * @return idProofId
  **/
  @ApiModelProperty(value = "")


  public Long getIdProofId() {
    return idProofId;
  }

  public void setIdProofId(Long idProofId) {
    this.idProofId = idProofId;
  }

  public RegisteredUserDTO imageMedia(String imageMedia) {
    this.imageMedia = imageMedia;
    return this;
  }

  /**
   * Get imageMedia
   * @return imageMedia
  **/
  @ApiModelProperty(value = "")


  public String getImageMedia() {
    return imageMedia;
  }

  public void setImageMedia(String imageMedia) {
    this.imageMedia = imageMedia;
  }

  public RegisteredUserDTO lastName(String lastName) {
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

  public RegisteredUserDTO noOfHelps(Long noOfHelps) {
    this.noOfHelps = noOfHelps;
    return this;
  }

  /**
   * Get noOfHelps
   * @return noOfHelps
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfHelps() {
    return noOfHelps;
  }

  public void setNoOfHelps(Long noOfHelps) {
    this.noOfHelps = noOfHelps;
  }

  public RegisteredUserDTO noOfNeeds(Long noOfNeeds) {
    this.noOfNeeds = noOfNeeds;
    return this;
  }

  /**
   * Get noOfNeeds
   * @return noOfNeeds
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfNeeds() {
    return noOfNeeds;
  }

  public void setNoOfNeeds(Long noOfNeeds) {
    this.noOfNeeds = noOfNeeds;
  }

  public RegisteredUserDTO profession(String profession) {
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

  public RegisteredUserDTO profilePicId(Long profilePicId) {
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

  public RegisteredUserDTO rating(Long rating) {
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

  public RegisteredUserDTO socialQuotient(Long socialQuotient) {
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
    RegisteredUserDTO registeredUserDTO = (RegisteredUserDTO) o;
    return Objects.equals(this.bloodGroup, registeredUserDTO.bloodGroup) &&
        Objects.equals(this.description, registeredUserDTO.description) &&
        Objects.equals(this.dob, registeredUserDTO.dob) &&
        Objects.equals(this.email, registeredUserDTO.email) &&
        Objects.equals(this.emotionalQuotient, registeredUserDTO.emotionalQuotient) &&
        Objects.equals(this.firstName, registeredUserDTO.firstName) &&
        Objects.equals(this.gender, registeredUserDTO.gender) &&
        Objects.equals(this.happinessIndex, registeredUserDTO.happinessIndex) &&
        Objects.equals(this.id, registeredUserDTO.id) &&
        Objects.equals(this.idProofId, registeredUserDTO.idProofId) &&
        Objects.equals(this.imageMedia, registeredUserDTO.imageMedia) &&
        Objects.equals(this.lastName, registeredUserDTO.lastName) &&
        Objects.equals(this.noOfHelps, registeredUserDTO.noOfHelps) &&
        Objects.equals(this.noOfNeeds, registeredUserDTO.noOfNeeds) &&
        Objects.equals(this.profession, registeredUserDTO.profession) &&
        Objects.equals(this.profilePicId, registeredUserDTO.profilePicId) &&
        Objects.equals(this.rating, registeredUserDTO.rating) &&
        Objects.equals(this.socialQuotient, registeredUserDTO.socialQuotient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bloodGroup, description, dob, email, emotionalQuotient, firstName, gender, happinessIndex, id, idProofId, imageMedia, lastName, noOfHelps, noOfNeeds, profession, profilePicId, rating, socialQuotient);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisteredUserDTO {\n");
    
    sb.append("    bloodGroup: ").append(toIndentedString(bloodGroup)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    emotionalQuotient: ").append(toIndentedString(emotionalQuotient)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    happinessIndex: ").append(toIndentedString(happinessIndex)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idProofId: ").append(toIndentedString(idProofId)).append("\n");
    sb.append("    imageMedia: ").append(toIndentedString(imageMedia)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    noOfHelps: ").append(toIndentedString(noOfHelps)).append("\n");
    sb.append("    noOfNeeds: ").append(toIndentedString(noOfNeeds)).append("\n");
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


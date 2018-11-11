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
 * AddressDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")

public class AddressDTO   {
  @JsonProperty("city")
  private String city = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("houseName")
  private String houseName = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("loggedUserId")
  private Long loggedUserId = null;

  @JsonProperty("place")
  private String place = null;

  @JsonProperty("state")
  private String state = null;

  @JsonProperty("zip")
  private Long zip = null;

  public AddressDTO city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  **/
  @ApiModelProperty(value = "")


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public AddressDTO country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  **/
  @ApiModelProperty(value = "")


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public AddressDTO houseName(String houseName) {
    this.houseName = houseName;
    return this;
  }

  /**
   * Get houseName
   * @return houseName
  **/
  @ApiModelProperty(value = "")


  public String getHouseName() {
    return houseName;
  }

  public void setHouseName(String houseName) {
    this.houseName = houseName;
  }

  public AddressDTO id(Long id) {
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

  public AddressDTO loggedUserId(Long loggedUserId) {
    this.loggedUserId = loggedUserId;
    return this;
  }

  /**
   * Get loggedUserId
   * @return loggedUserId
  **/
  @ApiModelProperty(value = "")


  public Long getLoggedUserId() {
    return loggedUserId;
  }

  public void setLoggedUserId(Long loggedUserId) {
    this.loggedUserId = loggedUserId;
  }

  public AddressDTO place(String place) {
    this.place = place;
    return this;
  }

  /**
   * Get place
   * @return place
  **/
  @ApiModelProperty(value = "")


  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public AddressDTO state(String state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(value = "")


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public AddressDTO zip(Long zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Get zip
   * @return zip
  **/
  @ApiModelProperty(value = "")


  public Long getZip() {
    return zip;
  }

  public void setZip(Long zip) {
    this.zip = zip;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressDTO addressDTO = (AddressDTO) o;
    return Objects.equals(this.city, addressDTO.city) &&
        Objects.equals(this.country, addressDTO.country) &&
        Objects.equals(this.houseName, addressDTO.houseName) &&
        Objects.equals(this.id, addressDTO.id) &&
        Objects.equals(this.loggedUserId, addressDTO.loggedUserId) &&
        Objects.equals(this.place, addressDTO.place) &&
        Objects.equals(this.state, addressDTO.state) &&
        Objects.equals(this.zip, addressDTO.zip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, country, houseName, id, loggedUserId, place, state, zip);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressDTO {\n");
    
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    houseName: ").append(toIndentedString(houseName)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    loggedUserId: ").append(toIndentedString(loggedUserId)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
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


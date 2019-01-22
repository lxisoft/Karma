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
 * IdentityProofDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-01-15T13:59:42.068056700+05:30[Asia/Calcutta]")

public class IdentityProofDTO   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("idNo")
  private String idNo = null;

  @JsonProperty("identityProofTypeId")
  private Long identityProofTypeId = null;

  public IdentityProofDTO id(Long id) {
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

  public IdentityProofDTO idNo(String idNo) {
    this.idNo = idNo;
    return this;
  }

  /**
   * Get idNo
   * @return idNo
  **/
  @ApiModelProperty(value = "")


  public String getIdNo() {
    return idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public IdentityProofDTO identityProofTypeId(Long identityProofTypeId) {
    this.identityProofTypeId = identityProofTypeId;
    return this;
  }

  /**
   * Get identityProofTypeId
   * @return identityProofTypeId
  **/
  @ApiModelProperty(value = "")


  public Long getIdentityProofTypeId() {
    return identityProofTypeId;
  }

  public void setIdentityProofTypeId(Long identityProofTypeId) {
    this.identityProofTypeId = identityProofTypeId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdentityProofDTO identityProofDTO = (IdentityProofDTO) o;
    return Objects.equals(this.id, identityProofDTO.id) &&
        Objects.equals(this.idNo, identityProofDTO.idNo) &&
        Objects.equals(this.identityProofTypeId, identityProofDTO.identityProofTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idNo, identityProofTypeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IdentityProofDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idNo: ").append(toIndentedString(idNo)).append("\n");
    sb.append("    identityProofTypeId: ").append(toIndentedString(identityProofTypeId)).append("\n");
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


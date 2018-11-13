package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.lxisoft.client.karma.model.LoggedUserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VerificationTeamDTO
 */
@Validated
<<<<<<< HEAD
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-09T14:03:13.373+05:30[Asia/Calcutta]")
=======
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3

public class VerificationTeamDTO   {
  @JsonProperty("approvalLevel")
  private String approvalLevel = null;

  @JsonProperty("approvingUsers")
  @Valid
  private List<LoggedUserDTO> approvingUsers = null;

  @JsonProperty("id")
  private Long id = null;

  public VerificationTeamDTO approvalLevel(String approvalLevel) {
    this.approvalLevel = approvalLevel;
    return this;
  }

  /**
   * Get approvalLevel
   * @return approvalLevel
  **/
  @ApiModelProperty(value = "")


  public String getApprovalLevel() {
    return approvalLevel;
  }

  public void setApprovalLevel(String approvalLevel) {
    this.approvalLevel = approvalLevel;
  }

  public VerificationTeamDTO approvingUsers(List<LoggedUserDTO> approvingUsers) {
    this.approvingUsers = approvingUsers;
    return this;
  }

  public VerificationTeamDTO addApprovingUsersItem(LoggedUserDTO approvingUsersItem) {
    if (this.approvingUsers == null) {
      this.approvingUsers = new ArrayList<LoggedUserDTO>();
    }
    this.approvingUsers.add(approvingUsersItem);
    return this;
  }

  /**
   * Get approvingUsers
   * @return approvingUsers
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<LoggedUserDTO> getApprovingUsers() {
    return approvingUsers;
  }

  public void setApprovingUsers(List<LoggedUserDTO> approvingUsers) {
    this.approvingUsers = approvingUsers;
  }

  public VerificationTeamDTO id(Long id) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerificationTeamDTO verificationTeamDTO = (VerificationTeamDTO) o;
    return Objects.equals(this.approvalLevel, verificationTeamDTO.approvalLevel) &&
        Objects.equals(this.approvingUsers, verificationTeamDTO.approvingUsers) &&
        Objects.equals(this.id, verificationTeamDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(approvalLevel, approvingUsers, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VerificationTeamDTO {\n");
    
    sb.append("    approvalLevel: ").append(toIndentedString(approvalLevel)).append("\n");
    sb.append("    approvingUsers: ").append(toIndentedString(approvingUsers)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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


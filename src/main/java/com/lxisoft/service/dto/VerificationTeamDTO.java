package com.lxisoft.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the VerificationTeam entity.
 */
public class VerificationTeamDTO implements Serializable {

    private Long id;

    private String approvalLevel;

    private Set<LoggedUserDTO> approvingUsers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(String approvalLevel) {
        this.approvalLevel = approvalLevel;
    }

    public Set<LoggedUserDTO> getApprovingUsers() {
        return approvingUsers;
    }

    public void setApprovingUsers(Set<LoggedUserDTO> loggedUsers) {
        this.approvingUsers = loggedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VerificationTeamDTO verificationTeamDTO = (VerificationTeamDTO) o;
        if (verificationTeamDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), verificationTeamDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VerificationTeamDTO{" +
            "id=" + getId() +
            ", approvalLevel='" + getApprovalLevel() + "'" +
            "}";
    }
}

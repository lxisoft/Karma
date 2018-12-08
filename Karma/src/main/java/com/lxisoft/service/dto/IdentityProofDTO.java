package com.lxisoft.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the IdentityProof entity.
 */
public class IdentityProofDTO implements Serializable {

    private Long id;

    private String idNo;

    private Long identityProofTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Long getIdentityProofTypeId() {
        return identityProofTypeId;
    }

    public void setIdentityProofTypeId(Long identityProofTypeId) {
        this.identityProofTypeId = identityProofTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IdentityProofDTO identityProofDTO = (IdentityProofDTO) o;
        if (identityProofDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), identityProofDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IdentityProofDTO{" +
            "id=" + getId() +
            ", idNo='" + getIdNo() + "'" +
            ", identityProofType=" + getIdentityProofTypeId() +
            "}";
    }
}

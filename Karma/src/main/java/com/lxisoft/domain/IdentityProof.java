package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * IdentityProof entity.
 * @author Sarangi Balu
 */
@ApiModel(description = "IdentityProof entity. @author Sarangi Balu")
@Entity
@Table(name = "identity_proof")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class IdentityProof implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_no")
    private String idNo;

    @ManyToOne
    @JsonIgnoreProperties("")
    private IdentityProofType identityProofType;

    @OneToOne(mappedBy = "idProof")
    @JsonIgnore
    private RegisteredUser owner;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNo() {
        return idNo;
    }

    public IdentityProof idNo(String idNo) {
        this.idNo = idNo;
        return this;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public IdentityProofType getIdentityProofType() {
        return identityProofType;
    }

    public IdentityProof identityProofType(IdentityProofType identityProofType) {
        this.identityProofType = identityProofType;
        return this;
    }

    public void setIdentityProofType(IdentityProofType identityProofType) {
        this.identityProofType = identityProofType;
    }

    public RegisteredUser getOwner() {
        return owner;
    }

    public IdentityProof owner(RegisteredUser registeredUser) {
        this.owner = registeredUser;
        return this;
    }

    public void setOwner(RegisteredUser registeredUser) {
        this.owner = registeredUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdentityProof identityProof = (IdentityProof) o;
        if (identityProof.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), identityProof.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IdentityProof{" +
            "id=" + getId() +
            ", idNo='" + getIdNo() + "'" +
            "}";
    }
}

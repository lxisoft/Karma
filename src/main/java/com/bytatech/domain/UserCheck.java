package com.bytatech.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * UserCheck entity
 * @author Deepthi E
 */
@ApiModel(description = "UserCheck entity @author Deepthi E")
@Entity
@Table(name = "user_check")
public class UserCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vote_type")
    private String voteType;

    @Column(name = "category")
    private String category;

    @ManyToOne
    private Need need;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoteType() {
        return voteType;
    }

    public UserCheck voteType(String voteType) {
        this.voteType = voteType;
        return this;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public String getCategory() {
        return category;
    }

    public UserCheck category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Need getNeed() {
        return need;
    }

    public UserCheck need(Need need) {
        this.need = need;
        return this;
    }

    public void setNeed(Need need) {
        this.need = need;
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
        UserCheck userCheck = (UserCheck) o;
        if (userCheck.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userCheck.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserCheck{" +
            "id=" + getId() +
            ", voteType='" + getVoteType() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
}

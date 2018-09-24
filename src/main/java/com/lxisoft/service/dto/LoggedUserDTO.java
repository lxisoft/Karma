package com.lxisoft.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the LoggedUser entity.
 */
public class LoggedUserDTO implements Serializable {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private Long rating;

    private String description;

    private String profession;

    private String gender;

    private LocalDate dob;

    private String bloodGroup;

    private Long profilePicId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Long getProfilePicId() {
        return profilePicId;
    }

    public void setProfilePicId(Long mediaId) {
        this.profilePicId = mediaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoggedUserDTO loggedUserDTO = (LoggedUserDTO) o;
        if(loggedUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loggedUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoggedUserDTO{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", rating=" + getRating() +
            ", description='" + getDescription() + "'" +
            ", profession='" + getProfession() + "'" +
            ", gender='" + getGender() + "'" +
            ", dob='" + getDob() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            "}";
    }
}

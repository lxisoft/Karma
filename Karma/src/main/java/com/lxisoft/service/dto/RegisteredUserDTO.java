package com.lxisoft.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RegisteredUser entity.
 */
public class RegisteredUserDTO implements Serializable {

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

    private Long emotionalQuotient;

    private Long socialQuotient;

    private Long happinessIndex;

    private Long profilePicId;
    
    private Long noOfHelps;
    
    private Long noOfNeeds;

    private Long idProofId;
    
    private String imageMedia;
    

    /**
	 * @return the noOfHelps
	 */
	public Long getNoOfHelps() {
		return noOfHelps;
	}

	/**
	 * @param noOfHelps the noOfHelps to set
	 */
	public void setNoOfHelps(Long noOfHelps) {
		this.noOfHelps = noOfHelps;
	}

	/**
	 * @return the noOfNeeds
	 */
	public Long getNoOfNeeds() {
		return noOfNeeds;
	}

	/**
	 * @param noOfNeeds the noOfNeeds to set
	 */
	public void setNoOfNeeds(Long noOfNeeds) {
		this.noOfNeeds = noOfNeeds;
	}

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

    public Long getEmotionalQuotient() {
        return emotionalQuotient;
    }

    public void setEmotionalQuotient(Long emotionalQuotient) {
        this.emotionalQuotient = emotionalQuotient;
    }

    public Long getSocialQuotient() {
        return socialQuotient;
    }

    public void setSocialQuotient(Long socialQuotient) {
        this.socialQuotient = socialQuotient;
    }

    public Long getHappinessIndex() {
        return happinessIndex;
    }

    public void setHappinessIndex(Long happinessIndex) {
        this.happinessIndex = happinessIndex;
    }

    public Long getProfilePicId() {
        return profilePicId;
    }

    public void setProfilePicId(Long mediaId) {
        this.profilePicId = mediaId;
    }

    public Long getIdProofId() {
        return idProofId;
    }

    public void setIdProofId(Long identityProofId) {
        this.idProofId = identityProofId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegisteredUserDTO registeredUserDTO = (RegisteredUserDTO) o;
        if (registeredUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), registeredUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegisteredUserDTO{" +
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
            ", emotionalQuotient=" + getEmotionalQuotient() +
            ", socialQuotient=" + getSocialQuotient() +
            ", happinessIndex=" + getHappinessIndex() +
            ", profilePic=" + getProfilePicId() +
            ", idProof=" + getIdProofId() +
            "}";
    }

	public String getImageMedia() {
		return imageMedia;
	}

	public void setImageMedia(String imageMedia) {
		this.imageMedia = imageMedia;
	}
}

package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * LoggedUser entity.
 * @author Muhammed Ruhail
 */
@ApiModel(description = "LoggedUser entity. @author Muhammed Ruhail")
@Entity
@Table(name = "logged_user")
public class LoggedUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "description")
    private String description;

    @Column(name = "profession")
    private String profession;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "blood_group")
    private String bloodGroup;

    @OneToOne
    @JoinColumn(unique = true)
    private Media profilePic;

    @OneToMany(mappedBy = "loggedUser")
    @JsonIgnore
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "postedUser")
    @JsonIgnore
    private Set<Need> needs = new HashSet<>();

    @OneToMany(mappedBy = "providedUser")
    @JsonIgnore
    private Set<Help> helps = new HashSet<>();

    @ManyToMany(mappedBy = "approvingUsers")
    @JsonIgnore
    private Set<VerificationTeam> verificationTeams = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public LoggedUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public LoggedUser firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LoggedUser lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getRating() {
        return rating;
    }

    public LoggedUser rating(Long rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public LoggedUser description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfession() {
        return profession;
    }

    public LoggedUser profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGender() {
        return gender;
    }

    public LoggedUser gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public LoggedUser dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public LoggedUser bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Media getProfilePic() {
        return profilePic;
    }

    public LoggedUser profilePic(Media media) {
        this.profilePic = media;
        return this;
    }

    public void setProfilePic(Media media) {
        this.profilePic = media;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public LoggedUser addresses(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public LoggedUser addAddresses(Address address) {
        this.addresses.add(address);
        address.setLoggedUser(this);
        return this;
    }

    public LoggedUser removeAddresses(Address address) {
        this.addresses.remove(address);
        address.setLoggedUser(null);
        return this;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Need> getNeeds() {
        return needs;
    }

    public LoggedUser needs(Set<Need> needs) {
        this.needs = needs;
        return this;
    }

    public LoggedUser addNeeds(Need need) {
        this.needs.add(need);
        need.setPostedUser(this);
        return this;
    }

    public LoggedUser removeNeeds(Need need) {
        this.needs.remove(need);
        need.setPostedUser(null);
        return this;
    }

    public void setNeeds(Set<Need> needs) {
        this.needs = needs;
    }

    public Set<Help> getHelps() {
        return helps;
    }

    public LoggedUser helps(Set<Help> helps) {
        this.helps = helps;
        return this;
    }

    public LoggedUser addHelps(Help help) {
        this.helps.add(help);
        help.setProvidedUser(this);
        return this;
    }

    public LoggedUser removeHelps(Help help) {
        this.helps.remove(help);
        help.setProvidedUser(null);
        return this;
    }

    public void setHelps(Set<Help> helps) {
        this.helps = helps;
    }

    public Set<VerificationTeam> getVerificationTeams() {
        return verificationTeams;
    }

    public LoggedUser verificationTeams(Set<VerificationTeam> verificationTeams) {
        this.verificationTeams = verificationTeams;
        return this;
    }

    public LoggedUser addVerificationTeams(VerificationTeam verificationTeam) {
        this.verificationTeams.add(verificationTeam);
        verificationTeam.getApprovingUsers().add(this);
        return this;
    }

    public LoggedUser removeVerificationTeams(VerificationTeam verificationTeam) {
        this.verificationTeams.remove(verificationTeam);
        verificationTeam.getApprovingUsers().remove(this);
        return this;
    }

    public void setVerificationTeams(Set<VerificationTeam> verificationTeams) {
        this.verificationTeams = verificationTeams;
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
        LoggedUser loggedUser = (LoggedUser) o;
        if (loggedUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loggedUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoggedUser{" +
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

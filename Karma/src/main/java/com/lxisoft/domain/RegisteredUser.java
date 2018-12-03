package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * RegisteredUser entity.
 * @author Muhammed Ruhail
 */
@ApiModel(description = "RegisteredUser entity. @author Muhammed Ruhail")
@Entity
@Table(name = "registered_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RegisteredUser implements Serializable {

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

    @Column(name = "emotional_quotient")
    private Long emotionalQuotient;

    @Column(name = "social_quotient")
    private Long socialQuotient;

    @Column(name = "happiness_index")
    private Long happinessIndex;

    @OneToOne    @JoinColumn(unique = true)
    private Media profilePic;

    @OneToOne    @JoinColumn(unique = true)
    private IdentityProof idProof;

    @OneToMany(mappedBy = "registeredUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Address> addresses = new HashSet<>();
    @OneToMany(mappedBy = "postedUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Need> needs = new HashSet<>();
    @OneToMany(mappedBy = "providedUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Help> helps = new HashSet<>();
    @OneToMany(mappedBy = "registeredUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Post> posts = new HashSet<>();
    @OneToMany(mappedBy = "registeredUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Feed> feeds = new HashSet<>();
    @OneToMany(mappedBy = "checkedUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserCheck> checkedNeeds = new HashSet<>();
    @ManyToMany(mappedBy = "approvingUsers")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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

    public RegisteredUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public RegisteredUser firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisteredUser lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getRating() {
        return rating;
    }

    public RegisteredUser rating(Long rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public RegisteredUser description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfession() {
        return profession;
    }

    public RegisteredUser profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGender() {
        return gender;
    }

    public RegisteredUser gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public RegisteredUser dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public RegisteredUser bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Long getEmotionalQuotient() {
        return emotionalQuotient;
    }

    public RegisteredUser emotionalQuotient(Long emotionalQuotient) {
        this.emotionalQuotient = emotionalQuotient;
        return this;
    }

    public void setEmotionalQuotient(Long emotionalQuotient) {
        this.emotionalQuotient = emotionalQuotient;
    }

    public Long getSocialQuotient() {
        return socialQuotient;
    }

    public RegisteredUser socialQuotient(Long socialQuotient) {
        this.socialQuotient = socialQuotient;
        return this;
    }

    public void setSocialQuotient(Long socialQuotient) {
        this.socialQuotient = socialQuotient;
    }

    public Long getHappinessIndex() {
        return happinessIndex;
    }

    public RegisteredUser happinessIndex(Long happinessIndex) {
        this.happinessIndex = happinessIndex;
        return this;
    }

    public void setHappinessIndex(Long happinessIndex) {
        this.happinessIndex = happinessIndex;
    }

    public Media getProfilePic() {
        return profilePic;
    }

    public RegisteredUser profilePic(Media media) {
        this.profilePic = media;
        return this;
    }

    public void setProfilePic(Media media) {
        this.profilePic = media;
    }

    public IdentityProof getIdProof() {
        return idProof;
    }

    public RegisteredUser idProof(IdentityProof identityProof) {
        this.idProof = identityProof;
        return this;
    }

    public void setIdProof(IdentityProof identityProof) {
        this.idProof = identityProof;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public RegisteredUser addresses(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public RegisteredUser addAddresses(Address address) {
        this.addresses.add(address);
        address.setRegisteredUser(this);
        return this;
    }

    public RegisteredUser removeAddresses(Address address) {
        this.addresses.remove(address);
        address.setRegisteredUser(null);
        return this;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Need> getNeeds() {
        return needs;
    }

    public RegisteredUser needs(Set<Need> needs) {
        this.needs = needs;
        return this;
    }

    public RegisteredUser addNeeds(Need need) {
        this.needs.add(need);
        need.setPostedUser(this);
        return this;
    }

    public RegisteredUser removeNeeds(Need need) {
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

    public RegisteredUser helps(Set<Help> helps) {
        this.helps = helps;
        return this;
    }

    public RegisteredUser addHelps(Help help) {
        this.helps.add(help);
        help.setProvidedUser(this);
        return this;
    }

    public RegisteredUser removeHelps(Help help) {
        this.helps.remove(help);
        help.setProvidedUser(null);
        return this;
    }

    public void setHelps(Set<Help> helps) {
        this.helps = helps;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public RegisteredUser posts(Set<Post> posts) {
        this.posts = posts;
        return this;
    }

    public RegisteredUser addPosts(Post post) {
        this.posts.add(post);
        post.setRegisteredUser(this);
        return this;
    }

    public RegisteredUser removePosts(Post post) {
        this.posts.remove(post);
        post.setRegisteredUser(null);
        return this;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Feed> getFeeds() {
        return feeds;
    }

    public RegisteredUser feeds(Set<Feed> feeds) {
        this.feeds = feeds;
        return this;
    }

    public RegisteredUser addFeeds(Feed feed) {
        this.feeds.add(feed);
        feed.setRegisteredUser(this);
        return this;
    }

    public RegisteredUser removeFeeds(Feed feed) {
        this.feeds.remove(feed);
        feed.setRegisteredUser(null);
        return this;
    }

    public void setFeeds(Set<Feed> feeds) {
        this.feeds = feeds;
    }

    public Set<UserCheck> getCheckedNeeds() {
        return checkedNeeds;
    }

    public RegisteredUser checkedNeeds(Set<UserCheck> userChecks) {
        this.checkedNeeds = userChecks;
        return this;
    }

    public RegisteredUser addCheckedNeeds(UserCheck userCheck) {
        this.checkedNeeds.add(userCheck);
        userCheck.setCheckedUser(this);
        return this;
    }

    public RegisteredUser removeCheckedNeeds(UserCheck userCheck) {
        this.checkedNeeds.remove(userCheck);
        userCheck.setCheckedUser(null);
        return this;
    }

    public void setCheckedNeeds(Set<UserCheck> userChecks) {
        this.checkedNeeds = userChecks;
    }

    public Set<VerificationTeam> getVerificationTeams() {
        return verificationTeams;
    }

    public RegisteredUser verificationTeams(Set<VerificationTeam> verificationTeams) {
        this.verificationTeams = verificationTeams;
        return this;
    }

    public RegisteredUser addVerificationTeams(VerificationTeam verificationTeam) {
        this.verificationTeams.add(verificationTeam);
        verificationTeam.getApprovingUsers().add(this);
        return this;
    }

    public RegisteredUser removeVerificationTeams(VerificationTeam verificationTeam) {
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
        RegisteredUser registeredUser = (RegisteredUser) o;
        if (registeredUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), registeredUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
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
            "}";
    }
}

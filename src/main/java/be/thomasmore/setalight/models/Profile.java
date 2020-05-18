package be.thomasmore.setalight.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Profile {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_generator")
    @SequenceGenerator(name = "profile_generator", sequenceName = "profile_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    private Integer id;
    private Date birthDate;
    private String profilePicture;
    private String email;
    private String hairColor;
    private String fullPicture;
    private String physicalAttributes;
    private String nationalInsuranceNumber;
    private double length;
    @ManyToMany
    private Collection<User> friends;
    private String height;
    @OneToOne(fetch = FetchType.LAZY)
    private User userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String haircolor) {
        this.hairColor = haircolor;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilepicture) {
        this.profilePicture = profilepicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullPicture() {
        return fullPicture;
    }

    public void setFullPicture(String fullpicture) {
        this.fullPicture = fullpicture;
    }

    public String getPhysicalAttributes() {
        return physicalAttributes;
    }

    public void setPhysicalAttributes(String physicalAtributes) {
        this.physicalAttributes = physicalAtributes;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Collection<User> getFriends() {
        return friends;
    }

    public void setFriends(Collection<User> friends) {
        this.friends = friends;
    }
}

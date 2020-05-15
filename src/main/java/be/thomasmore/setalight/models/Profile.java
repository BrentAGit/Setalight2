package be.thomasmore.setalight.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Profile {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_generator")
    @SequenceGenerator(name = "profile_generator", sequenceName = "profile_seq",
            initialValue = 0, allocationSize = 1)
    @Id
    private Integer id;
    private Date birthdate;
    private String profilepicture;
    private String email;
    private String haircolor;
    private String fullpicture;
    private String physicalAtributes;
    private String nationalInsuranceNumber;
    private double length;
    @ManyToMany
    private Collection<User> friends;
    private String Height;
    @OneToOne(fetch = FetchType.LAZY)
    private User userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(String haircolor) {
        this.haircolor = haircolor;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullpicture() {
        return fullpicture;
    }

    public void setFullpicture(String fullpicture) {
        this.fullpicture = fullpicture;
    }

    public String getPhysicalAtributes() {
        return physicalAtributes;
    }

    public void setPhysicalAtributes(String physicalAtributes) {
        this.physicalAtributes = physicalAtributes;
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

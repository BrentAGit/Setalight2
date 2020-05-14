package be.thomasmore.setalight.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq",
            initialValue = 0, allocationSize = 1)
    @Id
    private Integer id;
    private String username;
    private String password;
    private String role;
    private Date birthdate;
    private String profilepicture;
    private String email;
    private String haircolor;
    private String fullpicture;
    private String physicalAtributes;
    private String nationalInsuranceNumber;
    private double length;
    @ManyToMany
    private Collection<User> Frends;
    @ManyToMany
    private Collection<Event> ervaring;

    private boolean verified;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(String haircolor) {
        this.haircolor = haircolor;
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

    public Collection<User> getFrends() {
        return Frends;
    }

    public void setFrends(Collection<User> frends) {
        Frends = frends;
    }

    public Collection<Event> getErvaring() {
        return ervaring;
    }

    public void setErvaring(Collection<Event> ervaring) {
        this.ervaring = ervaring;
    }
}

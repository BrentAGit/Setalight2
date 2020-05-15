package be.thomasmore.setalight.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Profile {

    @GeneratedValue
    @Id
    private Integer id;
    private Date birthdate;
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
    private String Height;
    private String haircolor;
    @OneToOne
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
}

package be.thomasmore.setalight.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Profile {

    @GeneratedValue
    @Id
    private Integer id;
    private Date birthdate;
    private String Height;
    private String haircolor;
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
}

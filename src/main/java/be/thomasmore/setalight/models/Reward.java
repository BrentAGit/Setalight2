package be.thomasmore.setalight.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class Reward {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reward_generator")
    @SequenceGenerator(name = "reward_generator", sequenceName = "reward_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    private Integer id;
    private String name;
    private String picture;
    private int points;
    private boolean active;
    @ManyToMany
    private Collection<User> userBoughtRewardName = new ArrayList<>();
    @ElementCollection
    private Collection<Date> userBoughtRewardTime = new ArrayList<>();

    public Reward() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<User> getUserBoughtRewardName() {
        return userBoughtRewardName;
    }

    public void setUserBoughtRewardName(Collection<User> userBoughtRewardName) {
        this.userBoughtRewardName = userBoughtRewardName;
    }

    public Collection<Date> getUserBoughtRewardTime() {
        return userBoughtRewardTime;
    }

    public void setUserBoughtRewardTime(Collection<Date> userBoughtRewardTime) {
        this.userBoughtRewardTime = userBoughtRewardTime;
    }
}

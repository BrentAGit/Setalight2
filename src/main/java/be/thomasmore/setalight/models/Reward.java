package be.thomasmore.setalight.models;

import javax.persistence.*;

public class Reward {

    @Id
    private Integer id;
    private String name;
    private int points;

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

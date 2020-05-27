package be.thomasmore.setalight.models;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

@Entity
public class Event {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
    @SequenceGenerator(name = "event_generator", sequenceName = "event_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    private Integer id;
    private String name;
    private String description;
    private int amountOfParticipants;
    private Date date;
    private boolean control;
    private String postcode;
    private String city;
    private String street;
    private String houseNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private String typeWanted;
    private String picture;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private Collection<User> users;

    public Integer getId() {
        return id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountOfParticipants() {
        return amountOfParticipants;
    }

    public void setAmountOfParticipants(int amountOfParticipants) {
        this.amountOfParticipants = amountOfParticipants;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String housenumber) {
        this.houseNumber = housenumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public String getTypeWanted() {
        return typeWanted;
    }

    public void setTypeWanted(String typeWanted) {
        this.typeWanted = typeWanted;
    }

    public String getDateString() {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(getDate());
    }
    public String getDateStringEvent() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(getDate());
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCityAndPostcode() {
        return String.format(" stad : %s %s ", getCity(), getPostcode());
    }
    public String getStreetAndNumber() {
        return String.format(" straat : %s %s ", getStreet(), getHouseNumber());
    }


    public Integer countUsers() {
        return getUsers().size();
    }
}
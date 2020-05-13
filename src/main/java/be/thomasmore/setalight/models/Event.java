package be.thomasmore.setalight.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

@Entity
public class Event {
    @GeneratedValue
    @Id
    private int id;
    private String name;
    private String description;
    private int aantaldeelnemers;
    private String adres;
    private Date datum;
    private Time startTime;
    private Time endTime;
    private boolean controle;
    @ManyToMany
    private Collection<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAantaldeelnemers() {
        return aantaldeelnemers;
    }

    public void setAantaldeelnemers(int aantaldeelnemers) {
        this.aantaldeelnemers = aantaldeelnemers;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getDatum() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(datum);
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean isControle() {
        return controle;
    }

    public void setControle(boolean controle) {
        this.controle = controle;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}

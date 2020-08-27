package be.thomasmore.setalight.models;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private double rewardPoints;
    @ManyToMany
    private Collection<Profile> friends;
    private String height;
    @OneToOne(fetch = FetchType.LAZY)
    private User userId;
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Event> checkedEvents = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Event> invitedEvents = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Profile> invitedBy = new ArrayList<>();
    @OneToMany
    private Collection<Event> checkedCanceledEvents = new ArrayList<>();
    @OneToMany
    private Collection<Profile> alreadyInvitedUsers = new ArrayList<>();
    @OneToMany
    private Collection<Event> skippedEvents = new ArrayList<>();

    public Profile() {
    }

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

    public Collection<Profile> getFriends() {
        return friends;
    }

    public ArrayList<Profile> getArrayFriends() {
        return new ArrayList<>(this.friends);
    }

    public void setFriends(Collection<Profile> friends) {
        this.friends = friends;
    }

    public Collection<Event> getCheckedEvents() {
        return checkedEvents;
    }

    public void setCheckedEvents(Collection<Event> checkedEvents) {
        this.checkedEvents = checkedEvents;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Collection<Event> getInvitedEvents() {
        return invitedEvents;
    }

    public void setInvitedEvents(Collection<Event> invitedEvents) {
        this.invitedEvents = invitedEvents;
    }

    public Collection<Profile> getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(Collection<Profile> invitedBy) {
        this.invitedBy = invitedBy;
    }

    public String getBirthDateString() {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(getBirthDate());
    }

    public String getBirthDateStringProfile() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(getBirthDate());
    }

    public Collection<Event> getCheckedCanceledEvents() {
        return checkedCanceledEvents;
    }

    public void setCheckedCanceledEvents(Collection<Event> checkedCanceledEvents) {
        this.checkedCanceledEvents = checkedCanceledEvents;
    }

    public Collection<Profile> getAlreadyInvitedUsers() {
        return alreadyInvitedUsers;
    }

    public void setAlreadyInvitedUsers(Collection<Profile> alreadyInvitedUsers) {
        this.alreadyInvitedUsers = alreadyInvitedUsers;
    }

    public Collection<Event> getSkippedEvents() {
        return skippedEvents;
    }

    public void setSkippedEvents(Collection<Event> skippedEvents) {
        this.skippedEvents = skippedEvents;
    }
}

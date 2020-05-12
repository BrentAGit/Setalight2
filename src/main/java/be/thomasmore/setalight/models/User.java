package be.thomasmore.setalight.models;

import javax.persistence.*;

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
    private double length;
    private String haircolor;
    private String fullpicture;
    private String physicalAtributes;
    private String rijksregisternummer;
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
}

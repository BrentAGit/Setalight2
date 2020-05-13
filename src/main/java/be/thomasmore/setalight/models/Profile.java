package be.thomasmore.setalight.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Profile {

    @GeneratedValue
    @Id
    private Integer id;
    private Date birthdate;
    private String Height;
    private String haircolor;
    @OneToOne
    private User userId;

}

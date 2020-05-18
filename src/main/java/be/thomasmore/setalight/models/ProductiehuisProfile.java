package be.thomasmore.setalight.models;

import javax.persistence.*;

@Entity
public class ProductiehuisProfile {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productiehuisProfile_generator")
    @SequenceGenerator(name = "productiehuisProfile_generator", sequenceName = "productiehuisProfile_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    Integer id;
    String description;
    String province;
    String city;
    String postalCode;
    String street;
    String houseNumber;
    String companyNumber;
    String nameOwner;
    @OneToOne
    User userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }
}

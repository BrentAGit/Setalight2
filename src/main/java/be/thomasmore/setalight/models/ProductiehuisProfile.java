package be.thomasmore.setalight.models;

import javax.persistence.*;

@Entity
public class ProductiehuisProfile {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productiehuisProfile_generator")
    @SequenceGenerator(name = "productiehuisProfile_generator", sequenceName = "productiehuisProfile_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    Integer id;
    private String nameCompany;
    private String description;
    private String province;
    private String city;
    private String postalCode;
    private String street;
    private String houseNumber;
    private String companyNumber;
    private String nameOwner;
    private String logo;
    @OneToOne(fetch = FetchType.LAZY)
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

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
    public String getCityAndPostcode() {
        return String.format(" stad : %s %s ", getCity(), getPostalCode());
    }
    public String getStreetAndNumber() {
        return String.format(" straat : %s %s ", getStreet(), getHouseNumber());
    }

}

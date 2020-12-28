package com.ulrictodman.portfoliobackend.model;

import com.ulrictodman.portfoliobackend.enumeration.ContactRole;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ContactInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String addressLine1;
    @Nullable
    private String addressLine2;
    @Nullable
    private String aptOrSuite;
    @Size(max = 5)
    @NotNull
    private String zipCode;
    @NotNull
    private String city;
    @NotNull
    private String state;

    @Email
    private String email;
    @NotNull
    private ContactRole role;


    public ContactInformation() {
    }

    public ContactInformation(int id, @NotNull String firstName, @NotNull String lastName, @NotNull String addressLine1, @Nullable String addressLine2, @Nullable String aptOrSuite, @Size(max = 5) @NotNull String zipCode, @NotNull String city, @NotNull String state, @Email String email, @NotNull ContactRole role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.aptOrSuite = aptOrSuite;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Nullable
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(@Nullable String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Nullable
    public String getAptOrSuite() {
        return aptOrSuite;
    }

    public void setAptOrSuite(@Nullable String aptOrSuite) {
        this.aptOrSuite = aptOrSuite;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ContactRole getRole() {
        return role;
    }

    public void setRole(ContactRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInformation that = (ContactInformation) o;
        return getId() == that.getId() &&
                getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                getAddressLine1().equals(that.getAddressLine1()) &&
                Objects.equals(getAddressLine2(), that.getAddressLine2()) &&
                Objects.equals(getAptOrSuite(), that.getAptOrSuite()) &&
                getZipCode().equals(that.getZipCode()) &&
                getCity().equals(that.getCity()) &&
                getState().equals(that.getState()) &&
                getEmail().equals(that.getEmail()) &&
                getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddressLine1(), getAddressLine2(), getAptOrSuite(), getZipCode(), getCity(), getState(), getEmail(), getRole());
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", aptOrSuite='" + aptOrSuite + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

}

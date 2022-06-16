package com.example.take_project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"packages"})
public class Client implements EntityBaseInterface {
    @Id
    @GeneratedValue
    private Long id;

    private String lastName;

    private String address;

    private String telephoneNumber;

    private String firstName;

    @OneToMany
    private List<ClientPackage> packages;

    public Client() {
    }

    public Long getId() {
        return id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ClientPackage> getPackages() {
        return packages;
    }

    public void setPackages(List<ClientPackage> packages) {
        this.packages = packages;
    }
}

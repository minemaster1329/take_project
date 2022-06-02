package  com.example.take_project.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Schema(name = "Car", description = "Stores info about cars")
public class Car implements EntityBaseInterface {
    @Id
    @GeneratedValue
    private Long id;

    private String brand;

    private String licensePlateNr;

    private String ownerFirstLastName;

    private String telephoneNr;

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLicensePlateNr() {
        return licensePlateNr;
    }

    public void setLicensePlateNr(String licensePlateNr) {
        this.licensePlateNr = licensePlateNr;
    }

    public String getOwnerFirstLastName() {
        return ownerFirstLastName;
    }

    public void setOwnerFirstLastName(String ownerFirstLastName) {
        this.ownerFirstLastName = ownerFirstLastName;
    }

    public String getTelephoneNr() {
        return telephoneNr;
    }

    public void setTelephoneNr(String telephoneNr) {
        this.telephoneNr = telephoneNr;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

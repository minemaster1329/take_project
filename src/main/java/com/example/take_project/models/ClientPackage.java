package com.example.take_project.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Schema(name = "ClientPackage", description = "Stores info about client package")
public class ClientPackage implements EntityBaseInterface {
    @Id
    @GeneratedValue
    private Long id;

    private String deliveryAddress;

    private String type;

    private Integer weight;

    private Boolean isPaidFor;

    // precision = max number of digits
    // scale = number of digits to the right of decimal point
    @Column(precision=7, scale=2)
    private BigDecimal price;

    private Date estimatedDeliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client packageOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;


    public ClientPackage() {
    }

    public Long getId() {
        return id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getPaidFor() {
        return isPaidFor;
    }

    public void setPaidFor(Boolean paidFor) {
        isPaidFor = paidFor;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Client getPackageOwner() {
        return packageOwner;
    }

    public void setPackageOwner(Client packageOwner) {
        this.packageOwner = packageOwner;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

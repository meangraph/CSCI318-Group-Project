package com.example.ProcurementMicroservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

/** This is the class responsible for creating objects of type Contact. Contact objects are employees that are a part of Supplier objects*/

@Entity
@Table(name = "Contact")
@JsonPropertyOrder({"id"})//This makes it so the auto generated ID appears first
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String name;

    private String phone;

    private String email;

    private String position;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    public Contact(@JsonProperty("name") String name,
                   @JsonProperty("phone") String phone,
                   @JsonProperty("email") String email,
                   @JsonProperty("position") String position) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public Contact(){}

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    @Override
    public String toString() {
        return "Contact{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", supplier=" + supplier +
                '}';
    }
}

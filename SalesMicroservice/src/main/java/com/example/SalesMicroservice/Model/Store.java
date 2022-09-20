package com.example.SalesMicroservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Store")
@JsonPropertyOrder({"id"})
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private int managerID;
    private String address;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn
    @JsonIgnore
    List<InStoreSale> sales = new ArrayList<>();

    public Store(@JsonProperty("manager") int managerID,
                 @JsonProperty("address") String address) {
        this.managerID = managerID;
        this.address = address;
    }

    public Store() {
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<InStoreSale> getSales() {
        return sales;
    }

    public void setSales(List<InStoreSale> sales) {
        this.sales = sales;
    }

    public void addSales(InStoreSale sale) {
        sales.add(sale);
    }

}


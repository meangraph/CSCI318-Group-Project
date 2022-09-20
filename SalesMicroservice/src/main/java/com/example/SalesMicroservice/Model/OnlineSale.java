package com.example.SalesMicroservice.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;

@Entity
@JsonPropertyOrder({"id"})
public class OnlineSale extends Sale{

    private String customerName;
    private String address;



    public OnlineSale(@JsonProperty("productName")String productName,
                      @JsonProperty("quantity") int quantity,
                      @JsonProperty("customerName") String customerName,
                      @JsonProperty("address") String address) {
        super(productName, quantity);
        this.customerName = customerName;
        this.address = address;
    }

    public OnlineSale() {}

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

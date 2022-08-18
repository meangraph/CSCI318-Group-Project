package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


//Might need to redo this one. To make it easier, i put a bool flag called "online" to denote weather it's online or offline.
public class Sale {

    @NotBlank
    String productName;
    @NotBlank
    int quantity;
    @NotBlank
    boolean online;

    //instore vars
    int reciptNumber;
    Store store;

    //online vars
    String customerName;

    //In person constructor
    public Sale(@JsonProperty String productName, @JsonProperty int quantity, @JsonProperty int reciptNumber, Store store){
        this.productName = productName;
        this.quantity = quantity;
        this.reciptNumber = reciptNumber;
        this.store = store;
        online = false;
    }

    //Online constructor
    public Sale(@JsonProperty String productName, @JsonProperty int quantity, @JsonProperty String customerName){
        this.productName = productName;
        this.quantity = quantity;
        this.customerName = customerName;
        online = true;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isOnline() {
        return online;
    }

    public int getReciptNumber() {
        return reciptNumber;
    }

    public Store getStore() {
        return store;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }
}

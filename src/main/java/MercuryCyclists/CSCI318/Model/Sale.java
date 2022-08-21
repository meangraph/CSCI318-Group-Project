package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


//Might need to redo this one. To make it easier, i put a bool flag called "online" to denote weather it's online or offline.
@Entity(name = "Sale")
public class Sale {

    @NotBlank
    String productName;
    @NotBlank
    int quantity;
    @NotBlank
    boolean online;

    @NotBlank
    @Id
    long reciptNumber;
    //instore vars

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    Store store;

    //online vars
    String customerName;

    //In person constructor
    public Sale(@JsonProperty String productName, @JsonProperty int quantity, @JsonProperty long reciptNumber, Store store){
        this.productName = productName;
        this.quantity = quantity;
        this.reciptNumber = reciptNumber;
        this.store = store;
        online = false;
    }

    public Sale(){}

    //Online constructor
    public Sale(@JsonProperty String productName, @JsonProperty int quantity, @JsonProperty String customerName, @JsonProperty long reciptNumber){
        this.productName = productName;
        this.quantity = quantity;
        this.customerName = customerName;
        this.reciptNumber = reciptNumber;
        online = true;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isOnline() {
        return online;
    }

    public long getReciptNumber() {
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

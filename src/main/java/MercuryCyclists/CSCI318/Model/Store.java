package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class Store {

    //Not sure what to put in as a manager object, so i just put in a numbered ID.
    @NotBlank
    private int managerID;
    @NotBlank
    private Address address;

    List<Sale> sales;

    public Store(@JsonProperty int managerID, Address address){
        this.managerID = managerID;
        this.address = address;
    }

    public Address getAddress(){
        return address;
    }

    public int getManagerID(){
        return managerID;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void addSale(Sale sale){
        sales.add(sale);
    }
}

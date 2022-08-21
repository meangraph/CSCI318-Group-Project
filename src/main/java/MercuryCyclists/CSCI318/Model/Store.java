package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;
import MercuryCyclists.CSCI318.Model.Address;

@Entity(name = "Store")
public class Store {

    //Not sure what to put in as a manager object, so i just put in a numbered ID.
    @NotBlank
    private int managerID;
    @NotBlank
    @Id
    private String address;

    @OneToMany(mappedBy = "store",
            cascade = CascadeType.ALL)
    List<Sale> sales;

    public Store(@JsonProperty int managerID, String address){
        this.managerID = managerID;
        this.address = address;
    }

    public Store(){}

    public String getAddress(){
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

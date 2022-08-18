package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class Product {
    @NotBlank
    private String name;
    @NotBlank
    private int productID;
    @NotBlank
    private double price;
    @NotBlank
    String comment;

    private List<Part> parts;

    List<Sale> sales;

    public Product(@JsonProperty String name, @JsonProperty int productID, @JsonProperty double price, @JsonProperty String comment){
        this.name = name;
        this.productID = productID;
        this.price = price;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public int getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public void addPart(Part part){
        parts.add(part);
    }
    public void addSales(Sale sale){
        sales.add(sale);
    }
}

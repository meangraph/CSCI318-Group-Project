package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


@Entity(name = "Product")
public class Product {
    @NotBlank
    private String name;
    @NotBlank
    @Id
    private long productID;
    @NotBlank
    private double price;
    @NotBlank
    String comment;

    @Transient
    private List<Optional<Part>> parts;

    @Transient
    private List<Optional<Sale>> sales;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Part> partsList;

    @OneToMany
    private List<Sale> salesList;

    public Product(@JsonProperty String name, @JsonProperty long productID, @JsonProperty double price, @JsonProperty String comment) {
        this.name = name;
        this.productID = productID;
        this.price = price;
        this.comment = comment;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public List<Optional<Part>> getParts() {
        return parts;
    }

    public long getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public void addPart(Optional<Part> part) {
        if (parts.stream().anyMatch(c -> c.get().getPartID() == part.get().getPartID())) {
            System.out.println("Found!");
            removePart(part);
        }
        parts.add(part);
        partsList.add(part.get());
    }

    public void removePart(Optional<Part> part) {
        parts.remove(part);
        partsList.remove(part);
    }


    public void addSales(Optional<Sale> sale) {
        if (sales.stream().anyMatch(c -> c.get().getReciptNumber() == sale.get().getReciptNumber())) {
            System.out.println("Found!");
            removeSale(sale);
        }
        sales.add(sale);
        salesList.add(sale.get());
    }

    public void removeSale(Optional<Sale> sale) {
        sales.remove(sale);
        salesList.remove(sale);
    }
}

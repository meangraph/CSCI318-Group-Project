
package com.example.InventoryMicroservice.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity(name = "Part")
@SequenceGenerator(name="seq", initialValue=100, allocationSize=1000)
@JsonPropertyOrder({"id"})
public class Part {

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long partID;
    private String description;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JsonBackReference
    private Supplier supplier;

    private int stock;

    public Part(@JsonProperty String name,
                @JsonProperty Long partID,
                @JsonProperty String description,
                @JsonProperty Supplier supplier,
                @JsonProperty int stock){
        this.name = name;
        this.partID = partID;
        this.description = description;
        this.supplier = supplier;
        this.stock = stock;
    }

    public Part(){}

    //Accessors
    public String getName(){ return name;}

    public Long getPartID(){return partID;}

    public String getDescription(){return description;}

    public Product getProduct() { return product; }

    public Supplier getSupplier() {return supplier;}

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartID(Long partID) {
        this.partID = partID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        //supplier.addPart(this);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

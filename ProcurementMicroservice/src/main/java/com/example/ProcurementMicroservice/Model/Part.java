
package com.example.ProcurementMicroservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity(name = "Part")
@JsonPropertyOrder({"id"})
public class Part {

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long partID;
    private String description;
    @ManyToOne
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JsonIgnore
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
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

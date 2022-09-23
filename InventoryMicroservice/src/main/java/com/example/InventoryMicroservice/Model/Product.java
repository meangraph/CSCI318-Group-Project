package com.example.InventoryMicroservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Product")
@JsonPropertyOrder({"id"})
public class Product {
    private String name;

    @Id
    private Long productID;
    private double price;
    String comment;
    private int stock;


    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Part> partsList = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    private List<Sale> salesList = new ArrayList<>();



    public Product(Long id,
                   String name,
                   double price,
                   String comment,
                   int stock) {
        this.productID = id;
        this.name = name;
        this.price = price;
        this.comment = comment;
        this.stock = stock;
    }

    @JsonIgnore
    public List<Part> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<Part> partsList) {
        this.partsList = partsList;
    }

    public List<Sale> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sale> salesList) {
        this.salesList = salesList;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public List<Part> getParts() {return partsList;
    }

    public Long getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public void addPart(Part part) {
        part.setProduct(this);
        partsList.add(part);
    }

    public void removePart(Part part) { partsList.remove(part);}


    public void addSales(Sale sale) {
       salesList.add(sale);
    }

    public void removeSale(Sale sale) {
        salesList.remove(sale);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

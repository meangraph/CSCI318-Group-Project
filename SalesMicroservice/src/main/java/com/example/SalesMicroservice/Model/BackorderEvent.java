package com.example.SalesMicroservice.Model;

import com.example.SalesMicroservice.Model.Product;
import com.example.SalesMicroservice.Model.Sale;

public class BackorderEvent {

    private Sale sale;
    private Product product;
    private String message;

    public BackorderEvent() {
    }

    public BackorderEvent(Sale sale, Product product, String message) {
        this.sale = sale;
        this.product = product;
        this.message = message;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

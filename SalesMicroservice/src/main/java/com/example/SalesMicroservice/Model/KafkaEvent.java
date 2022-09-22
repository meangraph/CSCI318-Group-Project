package com.example.SalesMicroservice.Model;

public class KafkaEvent {

    private Product product;
    private Sale sale;
    private String comment;

    public KafkaEvent() {
    }

    public KafkaEvent(Product product, Sale sale, String comment) {
        this.product = product;
        this.sale = sale;
        this.comment = comment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

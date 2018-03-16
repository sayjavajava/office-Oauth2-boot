package com.example.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public  class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    String productname;
    String Sku;
    double price;
    int quantity;
    boolean  InStock;

    public Product(Long id, String productname, String sku, double price, int quantity,boolean Instock) {
        this.id = id;
        this.productname = productname;
        Sku = sku;
        this.price = price;
        this.InStock = Instock;
        this.quantity = quantity;
    }

    public Product() {
    }

    public boolean isInStock() {
        return InStock;
    }

    public void setInStock(boolean inStock) {
        InStock = inStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getSku() {
        return Sku;
    }

    public void setSku(String sku) {
        Sku = sku;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productname='" + productname + '\'' +
                ", Sku='" + Sku + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
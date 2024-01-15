package org.DTO;

public class Product {
    private int prodId;
    private String prodName;
    private double prodPrice;
    private int prodQty;

    public Product() {
    }

    public Product(int prodId, String prodName, double prodPrice, int prodQty) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodQty = prodQty;
    }

    public Product(String prodName, int prodQty) {
        this.prodName = prodName;
        this.prodQty = prodQty;
    }

    public Product(String prodName, double prodPrice, int prodQty) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodQty = prodQty;
    }

    public Product(int prodId, String prodName) {
        this.prodId = prodId;
        this.prodName = prodName;
    }

    public Product(String prodName, double prodPrice) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public int getProdQty() {
        return prodQty;
    }

    public void setProdQty(int prodQty) {
        this.prodQty = prodQty;
    }
}

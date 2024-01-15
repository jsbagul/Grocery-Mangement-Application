package org.DTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Order {
    private int orderId;

    private User user;
    private double totalBill;
    private List<Product> productList=new LinkedList<>();


    public Order() {
    }

    public Order(User user) {
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }
}

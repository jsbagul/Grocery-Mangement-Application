package org.DAO;

import org.DTO.Product;

import java.util.List;

public interface Service {
    public List<Product> showAllProduct();
    public int removeProudct(int prodId);
    public List<Product> showByName(String name);
}

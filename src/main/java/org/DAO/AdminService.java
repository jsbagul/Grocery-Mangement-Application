package org.DAO;

import org.DTO.Product;

import java.util.List;

public interface AdminService extends Service {

    public int addNewProduct(Product product);

    public boolean updatePrice(int prodId,double price);
    public boolean updateQty(int prodId,int prodQty);

}

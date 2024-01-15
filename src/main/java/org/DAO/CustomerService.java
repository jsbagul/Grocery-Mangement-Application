package org.DAO;

import org.DTO.Order;
import org.DTO.Product;
import org.DTO.User;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends Service {

    public boolean placeOrder(Order order) throws SQLException;
    public List<Product> displayAllProduct(int ordId);
    public List<Product> displayAllProduct();
    public boolean cancelOrder(int prodId,int ordId);
    public double getTotalBill(int ordId);
    public List<Order> displayAllOrders(User user);

    public int removeProudct(int prodId);


}

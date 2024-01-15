package org.DAO;

import org.DTO.Order;
import org.DTO.Product;
import org.DTO.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImple implements CustomerService{
    @Override
    public List<Product> showAllProduct() {

            List<Product> productList=new ArrayList<>();
            String query="Select * from product_info";

            try {
                Statement stmt= ConnectionDB.getConnection().createStatement();
                ResultSet rs= stmt.executeQuery(query);
                while (rs.next()){
                    Product product=new Product(rs.getString(2),rs.getDouble(3),rs.getInt(4));
                    productList.add(product);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

            return productList;

    }

    @Override
    public boolean placeOrder(Order order) throws SQLException {
        String insertUserProcedure = "{call insertUser(?)}";//it insert insert uID in user_info and gives orderID
        String placeOrderProcedure = "{call placeOrder(? , ? , ?)}";
        CallableStatement cstmt = ConnectionDB.getConnection().prepareCall(insertUserProcedure);
        cstmt.setInt(1 , order.getUser().getUserId());
        cstmt.execute() ;
        ResultSet rs = cstmt.getResultSet();
        int ordId=0 ;
        while (rs.next())
            ordId = rs.getInt(1);

        cstmt = ConnectionDB.getConnection().prepareCall(placeOrderProcedure);

        for (Product p : order.getProductList()) {
            cstmt.setInt(1, ordId);
            cstmt.setString(2, p.getProdName() );
            cstmt.setInt(3 , p.getProdQty());
            cstmt.execute();
        }
        return true ;
    }


    @Override
    public List<Product> displayAllProduct(int ordId) {
        List<Product> productList =new ArrayList<>();
        String query="select p.prodId,p.prodName from pivot op inner join product_info p on p.prodId=op.prodId where ordId=?;";

        PreparedStatement pstmt= null;
        try {
            pstmt = ConnectionDB.getConnection().prepareStatement(query);
            pstmt.setInt(1,ordId);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                Product product=new Product(id,name);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return productList;
    }


    @Override
    public List<Product> displayAllProduct() {
        String selectQuery = "SELECT prodName , prodPrice FROM product_info ";
        List<Product> productList = new ArrayList<>();
        try {
            Statement stmt = ConnectionDB.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                String name = rs.getString(1);
                double price = rs.getDouble(2);
                Product pro = new Product(name , price);
                productList.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return productList;
    }

    @Override
    public boolean cancelOrder(int prodId,int ordId) {
        String query="{call cancelProduct(? , ?)}";

        try {
            CallableStatement cstmt=ConnectionDB.getConnection().prepareCall(query);
            cstmt.setInt(1,ordId);
            cstmt.setInt(2,prodId);
            cstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public double getTotalBill(int ordId) {
       String query="select totalBill from order_info where ordId=?";
       double bill=0.0;
        try {
            PreparedStatement pstmt=ConnectionDB.getConnection().prepareStatement(query);
            pstmt.setInt(1,ordId);
           ResultSet rs= pstmt.executeQuery();
           while (rs.next()){
               bill=rs.getDouble(1);
           }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return bill;
    }

    @Override
    public List<Order> displayAllOrders(User user) {
        List<Order> orderList=new ArrayList<>();
        String query="select ordId from order_info where userId= "+user.getUserId();

        try {
            Statement statement=ConnectionDB.getConnection().createStatement();
            ResultSet rs=statement.executeQuery(query);
            while (rs.next()){
                int ordId=rs.getInt(1);
                Order o1=new Order();
                o1.setOrderId(ordId);
                orderList.add(o1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return orderList;

    }


 /*   @Override
    public List<Order> getAllOrders() {
       List<Order> orderList=new ArrayList<>();
       String query="select o.ordId,p.prodId,prod.prodName,p.prodQty from order_info o inner join pivot p on o.ordId=p.ordId inner join product_info prod on prod.prodId=p.prodId ;";

        try {
            Statement stmt=ConnectionDB.getConnection().createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while (rs.next()){
                int ordId=rs.getInt(1);
                int prodId=rs.getInt(2);
                String prodName=rs.getString(3);
                int prodQty=rs.getInt(4);
                Order order=new Order();
                order.setOrderId(ordId);


            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }*/

    @Override
    public int removeProudct(int prodId) {
       String query="{call removeProd(?)}";
        try {
            CallableStatement cstmt=ConnectionDB.getConnection().prepareCall(query);
            cstmt.setInt(1,prodId);
            cstmt.setInt(1,prodId);
           return cstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public List<Product> showByName(String name) {
        return null;
    }


}

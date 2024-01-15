package org.DAO;

import org.DTO.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImple implements AdminService{
    @Override
    public List<Product> showAllProduct() {
       List<Product> productList=new ArrayList<>();
       String query="Select * from product_info";

        try {
            Statement stmt= ConnectionDB.getConnection().createStatement();
           ResultSet rs= stmt.executeQuery(query);
           while (rs.next()){
               Product product=new Product(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4));
               productList.add(product);
           }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productList;
    }

    @Override
    public int addNewProduct(Product product) {
        String query ="insert into product_info values(?,?,?,?)";
        try {
            PreparedStatement pstmt=ConnectionDB.getConnection().prepareStatement(query);
            pstmt.setInt(1,product.getProdId());
            pstmt.setString(2,product.getProdName());
            pstmt.setDouble(3,product.getProdPrice());
            pstmt.setInt(4,product.getProdQty());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public List<Product> showByName(String name) {
        String query="select * from product_info where prodName=?";
        List<Product> productList=new ArrayList<>();
        try {
            PreparedStatement pstmt=ConnectionDB.getConnection().prepareStatement(query);
            pstmt.setString(1,name);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                Product product=new Product(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    return productList;
    }

    @Override
    public boolean updatePrice(int prodId,double price) {
      String query="update product_info set prodPrice=? where prodId=?";
      int n=0;
        try {
            PreparedStatement pstmt=ConnectionDB.getConnection().prepareStatement(query);
            pstmt.setDouble(1,price);
            pstmt.setInt(2,prodId);

             n= pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

            if (n==1){
                return true;
            }
         return false;
    }

    @Override
    public boolean updateQty(int prodId,int prodQty) {
        String query="update product_info set prodQty=? where prodId=?";
        int n=0;
        try {
            PreparedStatement pstmt=ConnectionDB.getConnection().prepareStatement(query);
            pstmt.setInt(1,prodQty);
            pstmt.setInt(2,prodId);
            n= pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (n==1){
            return true;
        }
        return false;
    }

    @Override
    public int removeProudct(int prodId) {
        String query="delete from product_info where prodId=?";
        try {
            PreparedStatement pstmt=ConnectionDB.getConnection().prepareStatement(query);
            pstmt.setInt(1,prodId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
}

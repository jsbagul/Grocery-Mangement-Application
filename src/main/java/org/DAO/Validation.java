package org.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Validation {


    public String getValidation(String userName, String userPass) {

        String query="select role from user_info where userName=? && userPass=?";
        String role="";
        try {
            PreparedStatement pstmt=ConnectionDB.getConnection().prepareStatement(query);
            pstmt.setString(1,userName);
            pstmt.setString(2,userPass);
           ResultSet rs= pstmt.executeQuery();
          while (rs.next()){
            role=rs.getString(1);
          }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return role;
    }
}

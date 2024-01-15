package org.DAO;

import org.DTO.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static User getUser(User user)
    {

        String getUserQuery = "select * from user_info where userName = ? and userPass = ?";
        try {
            PreparedStatement pstmt = ConnectionDB.getConnection().prepareStatement(getUserQuery);
            pstmt.setString(1 , user.getUserName());
            pstmt.setString(2 , user.getUserPass());
            ResultSet rs = pstmt.executeQuery() ;
            while (rs.next())
            {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String role = rs.getString(4 );
                user = new  User(id , name , "*********" , role) ;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user ;
    }
}

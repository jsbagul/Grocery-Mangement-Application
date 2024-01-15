package org.DTO;

public class User {
    private int userId;
    private String userName;
    private String userPass;
    private String role;

    public User() {
    }

    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public User(int userId, String userName, String userPass, String role) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

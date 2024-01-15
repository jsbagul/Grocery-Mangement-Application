package org.UI;
import org.DAO.UserDAO;
import org.DAO.Validation;
import org.DTO.User;

import java.util.Scanner;

public class MainApp
{
    static Scanner scanner=new Scanner(System.in);
    static Validation validation=new Validation();

    public static void main( String[] args ) throws InterruptedException {
        System.out.println("Welcome to Shop");
        System.out.println("Enter your User Name");
        String userName=scanner.next();
        System.out.println("Enter Password");
        String userPass=scanner.next();
        User user=new User(userName,userPass);
        user= UserDAO.getUser(user);

        String role=validation.getValidation(userName,userPass);
        switch (role){
            case "admin":
                System.out.println("You are admin");
                AdminUI adminUI=new AdminUI();
                adminUI.admin(user);
                break;
            case "customer":
                System.out.println("You are Customer");
                CustomerUI customerUI=new CustomerUI();
                customerUI.customer(user);
                break;
            default:
                System.out.println("Invalid userName password");
                break;

        }
    }

}

package org.UI;

import org.DAO.AdminService;
import org.DAO.AdminServiceImple;
import org.DTO.Product;
import org.DTO.User;

import java.util.Scanner;

public class AdminUI {
    static Scanner scanner=new Scanner(System.in);
    static AdminService adminService=new AdminServiceImple();
    public static void admin(User user) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Welcome "+user.getUserName());
        System.out.println("Select mode of Operation");
        System.out.println("1.Display All Product");
        System.out.println("2.Add new Product");
        System.out.println("3.Find Product by Name");
        System.out.println("4.Update product Price");
        System.out.println("5.Update product Qty");
        System.out.println("6.Remove Product");
        System.out.println("7.exit");
        int choise=scanner.nextInt();

        switch (choise){
            case 1:
                displayAllProduct();
                break;
            case 2:
                addProduct();
                break;
            case 3:
                displayByName();
                break;
            case 4:
                updateProdPrice();
                break;
            case 5:
                updateProdQty();
                break;
            case 6:
                removeProduct();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        admin(user);
    }

    private static void removeProduct() {
        System.out.println("Enter Product Id");
        int id=scanner.nextInt();

        if (adminService.removeProudct(id)==1){
            System.out.println("Product Removed Successful !");
        }else {
            System.out.println("Something went Wrong");
        }
    }

    private static void updateProdQty() {
        System.out.println("Enter Product Id");
        int id=scanner.nextInt();
        System.out.println("Enter Updated price");
        int qty= scanner.nextInt();
        if (adminService.updateQty(id,qty)){
            System.out.println("Quantity updated !");
        }
        else {
            System.out.println("Something went wrong...");
        }

    }

    private static void updateProdPrice() {
        System.out.println("Enter Product Id");
        int n=scanner.nextInt();
        System.out.println("Enter Updated price");
        double price= scanner.nextDouble();
        if (adminService.updatePrice(n,price)){
            System.out.println("Price updated !!");
        }
        else {
            System.out.println("Something went wrong");
        }
    }

    private static void displayByName() {
        System.out.println("Enter Product Name");
        String n=scanner.next();
        System.out.println("ProductID   ProductName    Price     Qty");
        System.out.println("==========================================");
        for (Product p: adminService.showByName(n)){
            System.out.println(p.getProdId()+"\t"+p.getProdName()+"\t"+p.getProdPrice()+"\t"+p.getProdQty());
        }
        System.out.println("==========================================");
    }

    private static void addProduct() {
        System.out.println("Enter Product Id");
        int id=scanner.nextInt();
        System.out.println("Enter Product Name");
        String name=scanner.next();
        System.out.println("Enter Product Price");
        double price= scanner.nextDouble();
        System.out.println("Enter total Quantity");
        int qty=scanner.nextInt();

        Product product=new Product(id,name,price,qty);

        int n= adminService.addNewProduct(product);

        if (n==1){
            System.out.println("Product Added Successfully");
        }else {
            System.out.println("Product Not added");
        }
    }

    private static void displayAllProduct() {
        System.out.println("ProductID   ProductName    Price     Qty");
        System.out.println("===========================================");
        for (Product p: adminService.showAllProduct()) {
            System.out.println(p.getProdId()+"\t\t"+p.getProdName()+"\t\t"+p.getProdPrice()+"\t\t"+p.getProdQty());
        }
        System.out.println("===========================================");
    }
}

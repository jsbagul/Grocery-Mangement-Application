package org.UI;

import org.DAO.CustomerService;
import org.DAO.CustomerServiceImple;
import org.DTO.Order;
import org.DTO.Product;
import org.DTO.User;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerUI {
    static Scanner scanner=new Scanner(System.in);
    static CustomerService customerService=new CustomerServiceImple();
    public static void customer(User user) throws InterruptedException {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Welcome "+user.getUserName());
        System.out.println("Select Mode of Operation");
        System.out.println("1.Display All Product");
        System.out.println("2.place a order");
        System.out.println("3.Cancel Product from Order");
        System.out.println("4.Generate Total bill");
        System.out.println("5.View All placed Order");
        System.out.println("6.exit");


        int choise=scanner.nextInt();

        switch (choise){
            case 1:
                displayProducts();
                break;
            case 2:
                placeOrd(user);
                break;
            case 3:
                cancelOrd(user);
                break;
            case 4:
                GenerateTotalBill();
                break;
            case 5:
                viewAllOrders(user);
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        customer(user);
    }

    private static void placeOrd(User user) throws InterruptedException {
        Order order=new Order(user);

        do {
            System.out.println("ENTER PRODUCT NAME ");
            String productName = scanner.next();
            System.out.println("ENTER ORDER QTY ");
            int orderQty = scanner.nextInt();
            Product product = new Product(productName, orderQty);
            order.addProduct(product);

            System.out.println("ADD MORE PRODUCT (Y/N)");
            char ch = scanner.next().charAt(0);
            if (ch == 'n' || ch == 'N')
                break;

        }while(true);
        boolean status = false;
        try {
            status = customerService.placeOrder(order);
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (status){
            Thread.sleep(1000);
            System.out.println("ORDER PLACED !!");}
        else{
            System.out.println("ORDER NOT PLACED !!");
        }
    }

    private static void cancelOrd(User user) throws InterruptedException {
        viewOrder(user);
        System.out.println("SELECT ORDER ");
        int ordId = scanner.nextInt() ;
        for (Product p :customerService.displayAllProduct(ordId)){
            Thread.sleep(1200);
            System.out.println(p.getProdId() +"     "+ p.getProdName());
        }
        Thread.sleep(1200);

        System.out.println("Enter Product id to cancel");
        int prodId=scanner.nextInt();
        boolean status=false;
        status=customerService.cancelOrder(prodId,ordId);
        if (status){
            System.out.println("Order cancelled");
        }else {
            System.out.println("Product Not Cancelled");
        }
    }

    private static void GenerateTotalBill() {
        System.out.println("Enter Order Id ");
        int ordId=scanner.nextInt();
        System.out.println("Your Total bill is "+customerService.getTotalBill(ordId));
    }

    private static void displayProducts() {
        System.out.println("ProductName    Price     Qty");
        System.out.println("================================");
        for (Product p: customerService.displayAllProduct()) {
            System.out.println(p.getProdName()+"\t\t"+p.getProdPrice());
        }
        System.out.println("================================");
    }


    private static void viewOrder(User user) throws InterruptedException {
        System.out.println("All OrderIdList");
        System.out.println("================================");
        for (Order op: customerService.displayAllOrders(user)) {
                Thread.sleep(1000);
                System.out.println(op.getOrderId());
        }
        System.out.println("================================");

    }
    private static void viewAllOrders(User user) throws InterruptedException {
        viewOrder(user);
        System.out.println("SELECT ORDER Id ");
        int ordId = scanner.nextInt() ;
        for (Product p :customerService.displayAllProduct(ordId)){
            Thread.sleep(1200);
            System.out.println(p.getProdId() +"     "+ p.getProdName());
        }
        Thread.sleep(1200);
    }
}

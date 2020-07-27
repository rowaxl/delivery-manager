import DAO.CustomerDao;
import DAO.OrderDao;
import models.Customer;
import models.Order;
import printers.CustomerPrinter;
import printers.ItemPrinter;
import printers.OrderPrinter;

import java.util.Scanner;

public class Diver {
    private static final Scanner scanner = new Scanner(System.in);

    static void printOptions() {
        System.out.println("Select the action from belows:");
        System.out.println("[1] Print the menu");
        System.out.println("[2] Print all customers");
        System.out.println("[3] Print all orders");
        System.out.println("[4] Add new customer");
        System.out.println("[5] Make the order");
        System.out.println("[6] Delete the order");
        System.out.println("[q] Quit");
    }

    static Customer promptCustomerInfo() {
        System.out.println("Enter the name:");
        String name = scanner.next();

        System.out.println("Enter the address:");
        String address = scanner.next();

        System.out.println("Enter the phone number:");
        String phoneNum = scanner.next();

        return new Customer(name, address, phoneNum);
    }

    static Order promptOrderInfo() {
        System.out.println("Enter the customer's ID:");
        int customerId = scanner.nextInt();

        System.out.println("Enter the item's ID:");
        int itemId = scanner.nextInt();

        System.out.println("Enter the quantity:");
        int quantity = scanner.nextInt();

        return new Order(customerId, itemId, quantity);
    }

    static int propmtOrderId() {
        System.out.println("Enter the order ID:");
        int orderId = scanner.nextInt();

        boolean isOrderExist = OrderDao.isOrderExist(orderId);
        while (!isOrderExist) {
            System.out.println("Enter the valid order ID:");
            orderId = scanner.nextInt();
            isOrderExist = OrderDao.isOrderExist(orderId);
        }

        return orderId;
    }

    public static void main(String[] args) {
        boolean isDone = false;
        while (!isDone) {
            printOptions();
            String input = scanner.next();

            switch (input) {
                case "1":
                    ItemPrinter.printItems();
                    System.out.println();
                    break;
                case "2":
                    CustomerPrinter.printAllCustomers();
                    System.out.println();
                    break;
                case "3":
                    OrderPrinter.printOrders();
                    System.out.println();
                    break;
                case "4":
                    Customer customer = promptCustomerInfo();
                    CustomerDao.insertCustomer(customer);
                    System.out.println("New customer created successfully");
                    System.out.println();
                    break;
                case "5":
                    Order order = promptOrderInfo();
                    OrderDao.createOrder(order);
                    System.out.println("New order created successfully");
                    System.out.println();
                    break;
                case "6":
                    int orderId = propmtOrderId();
                    OrderDao.deleteOrder(orderId);
                    System.out.printf("Order %d deleted successfully\n", orderId);
                    System.out.println();
                    break;
                case "q":
                case "Q":
                    isDone = true;
                    break;
                default:
                    System.out.println("You should choose valid option");
                    break;
            }
        }
        scanner.close();
    }
}

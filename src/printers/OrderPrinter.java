package printers;

import DAO.OrderDao;
import models.Order;

import java.util.ArrayList;

public class OrderPrinter {
    public static void printOrders() {
        ArrayList<Order> orders = OrderDao.getOrders();

        System.out.printf("%-5s %20s %20s %15s %15s %15s\n", "ID", "Customer", "Address", "Item", "Quantity", "Total price");
        for (Order order: orders) {
            System.out.printf("%-5s %20s %20s %15s %15s %15s\n",
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getCustomerAddress(),
                    order.getItemName(),
                    order.getQuantity(),
                    order.getTotalAmount()
                );
        }
    }
}

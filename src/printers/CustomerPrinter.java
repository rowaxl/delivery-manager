package printers;

import DAO.CustomerDao;
import models.Customer;

import java.util.ArrayList;

public class CustomerPrinter {
    public static void printAllCustomers() {
        ArrayList<Customer> customers = CustomerDao.getCustomers();

        System.out.printf("%-10s %20s %20s %20s\n", "ID", "Name", "Address", "Phone number");
        for (Customer customer: customers) {
            System.out.printf("%-10s %20s %20s %20s\n", customer.getId(), customer.getName(), customer.getAddress(), customer.getPhoneNum());
        }
    }
}

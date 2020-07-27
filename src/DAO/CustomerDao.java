package DAO;

import helper.DBConnection;
import models.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDao {
    private static final String INSERT_QUERY = "INSERT INTO Customers (name, address, phone_num) VALUES (?, ?, ?)";

    public static ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            Statement smt = connection.createStatement();

            ResultSet rs = smt.executeQuery("SELECT * FROM Customers");

            while (rs.next()) {
                customers.add(
                    new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone_num")
                    )
                );
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return customers;
    }

    public static void insertCustomer(Customer detail) {
        try {
           Connection connection = DBConnection.getConnection();
           PreparedStatement prst = connection.prepareStatement(INSERT_QUERY);

           prst.setString(1, detail.getName());
           prst.setString(2, detail.getAddress());
           prst.setString(3, detail.getPhoneNum());

           prst.executeUpdate();

           connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

package DAO;

import helper.DBConnection;
import models.Item;
import models.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDao {
    public static final String INSERT_QUERY = "INSERT INTO Orders (customer_id, item_id, quantity) VALUES (?, ?, ?)";

    public static ArrayList<Order> getOrders() {
            ArrayList<Order> result = new ArrayList<>();
            try {
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT Orders.id, Customers.name, Customers.address, Items.name, Orders.quantity, (Items.price * Orders.quantity) as total_amount FROM Orders, Items, Customers WHERE Orders.item_id = Items.id AND Orders.customer_id = Customers.id");

                while(rs.next()) {
                    result.add(
                        new Order(
                            rs.getInt("id"),
                            rs.getString("Customers.name"),
                            rs.getString("Customers.address"),
                            rs.getString("Items.name"),
                            rs.getInt("quantity"),
                            rs.getDouble("total_amount")
                    ));
                }

                connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }

            return result;
    }

    public static void createOrder(Order order) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement prst = connection.prepareStatement(INSERT_QUERY);

            prst.setInt(1, order.getCustomerId());
            prst.setInt(2, order.getItemId());
            prst.setInt(3, order.getQuantity());

            prst.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static boolean isOrderExist(int orderId) {
        boolean isExist = false;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement prst = connection.prepareStatement("SELECT * FROM Orders WHERE id = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            prst.setInt(1, orderId);
            ResultSet rs = prst.executeQuery();

            int size = 0;
            if (rs != null) {
                rs.last();
                size = rs.getRow();
            }

            isExist = size == 1;
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return isExist;
    }

    public static void deleteOrder(int orderId) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement prst = connection.prepareStatement("DELETE FROM Orders WHERE id = ?");

            prst.setInt(1, orderId);
            prst.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

package DAO;

import helper.DBConnection;
import models.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDao {
    public static ArrayList<Item> getItems() {
        ArrayList<Item> result = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Items");

            while(rs.next()) {
                result.add(new Item(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price")
                ));
            }

            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return result;
    }
}

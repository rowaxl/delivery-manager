package printers;

import DAO.ItemDao;
import models.Item;

import java.util.ArrayList;

public class ItemPrinter {
    public static void printItems() {
        ArrayList<Item> items = ItemDao.getItems();

        System.out.printf("%-10s %20s %10s\n", "ID", "Name", "Price");
        for (Item i: items) {
            System.out.printf("%-10d %20s %10.2f\n", i.getItem_id(), i.getName(), i.getPrice());
        }
    }
}

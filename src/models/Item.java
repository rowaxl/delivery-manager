package models;

public class Item {
    private int item_id;
    private String name;
    private double price;

    public Item(int item_id, String name, double price) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

package concurrencyplayground.model;

import java.util.List;

public class Order {

    private List<String> items;

    public Order(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }
}
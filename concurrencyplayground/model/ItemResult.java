package concurrencyplayground.model;

public class ItemResult {

    private String itemName;
    private double price;
    private double shipping;

    public ItemResult(String itemName, double price, double shipping) {
        this.itemName = itemName;
        this.price = price;
        this.shipping = shipping;
    }

    public double getTotal() {
        return price + shipping;
    }

    @Override
    public String toString() {
        return itemName + " price=" + price + " shipping=" + shipping;
    }
}

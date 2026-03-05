package concurrencyplayground.service;

public class InventoryService {

    public boolean checkStock(String item) {

        sleep();

        System.out.println(Thread.currentThread().getName() + " Checked inventory for " + item);

        return true;
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (Exception ignored) {
        }
    }
}

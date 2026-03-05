package concurrencyplayground.service;

import java.util.Random;

public class ShippingService {

    Random random = new Random();

    public double calculateShipping(String item) {

        sleep();

        double shipping = 10 + random.nextInt(40);

        System.out.println(Thread.currentThread().getName() + " Calculated shipping for " + item);

        return shipping;
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (Exception ignored) {
        }
    }
}

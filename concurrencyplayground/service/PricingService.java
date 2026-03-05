package concurrencyplayground.service;

import java.util.Random;

public class PricingService {

    Random random = new Random();

    public double calculatePrice(String item) {

        sleep();

        double price = 500 + random.nextInt(1000);

        System.out.println(Thread.currentThread().getName() + " Calculated price for " + item);

        return price;
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (Exception ignored) {
        }
    }
}
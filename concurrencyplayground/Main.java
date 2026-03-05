package concurrencyplayground;

import java.util.List;

import concurrencyplayground.model.Order;
import concurrencyplayground.service.OrderProcessingService;

public class Main {

    public static void main(String[] args) {

        Order order = new Order(List.of(
                "Laptop",
                "Phone",
                "Keyboard",
                "Mouse",
                "Headphones"));

        OrderProcessingService service = new OrderProcessingService();
        // service.processOrder(order);
        service.processOrderWithCompletableFuture(order);
    }
}

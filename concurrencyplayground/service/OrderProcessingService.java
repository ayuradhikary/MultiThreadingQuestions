package concurrencyplayground.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import concurrencyplayground.model.ItemResult;
import concurrencyplayground.model.Order;
import concurrencyplayground.model.OrderSummary;

public class OrderProcessingService {

    private InventoryService inventoryService = new InventoryService();
    private PricingService pricingService = new PricingService();
    private ShippingService shippingService = new ShippingService();

    private AtomicInteger itemsProcessed = new AtomicInteger(0);

    private void incrementCounter() {
        itemsProcessed.incrementAndGet(); // thread-safe
    }

    // sequential execution
    // public void processOrder(Order order) {

    // long start = System.currentTimeMillis();
    // List<ItemResult> results = new ArrayList<>();

    // for (String item : order.getItems()) {

    // ItemResult result = processItem(item);

    // results.add(result);

    // }

    // OrderSummary summary = new OrderSummary(results);

    // long end = System.currentTimeMillis();

    // System.out.println(summary);

    // System.out.println("\nProcessing Time: " + (end - start) + " ms");
    // }

    // private ItemResult processItem(String item) {

    // inventoryService.checkStock(item);

    // double price = pricingService.calculatePrice(item);

    // double shipping = shippingService.calculateShipping(item);

    // return new ItemResult(item, price, shipping);
    // }

    // -----------------------------------------------------------------------------------------------------

    // Concurrent execution without using CompletableFuture

    // public void processOrder(Order order) {

    // long start = System.currentTimeMillis();

    // ExecutorService executor = Executors.newFixedThreadPool(4);
    // List<Future<ItemResult>> futures = new ArrayList<>();

    // for (String item : order.getItems()) {
    // Future<ItemResult> future = executor.submit(() -> processItem(item));
    // futures.add(future);
    // }

    // List<ItemResult> results = new ArrayList<>();

    // for (Future<ItemResult> future : futures) {

    // try {
    // results.add(future.get());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    // executor.shutdown();

    // OrderSummary summary = new OrderSummary(results);

    // long end = System.currentTimeMillis();

    // System.out.println(summary);

    // System.out.println("\nProcessing Time: " + (end - start) + " ms");
    // }

    // private ItemResult processItem(String item) {

    // inventoryService.checkStock(item);

    // double price = pricingService.calculatePrice(item);

    // double shipping = shippingService.calculateShipping(item);

    // return new ItemResult(item, price, shipping);
    // }

    // ------------------------------------------------------------------------------------------------------
    // Concurrent execution using Completable Future

    public void processOrderWithCompletableFuture(Order order) {

        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(8);

        List<CompletableFuture<ItemResult>> futures = new ArrayList<>();

        for (String item : order.getItems()) {
            futures.add(processItemAsync(item, executor));
        }

        // Wait for all items to finish
        List<ItemResult> results = futures.stream()
                .map(CompletableFuture::join)
                .toList();

        executor.shutdown();

        OrderSummary summary = new OrderSummary(results);

        long end = System.currentTimeMillis();

        System.out.println(summary);
        System.out.println("\nProcessing Time: " + (end - start) + " ms");
        System.out.println("Items processed: " + itemsProcessed);
    }

    // Before, we only ran one task per item, and each task executed 3 service calls
    // sequentially:
    // Now with CompletableFuture (services inside item)
    // Now, for each item, we also run 3 services concurrently:
    // InventoryService -> async
    // PricingService -> async
    // ShippingService -> async
    // since we are executing the services for each item concurrently we increased
    // the number of threads from 4 to 8 as each item is
    // calling three services and all three services are executing parallelly thats
    // why
    // previously all items were calling three services but not service calls were
    // not parallel only the items being processed were
    // parallelly being processed.
    private CompletableFuture<ItemResult> processItemAsync(String item, ExecutorService executor) {

        CompletableFuture<Boolean> inventoryFuture = CompletableFuture
                .supplyAsync(() -> inventoryService.checkStock(item), executor);

        CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(() -> pricingService.calculatePrice(item),
                executor);

        CompletableFuture<Double> shippingFuture = CompletableFuture
                .supplyAsync(() -> shippingService.calculateShipping(item), executor);

        // Wait for all 3 to complete and combine results
        return CompletableFuture
                .allOf(inventoryFuture, priceFuture, shippingFuture)
                .thenApply(v -> {
                    Boolean stock = inventoryFuture.join();
                    Double price = priceFuture.join();
                    Double shipping = shippingFuture.join();

                    incrementCounter(); // multiple threads incrementing at the same time

                    return new ItemResult(item, price, shipping);
                });

    }
}
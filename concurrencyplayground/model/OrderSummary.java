package concurrencyplayground.model;

import java.util.List;

public class OrderSummary {

    private List<ItemResult> results;
    private double total;

    public OrderSummary(List<ItemResult> results) {

        this.results = results;

        this.total = results.stream()
                .mapToDouble(ItemResult::getTotal)
                .sum();
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("\nOrder Summary\n");

        for (ItemResult r : results) {
            builder.append(r).append("\n");
        }

        builder.append("Total Price: ").append(total);

        return builder.toString();
    }
}
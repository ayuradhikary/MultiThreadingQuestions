
class Data {
    private int value;
    private boolean isAvailable = false;

    public synchronized void produce(int newValue) {
        while (isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        value = newValue;
        System.out.println("Produced: " + value);
        isAvailable = true;
        notify();
    }

    public synchronized void consume() {
        while (!isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Consumed: " + value);
        isAvailable = false;
        notify();
    }
}

public class SimpleProducerConsumer {
    public static void main(String[] args) {
        Data data = new Data();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                data.produce(i);
                try {
                    Thread.sleep(1000); // Simulate time taken to produce data
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                data.consume();
                try {
                    Thread.sleep(1500); // Simulate time taken to consume data
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}

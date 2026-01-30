package BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        Thread producerThread = new Thread(producer, "producerThread");
        Thread consumerThread = new Thread(consumer, "consumerThread");

        producerThread.start();
        consumerThread.start();
    }

}

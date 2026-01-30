package BlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> queue) {
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String element = this.blockingQueue.take();
                System.out.println("Consumed: " + element);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            System.out.println("Consumer stopped.");
        }
    }

}

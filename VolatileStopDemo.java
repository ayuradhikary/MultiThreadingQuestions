public class VolatileStopDemo {

    static class Worker extends Thread {
        private volatile boolean running = true; // stop flag

        @Override
        public void run() {
            int i = 0;
            while (running) { // check stop flag
                System.out.println(Thread.currentThread().getName() + " - Count: " + i++);
                try {
                    Thread.sleep(200); // simulate work
                } catch (InterruptedException e) {
                    // Tells the current thread: “Hey, you’ve been interrupted. You should stop what
                    // you’re doing when possible.”
                    Thread.currentThread().interrupt(); // restore interrupt flag .interrupt() → sets the “interrupt
                                                        // status” of the thread to true.
                    System.out.println("Thread interrupted!");
                }
            }
            System.out.println(Thread.currentThread().getName() + " stopped.");
        }

        // Method to stop the thread
        public void stopRunning() {
            running = false;
            this.interrupt(); // wakes up sleeping thread
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.start();

        // Let the thread run for 2 seconds
        Thread.sleep(2000);

        System.out.println("Requesting thread to stop...");
        worker.stopRunning(); // signal the thread to stop

        worker.join(); // wait for thread to finish
        System.out.println("Main thread finished.");
    }
}

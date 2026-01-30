public class CriticalSectionDemo {

    static class Counter {
        private int count = 0;

        // Only synchronize the increment itself, not the whole method
        public void increment() {
            // critical section
            synchronized (this) {
                count++;
            }
            // non-critical code could go here (doesn't block other threads)
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                counter.increment();
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                counter.increment();
        }, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}

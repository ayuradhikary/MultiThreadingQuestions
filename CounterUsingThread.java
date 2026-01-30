// Write a program where multiple threads increment a shared counter safely.\

public class CounterUsingThread {

    public class Counter {

        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }

    }

    public static void main(String[] args) {

        CounterUsingThread outer = new CounterUsingThread();
        Counter counter = outer.new Counter(); // non static innter class always belongs to the instances of inner class
                                               // thats why we did outer.new Counter();

        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        }, "thread-one");

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        }, "thread-two");

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + counter.getCount());

    }

}

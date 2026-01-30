// create two new threads that print even and odd numbers upto 100

public class MultiThreadingProblemOne {

    public static void main(String[] args) {

        Runnable evenRunnable = () -> {
            for (int i = 0; i <= 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(i + " Thread Name: " + Thread.currentThread().getName()); // even number
                }
            }
        };

        Runnable oddRunnable = () -> {
            for (int i = 1; i <= 100; i++) {
                if (i % 2 != 0) {
                    System.out.println(i + " Thread Name: " + Thread.currentThread().getName());
                }
            }
        };

        Thread evenThread = new Thread(evenRunnable, "even-thread");
        Thread oddThread = new Thread(oddRunnable, "odd-thread");

        evenThread.start();
        oddThread.start();
    }

}

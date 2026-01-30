// Print "Hello" and "World" using two threads in order.

public class HelloWorldUsingMultiThreading {

    public static void main(String[] args) {

        Runnable hello = () -> {
            System.out.print("Hello");
        };

        Runnable world = () -> {
            System.out.print(" World!");
        };

        Thread helloThread = new Thread(hello, "hello-thread");
        Thread worldThread = new Thread(world, "world-thread");

        helloThread.start();

        try {
            helloThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worldThread.start();

    }

}

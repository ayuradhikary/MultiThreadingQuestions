public class PrintUsingTwoThreads {
    public static void main(String[] args) {

        Runnable runnableOne = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
        };

        Runnable runnableTwo = () -> {
            for (int i = 5; i <= 10; i++) {
                System.out.println(i);
            }
        };

        Thread t1 = new Thread(runnableOne, "threadOneToFour");
        Thread t2 = new Thread(runnableTwo, "threadTwoFiveTo10");

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

    }
}

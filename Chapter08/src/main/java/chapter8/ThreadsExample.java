package chapter8;

public class ThreadsExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("T1: " + i);
            }
        });
        t1.setDaemon(true);
        t1.start();
        

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("T2: " + i);
            }
        }).start();

    }
}

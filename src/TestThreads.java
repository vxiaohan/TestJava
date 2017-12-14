import java.util.Random;

class Foo {
    static private String name = "";

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class MyThread implements Runnable {
    private int i = 0;
    private Foo foo = new Foo();

    @Override
    public void run() {
        for (i = 0; i < 30; i++) {
            synchronized (this) {
                foo.setName(Thread.currentThread().getName());
                Random random = new Random();
                long sleepTime = (long) random.nextInt(100000);
                int k = 0;
                while (k <= sleepTime) {
                    k++;
                }
                System.out.println(i + Thread.currentThread().getName() + " " + foo.getName());
            }
        }
    }
}

public class TestThreads extends Thread {
    public static void main(String args[]) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 10) {
                MyThread myThread = new MyThread();

                Thread myThread0 = new Thread(myThread);
                Thread myThread1 = new Thread(myThread);
                myThread0.start();
                myThread1.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

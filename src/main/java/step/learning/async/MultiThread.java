package step.learning.async;

import java.util.Random;

/**
 * Демонстрація багатопоточності
 */
public class MultiThread {
    private final static Random random = new Random();
    private double deposit;
    public void demo(){
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from task 1");
            }
        };
        Thread thread1 = new Thread(task1);
        //thread1.run(); синхрон
        thread1.start(); //асинхрон
        deposit = 100.0;
        for (int i = 1; i < 9; i++) {
            new Thread( new DepositTask(i)).start();
        }
    }
    private StringBuilder result = new StringBuilder();
    class DepositTask implements Runnable{
        double percent;
        private int digit;

        public DepositTask(int digit) {
            this.digit = digit;
        }
        private final Object depositLocker = new Object();

        public DepositTask(double percent) {
            this.percent = percent;
        }

        @Override
        public void run() {
            //try {
                //    Thread.sleep(150 );
                //} catch (InterruptedException e) {
                //    System.out.println("Sleeping interrupted");
                //}
            //double before,after,k = 1.0 + percent / 100.0;
            //synchronized (depositLocker) {
                //    before = deposit;
                //    deposit *= k;
                //    after = deposit;
                //}
            //System.out.printf("Before +%.1f: %.2f\n", percent, before);
            //System.out.printf("After +%.1f: %.2f\n", percent, after);
            synchronized (result) {
                result.append(digit);
                System.out.println(result.toString());
            }
        }
    }
}

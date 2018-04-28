package codequestion.threads;

public class multiThread {
    public static void main(String[] args) {
        new MyThread(0).start();
        new MyThread(1).start();
        new MyThread(2).start();
    }

    static class MyThread extends Thread {
        static final Object lock = new Object();
        static int count = 0;
        int a;
        public MyThread(int a) {
            this.a = a;
        }
        @Override
        public void run() {
            int i = 0;
            while (i< 10) {
                synchronized (lock) {
                    while (count % 3 != a) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    i++;
                    System.out.println(a);
                    lock.notifyAll();
                }
            }
        }
    }
}

package test;

public class SynchronizedTest {
    // 普通方法 锁this
    public synchronized void test1(String str) {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(str);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 静态方法 锁this.getClass()
    public synchronized static void test2(String str) {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(str);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 代码块 锁括号中配置的对象
    public void test3(String str) {

//        synchronized (this.getClass()) {  // 锁类
        synchronized (this) { // 锁对象
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(str);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        SynchronizedTest test2 = new SynchronizedTest();
        new Thread(() -> test.test3("thread1")).start();
        new Thread(() -> test2.test3("thread2")).start();
    }
}

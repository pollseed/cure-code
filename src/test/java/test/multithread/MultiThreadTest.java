package multithread.test;

import java.util.stream.IntStream;

import printer.CommandPrinter;

/**
 * マルチスレッドの説明のためのプログラム.
 */
public class MultiThreadTest extends Thread {
    private static final int MAX_CNT = 10;

    private boolean isStatic = false;

    private int instanceValue = 0;
    private static int staticValue = 0;

    private String threadName = null;

    /**
     * コンストラクタ.
     * 
     * @param v
     *            {@code bool} 値によってクラス・インスタンス変数を切り分ける
     * @param value
     *            実行されるスレッド名
     * @param bool
     *            {@code bool} 値によってクラス・インスタンス変数を切り分ける
     */
    public MultiThreadTest(int v, String value, boolean bool) {
        isStatic = bool;
        threadName = value;
        if (isStatic) {
            staticValue = v;
        } else {
            instanceValue = v;
        }
    }

    @Override
    public void run() {
        int[] idx = { 0 };
        IntStream.range(0, MAX_CNT).forEach(v -> {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isStatic) {
                System.out.println(threadName + idx[0]++ + " : " + --staticValue);
            } else {
                System.out.println(threadName + idx[0]++ + " : " + --instanceValue);
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {

        // インスタンス変数の場合
        MultiThreadTest t1 = new MultiThreadTest(1, "t1 : ", false);
        MultiThreadTest t2 = new MultiThreadTest(99, "t2 : ", false);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // クラス変数の場合
        MultiThreadTest t3 = new MultiThreadTest(1, "t3 : ", true);
        MultiThreadTest t4 = new MultiThreadTest(99, "t4 : ", true);
        t3.start();
        t4.start();

    }
}

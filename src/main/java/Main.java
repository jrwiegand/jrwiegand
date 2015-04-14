package jmodern;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws Exception {
        for (int i = 0;;i++) {
            System.out.println("Calling foo");
            foo(i);
        }
    }

    private static String foo(int x) throws InterruptedException {
        long pause = ThreadLocalRandom.current().nextInt(50, 500);
        Thread.sleep(pause);
        return "aaa" + pause;
    }
}

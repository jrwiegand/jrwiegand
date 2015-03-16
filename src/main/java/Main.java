package jmodern;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.channels.Channel;
import co.paralleluniverse.strands.channels.Channels;
import co.paralleluniverse.strands.channels.SelectAction;
import static co.paralleluniverse.strands.channels.Selector.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final Channel<Integer> ch1 = Channels.newChannel(0);
        final Channel<String> ch2 = Channels.newChannel(0);

        new Fiber<Void>(() -> {
            for (int i = 0; i < 10; i++) {
                Strand.sleep(100);
                ch1.send(i);
            }
            ch1.close();
        }).start();

        new Fiber<Void>(() -> {
            for (int i = 0; i < 10; i++) {
                Strand.sleep(130);
                ch2.send(Character.toString((char)('a' + i)));
            }
            ch2.close();
        }).start();

        new Fiber<Void>(() -> {
            for (int i = 0; i < 10; i++) {
                select(
                    receive(ch1, x -> System.out.println(x != null ? "Got a number: " + x : "ch1 closed")),
                    receive(ch2, x -> System.out.println(x != null ? "Got a string: " + x : "ch2 closed")));
            }
        }).start().join(); // join waits for this fiber to finish
    }
}

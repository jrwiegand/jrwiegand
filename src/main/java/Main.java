package jmodern;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.channels.*;
import com.codahale.metrics.*;
import static com.codahale.metrics.MetricRegistry.name;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.concurrent.TimeUnit.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final MetricRegistry metrics = new MetricRegistry();
        JmxReporter.forRegistry(metrics).build().start(); // starts reporting via JMX

        final Channel<Object> ch = Channels.newChannel(0);

        new Fiber<Void>(() -> {
            Meter meter = metrics.meter(name(Main.class, "messages" , "send", "rate"));
            for (int i = 0; i < 100000; i++) {
                Strand.sleep(ThreadLocalRandom.current().nextInt(50, 500)); // random sleep
                meter.mark(); // measures event rate

                ch.send(i);
            }
            ch.close();
        }).start();

        new Fiber<Void>(() -> {
            Counter counter = metrics.counter(name(Main.class, "messages", "received"));
            Timer timer = metrics.timer(name(Main.class, "messages", "duration"));

            Object x;
            long lastReceived = System.nanoTime();
            while ((x = ch.receive()) != null) {
                final long now = System.nanoTime();
                timer.update(now - lastReceived, NANOSECONDS); // creates duration histogram
                lastReceived = now;
                counter.inc(); // counts

                System.out.println("--> " + x);
            }
        }).start().join(); // join waits for this fiber to finish
    }
}

package jmodern;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.channels.*;
import java.lang.management.ManagementFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.management.MXBean;
import javax.management.ObjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        final AtomicInteger counter = new AtomicInteger();
        final Channel<Object> ch = Channels.newChannel(0);

        // create and register MBean
        ManagementFactory.getPlatformMBeanServer().registerMBean(new JModernInfo() {
            @Override
            public void send(String message) {
                try {
                    ch.send(message);
                } catch (Exception e) {
                    throw new RunTimeException(e);
                }
            }

            @Override
            public int getNumMessagesReceived() {
                return counter.get();
            }
        }, new ObjectName("jmodern:type=Info"));

        new Fiber<Void>(() -> {
            for (int i = 0; i < 1000000; i++) {
                Strand.sleep(100);
                log.info("Sending {}", i); //log something
                ch.send(i);
                if (i % 10 == 0)
                    log.warn("Send {} messages", i + 1); // log something
            }
            ch.close();
        }).start();

        new Fiber<Void>(() -> {
            Integer x;
            while((x = ch.receive()) != null)
                System.out.println("--> " + x);
        }).start().join(); // join waits for this fiber to finish
    }

    @MXBean
    public interface JModernInfo {
        void send(String messaage);
        int getNumMessagesReceived();
    }
}

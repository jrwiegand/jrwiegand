package jmodern;

import java.util.*;
import java.util.concurrent.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.parameters.TimeValue;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Benchmark {
    public static void main(String[] args) throws Exception {
        new Runner(new OptionsBuilder()
                .include(Benchmark.class.getName() + ".*")
                .forks(2)
                .warmupTime(TimeValue.seconds(5))
                .warmupIterations(3)
                .measurementTime(TimeValue.seconds(5))
                .measurementIterations(5)
                .addProfiler(GCProfiler.class)    // report GC time
                .addProfiler(StackProfiler.class) // report method stack execution profile
                .build()).run();
    }

    @GenerateMicroBenchmark
    public Object arrayList() {
        return add(new ArrayList<>());
    }

    @GenerateMicroBenchmark
    public Object linkedList() {
        return add(new LinkedList<>());
    }

    static Object add(List<Integer> list) {
        for (int i = 0; i < 4000; i++)
            list.add(i);
        return list;
    }
}

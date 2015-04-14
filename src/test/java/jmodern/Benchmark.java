package jmodern;

import java.util.concurrent.TimeUnit;
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
                .forks(1)
                .warmupTime(TimeValue.seconds(5))
                .warmupIterations(3)
                .measurementTime(TimeValue.seconds(5))
                .measurementIterations(5)
                .build()).run();
    }

    private double x = 2.0; // prevent constant folding

    @GenerateMicroBenchmark
    public double standardInvSqrt() {
        return 1.0/Math.sqrt(x);
    }

    @GenerateMicroBenchmark
    public double fastInvSqrt() {
        return invSqrt(x);
    }

    static double invSqrt(double x) {
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        x = x * (1.5d - xhalf * x * x);
        return x;
    }
}

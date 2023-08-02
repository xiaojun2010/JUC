package com.sum;

import com.sum.forkjoin.ForkJoinPoolDemo;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
//import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class BenchmarkTest {
    static int[] arr = {1,2,3,4,5,6,7,9,10,11,12,13,14,15,16,17,18,19,20};
     CompletableFutureSum completableFutureSum;
     ForkJoinPoolDemo forkJoinPoolDemo;


    @Setup(Level.Iteration)
    public void setUp()
    {
         completableFutureSum = new CompletableFutureSum();
         forkJoinPoolDemo = new ForkJoinPoolDemo();
    }
    @Benchmark
    public int testCompletableFuture(){
        return completableFutureSum.add(arr);
    }

    @Benchmark
    public int testForkJoinPool(){
        return forkJoinPoolDemo.sum(arr);
    }

    @TearDown
    public void finish(){
        completableFutureSum.close();
        forkJoinPoolDemo.close();
    }


    public static void main(String[] args)
            throws RunnerException
    {
        final Options opts = new OptionsBuilder()
                .include(BenchmarkTest.class.getSimpleName())
                .forks(1)
                .measurementIterations(10)
                .warmupIterations(10)
                .build();
        new Runner(opts).run();
    }
}

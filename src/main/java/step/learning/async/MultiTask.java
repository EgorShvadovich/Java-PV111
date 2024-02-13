package step.learning.async;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MultiTask {
    private final ExecutorService taskPool = Executors.newFixedThreadPool(3);

    public void demo1() {
        long startTime = System.nanoTime();
        long tick = (long) 1e6;
        Runnable runnable = () -> {
            System.out.printf("%d ms: Task starts\n",
                    (System.nanoTime() - startTime) / tick);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Sleeping interrupt");
            } finally {
                System.out.printf("%d ms: Task finishes\n",
                        (System.nanoTime() - startTime) / tick);
            }
        };
        for (int i = 0; i < 10; i++) {
            taskPool.submit(runnable);
        }
        taskPool.shutdown();
    }

    public void demo2() {
        Future<String> task = taskPool.submit(() -> {
            try {
                Thread.sleep(1000);
                return "task finishes";
            } catch (InterruptedException e) {
                return "Sleeping interrupted";
            }
        });
        try {
            System.out.println(task.get());

        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }
        taskPool.shutdown();
    }

    public void demo3() {

        long startTime = System.nanoTime();
        long tick = (long) 1e6; // nano/milli

        try {
            String res1 = taskPool.submit( () -> {
                Thread.sleep(1000);
                return "Task 1 finishes";
            }).get();   // ~ res1 = await  async(){...}
            System.out.printf( "%d ms: Task finishes with '%s'\n",
                    (System.nanoTime() - startTime) / tick,
                    res1 );

            String res2 = taskPool.submit( () -> {
                Thread.sleep(1000);
                return "Task 2 finishes";
            }).get();
            System.out.printf( "%d ms: Task finishes with '%s'\n",
                    (System.nanoTime() - startTime) / tick,
                    res2);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }

        taskPool.shutdown();
        System.out.printf("%d ms: MultiTask finishes\n",
                (System.nanoTime() - startTime) / tick);
    }

    public void demo() {
        Supplier<String> supplier = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Task 1 finishes";
        };
        Consumer<String> consumer = (supply) -> {
            System.out.println("got: " + supply);
        };
        Function<String,String> transform = (source) ->{
            return "Processed " + source;
        };
        Future<?> task1 = CompletableFuture.supplyAsync(supplier,taskPool).thenApply(transform).thenApply(transform).thenAccept(consumer);
        try {
            task1.get(2,TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        taskPool.shutdown();
    }

}

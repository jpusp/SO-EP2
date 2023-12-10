package runners;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorRunner {
    private final Executor executor;

    public ExecutorRunner(Executor executor) {
        this.executor = executor;
    }

    public void runTask(List<String> words, int listSize) {
        for (int i = 0; i <= listSize; i++) {
            List<Runnable> finalList = ReaderWriterFactory.createReaderWriterList(words, i, listSize);

            long total = 0;
            for (int j = 0; j < 50; j++) {
                ExecutorService executorService;
                if (executor.equals(Executor.SINGLE_THREAD)) {
                    executorService = Executors.newSingleThreadExecutor();
                } else {
                    executorService = Executors.newFixedThreadPool(listSize);
                }

                long timeStartMillis = System.currentTimeMillis();

                for (Runnable runnable : finalList) {
                    executorService.submit(runnable);
                }

                executorService.shutdown();

                try {
                    executorService.awaitTermination(25000, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }

                long timeEndMillis = System.currentTimeMillis();
                total += timeEndMillis - timeStartMillis;
            }

            double averageTime = total / 50.0;
            System.out.println(i + ";" + (100 - i) + ";" + averageTime);
        }
    }
}

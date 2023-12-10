import repository.BDFileReader;
import runners.Executor;
import runners.ExecutorRunner;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int LIST_SIZE = 100;
        BDFileReader reader = new BDFileReader();
        List<String> words = reader.readFile("/src/files/bd.txt");
        if (words == null) {
            System.out.println("Erro ao ler o arquivo bd.txt. Verifique o caminho do arquivo");
        } else {
            List<Executor> executors = new ArrayList<>();
            executors.add(Executor.MULTI_THREAD);
            executors.add(Executor.SINGLE_THREAD);

            for (Executor executor : executors) {
                System.out.println(executor.name());

                ExecutorRunner executorRunner = new ExecutorRunner(executor);
                executorRunner.runTask(words, LIST_SIZE);
            }
        }

    }
}
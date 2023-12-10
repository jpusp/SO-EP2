package runners;

import java.util.List;
import java.util.Random;

public class Writer implements Runnable{
    private List<String> words;
    private Random random = new Random();

    public Writer(List<String> words) {
        this.words = words;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                int randomPosition = random.nextInt(words.size());
                ReaderWriterControl.waitForReaders();
                words.set(randomPosition, "MODIFICADO");
            }

            Thread.sleep(1);

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

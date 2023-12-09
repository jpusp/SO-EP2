package runners;

import java.util.List;
import java.util.Random;

public class Reader implements Runnable{
    private List<String> words;
    private String selectedWord;
    private Random random = new Random();

    public Reader(List<String> words) {
        this.words = words;
    }

    @Override
    public void run() {
        ReaderWriterControl.readerStarted();
        for (int i = 0; i < 100; i++) {
            int randomPosition = random.nextInt(words.size());
            selectedWord = words.get(randomPosition);
        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            ReaderWriterControl.readerFinished();
        }
        ReaderWriterControl.readerFinished();
    }
}

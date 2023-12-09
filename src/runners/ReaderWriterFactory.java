package runners;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReaderWriterFactory {


    public static List<Runnable> createReaderWriterList(
            List<String>words,
            int readers,
            int listSize
    ) {
        int writers = listSize - readers;

        List<Runnable> readersList = IntStream
                .range(0, readers)
                .mapToObj(position -> new Reader(words))
                .collect(Collectors.toList());

        List<Runnable> writersList = IntStream
                .range(0, writers)
                .mapToObj(position -> new Writer(words))
                .collect(Collectors.toList());

        List<Runnable> mergedList = Stream
                .concat(readersList.stream(), writersList.stream())
                .collect(Collectors.toList());

        Collections.shuffle(mergedList);

        return mergedList;
    }
}

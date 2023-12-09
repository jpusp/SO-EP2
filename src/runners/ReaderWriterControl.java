package runners;

public class ReaderWriterControl {
    private static int activeReaders = 0;
    private static final Object lock = new Object();

    public static void readerStarted() {
        synchronized (lock) {
            activeReaders++;
        }
    }

    public static void readerFinished() {
        synchronized (lock) {
            activeReaders--;
            lock.notifyAll();
        }
    }

    public static void waitForReaders() throws InterruptedException {
        synchronized (lock) {
            while (activeReaders > 0) {
                lock.wait();
            }
        }
    }
}
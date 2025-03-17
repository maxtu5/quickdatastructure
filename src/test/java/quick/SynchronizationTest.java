package quick;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizationTest {

    @Test
    void testSync() {
        QuickDataStructure<Integer> structureInt = new QuickPushDataStructure<>();

        ExecutorService executorService = Executors.newFixedThreadPool(15);

        IntStream.range(0, 1000)
                .forEach(count -> executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        structureInt.push(count);
                    }
                }));
        try {
            executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(999, structureInt.pop());
    }

}
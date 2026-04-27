import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    static class Bogie {
        String type;
        int capacity;

        Bogie(String type, int capacity) {
            this.type = type;
            this.capacity = capacity;
        }
    }

    private List<Bogie> getBogies() {
        List<Bogie> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Bogie("Sleeper", i));
        }
        return list;
    }

    @Test
    void testLoopFilteringLogic() {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : getBogies()) {
            if (b.capacity > 60) result.add(b);
        }
        assertTrue(result.stream().allMatch(b -> b.capacity > 60));
    }

    @Test
    void testStreamFilteringLogic() {
        List<Bogie> result = getBogies().stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertTrue(result.stream().allMatch(b -> b.capacity > 60));
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<Bogie> loop = new ArrayList<>();
        for (Bogie b : getBogies()) {
            if (b.capacity > 60) loop.add(b);
        }

        List<Bogie> stream = getBogies().stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertEquals(loop.size(), stream.size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        long start = System.nanoTime();
        getBogies().stream().filter(b -> b.capacity > 60).toList();
        long end = System.nanoTime();

        assertTrue((end - start) > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<Bogie> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(new Bogie("Sleeper", i % 100));
        }

        List<Bogie> result = list.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertTrue(result.size() > 0);
    }
}
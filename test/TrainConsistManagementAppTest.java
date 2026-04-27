import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    private List<Bogie> getSampleBogies() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));
        return bogies;
    }

    // 1️⃣ Capacity > threshold
    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > 70)
                .collect(Collectors.toList());

        assertEquals(2, result.size()); // Sleeper + General
    }

    // 2️⃣ Capacity == threshold
    @Test
    void testFilter_CapacityEqualToThreshold() {
        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > 72)
                .collect(Collectors.toList());

        assertFalse(result.stream().anyMatch(b -> b.capacity == 72));
    }

    // 3️⃣ Capacity < threshold
    @Test
    void testFilter_CapacityLessThanThreshold() {
        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertFalse(result.stream().anyMatch(b -> b.capacity < 60));
    }

    // 4️⃣ Multiple matches
    @Test
    void testFilter_MultipleBogiesMatching() {
        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > 50)
                .collect(Collectors.toList());

        assertEquals(3, result.size());
    }

    // 5️⃣ No matches
    @Test
    void testFilter_NoBogiesMatching() {
        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > 200)
                .collect(Collectors.toList());

        assertTrue(result.isEmpty());
    }

    // 6️⃣ All match
    @Test
    void testFilter_AllBogiesMatching() {
        List<Bogie> result = getSampleBogies().stream()
                .filter(b -> b.capacity > 10)
                .collect(Collectors.toList());

        assertEquals(4, result.size());
    }

    // 7️⃣ Empty list
    @Test
    void testFilter_EmptyBogieList() {
        List<Bogie> empty = new ArrayList<>();

        List<Bogie> result = empty.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertTrue(result.isEmpty());
    }

    // 8️⃣ Original list unchanged
    @Test
    void testFilter_OriginalListUnchanged() {
        List<Bogie> original = getSampleBogies();

        List<Bogie> result = original.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertEquals(4, original.size()); // original unchanged
    }
}
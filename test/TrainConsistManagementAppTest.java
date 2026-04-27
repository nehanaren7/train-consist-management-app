import org.junit.jupiter.api.Test;
import java.util.*;
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

    private List<Bogie> getBogies() {
        return Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24),
                new Bogie("Sleeper", 70),
                new Bogie("AC Chair", 60)
        );
    }

    // 1️⃣ Grouping works
    @Test
    void testGrouping_BogiesGroupedByType() {
        Map<String, List<Bogie>> result =
                getBogies().stream().collect(Collectors.groupingBy(b -> b.name));

        assertTrue(result.containsKey("Sleeper"));
        assertEquals(2, result.get("Sleeper").size());
    }

    // 2️⃣ Multiple bogies same group
    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        Map<String, List<Bogie>> result =
                getBogies().stream().collect(Collectors.groupingBy(b -> b.name));

        assertEquals(2, result.get("AC Chair").size());
    }

    // 3️⃣ Different types
    @Test
    void testGrouping_DifferentBogieTypes() {
        Map<String, List<Bogie>> result =
                getBogies().stream().collect(Collectors.groupingBy(b -> b.name));

        assertEquals(3, result.keySet().size());
    }

    // 4️⃣ Empty list
    @Test
    void testGrouping_EmptyBogieList() {
        List<Bogie> empty = new ArrayList<>();

        Map<String, List<Bogie>> result =
                empty.stream().collect(Collectors.groupingBy(b -> b.name));

        assertTrue(result.isEmpty());
    }

    // 5️⃣ Single category
    @Test
    void testGrouping_SingleBogieCategory() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("Sleeper", 70)
        );

        Map<String, List<Bogie>> result =
                list.stream().collect(Collectors.groupingBy(b -> b.name));

        assertEquals(1, result.size());
    }

    // 6️⃣ Correct keys
    @Test
    void testGrouping_MapContainsCorrectKeys() {
        Map<String, List<Bogie>> result =
                getBogies().stream().collect(Collectors.groupingBy(b -> b.name));

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    // 7️⃣ Group size validation
    @Test
    void testGrouping_GroupSizeValidation() {
        Map<String, List<Bogie>> result =
                getBogies().stream().collect(Collectors.groupingBy(b -> b.name));

        assertEquals(2, result.get("Sleeper").size());
    }

    // 8️⃣ Original list unchanged
    @Test
    void testGrouping_OriginalListUnchanged() {
        List<Bogie> original = getBogies();

        original.stream().collect(Collectors.groupingBy(b -> b.name));

        assertEquals(5, original.size());
    }
}
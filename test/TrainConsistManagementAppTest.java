import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    private int[] bubbleSort(int[] arr) {
        int[] a = arr.clone();
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    @Test
    void testSort_BasicSorting() {
        int[] result = bubbleSort(new int[]{72, 56, 24, 70, 60});
        assertArrayEquals(new int[]{24, 56, 60, 70, 72}, result);
    }

    @Test
    void testSort_AlreadySortedArray() {
        int[] result = bubbleSort(new int[]{24, 56, 60, 70, 72});
        assertArrayEquals(new int[]{24, 56, 60, 70, 72}, result);
    }

    @Test
    void testSort_DuplicateValues() {
        int[] result = bubbleSort(new int[]{72, 56, 56, 24});
        assertArrayEquals(new int[]{24, 56, 56, 72}, result);
    }

    @Test
    void testSort_SingleElementArray() {
        int[] result = bubbleSort(new int[]{50});
        assertArrayEquals(new int[]{50}, result);
    }

    @Test
    void testSort_AllEqualValues() {
        int[] result = bubbleSort(new int[]{40, 40, 40});
        assertArrayEquals(new int[]{40, 40, 40}, result);
    }
}
import java.util.Arrays;
import java.util.Random;

/**
 * Plain-Java tests for {@link QuickSort}, with no third-party dependencies.
 *
 * <p>Run with {@code javac QuickSortTest.java && java QuickSortTest}.
 */
public final class QuickSortTest {

    private static int checks;

    private QuickSortTest() {
    }

    public static void main(String[] args) {
        handlesNullAndShortArrays();
        sortsKnownArrays();
        sortsDuplicatesAndExtremes();
        sortsSortedAndReversedArrays();
        sortsLargeDuplicateArray();
        sortsRandomArraysAgainstReference();
        System.out.println("All " + checks + " checks passed.");
    }

    private static void handlesNullAndShortArrays() {
        QuickSort.sort(null);

        int[] empty = {};
        QuickSort.sort(empty);
        checkArray("empty array", new int[] {}, empty);

        int[] single = {42};
        QuickSort.sort(single);
        checkArray("single element", new int[] {42}, single);
    }

    private static void sortsKnownArrays() {
        int[] input = {10, 7, 8, 9, 1, 5, 3, 6, 2, 4};
        QuickSort.sort(input);
        checkArray("example array", new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, input);

        int[] negatives = {0, -3, 5, -10, 8, -1};
        QuickSort.sort(negatives);
        checkArray("negative numbers", new int[] {-10, -3, -1, 0, 5, 8}, negatives);
    }

    private static void sortsDuplicatesAndExtremes() {
        int[] duplicates = {4, 2, 4, 1, 2, 4, 2};
        QuickSort.sort(duplicates);
        checkArray("duplicates", new int[] {1, 2, 2, 2, 4, 4, 4}, duplicates);

        int[] extremes = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, 1};
        QuickSort.sort(extremes);
        checkArray("integer extremes",
                new int[] {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE}, extremes);
    }

    private static void sortsSortedAndReversedArrays() {
        int size = 1_000;
        int[] ascending = new int[size];
        int[] descending = new int[size];
        int[] expected = new int[size];
        for (int i = 0; i < size; i++) {
            ascending[i] = i;
            descending[i] = size - 1 - i;
            expected[i] = i;
        }

        QuickSort.sort(ascending);
        checkArray("already sorted array", expected, ascending);

        QuickSort.sort(descending);
        checkArray("reversed array", expected, descending);
    }

    private static void sortsLargeDuplicateArray() {
        int[] values = new int[100_000];
        Arrays.fill(values, 7);
        QuickSort.sort(values);
        for (int value : values) {
            if (value != 7) {
                throw new AssertionError("large duplicate array was not sorted");
            }
        }
        checks++;
    }

    private static void sortsRandomArraysAgainstReference() {
        Random random = new Random(42);
        for (int trial = 0; trial < 100; trial++) {
            int[] actual = new int[random.nextInt(201)];
            for (int i = 0; i < actual.length; i++) {
                actual[i] = random.nextInt(201) - 100;
            }
            int[] expected = actual.clone();
            Arrays.sort(expected);

            QuickSort.sort(actual);
            if (!Arrays.equals(expected, actual)) {
                throw new AssertionError("random array mismatch\n expected: "
                        + Arrays.toString(expected) + "\n actual:   " + Arrays.toString(actual));
            }
            checks++;
        }
    }

    private static void checkArray(String name, int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(name + " mismatch\n expected: "
                    + Arrays.toString(expected) + "\n actual:   " + Arrays.toString(actual));
        }
        checks++;
    }
}

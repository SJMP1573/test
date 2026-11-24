import java.util.Arrays;
import java.util.Random;

/**
 * In-place ascending quicksort for {@code int} arrays.
 *
 * <p>Implementation notes:
 * <ul>
 *   <li>the pivot is chosen at random, which avoids the worst case on already
 *       sorted input;</li>
 *   <li>three-way partitioning splits the array into values smaller than,
 *       equal to and greater than the pivot, so arrays with many duplicates
 *       still sort efficiently;</li>
 *   <li>the smaller partition is sorted recursively and the larger one is
 *       handled in a loop, keeping the recursion depth at O(log n).</li>
 * </ul>
 *
 * <p>Average time complexity: O(n log n); worst case: O(n^2); extra space:
 * O(log n) for the recursion stack.
 */
public final class QuickSort {

    private static final Random RANDOM = new Random();

    private QuickSort() {
    }

    /**
     * Sorts the given array in ascending order, in place. A {@code null},
     * empty or single-element array is left unchanged.
     *
     * @param numbers the array to sort
     */
    public static void sort(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return;
        }
        sort(numbers, 0, numbers.length - 1);
    }

    private static void sort(int[] numbers, int low, int high) {
        while (low < high) {
            int pivot = numbers[low + RANDOM.nextInt(high - low + 1)];

            // Dutch national flag partitioning:
            // [low, less) < pivot, [less, current) == pivot, (greater, high] > pivot.
            int less = low;
            int current = low;
            int greater = high;
            while (current <= greater) {
                if (numbers[current] < pivot) {
                    swap(numbers, less++, current++);
                } else if (numbers[current] > pivot) {
                    swap(numbers, current, greater--);
                } else {
                    current++;
                }
            }

            // Recurse into the smaller side and loop on the larger one.
            if (less - low < high - greater) {
                sort(numbers, low, less - 1);
                low = greater + 1;
            } else {
                sort(numbers, greater + 1, high);
                high = less - 1;
            }
        }
    }

    private static void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }

    /**
     * Sorts the integers given as command-line arguments and prints the result.
     * With no arguments, sorts a built-in example array.
     *
     * @param args integers to sort
     */
    public static void main(String[] args) {
        int[] numbers;
        if (args.length == 0) {
            numbers = new int[] {10, 7, 8, 9, 1, 5, 3, 6, 2, 4};
            System.out.println("Input:  " + Arrays.toString(numbers));
        } else {
            numbers = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                try {
                    numbers[i] = Integer.parseInt(args[i]);
                } catch (NumberFormatException e) {
                    System.err.println("Not an integer: " + args[i]);
                    System.err.println("Usage: java QuickSort [integer ...]");
                    System.exit(1);
                    return;
                }
            }
            System.out.println("Input:  " + Arrays.toString(numbers));
        }

        sort(numbers);
        System.out.println("Sorted: " + Arrays.toString(numbers));
    }
}

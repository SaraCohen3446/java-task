public class Main {

    public static void main(String[] args) {
        int[] numbers = {2, 5, 3, 4, 2};
        SubsequenceCounter(numbers);
    }

    // builds the longest increasing sequence from unique values and prints all prefixes
    public static void SubsequenceCounter(int[] numbers) {

        // find min and max in one pass
        int min = numbers[0], max = numbers[0];
        for (int num : numbers) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        // build exists array and count distinct at the same time
        boolean[] exists = new boolean[max - min + 1];
        int count = 0;
        for (int num : numbers) {
            int idx = num - min;
            if (!exists[idx]) {
                exists[idx] = true;
                count++;
            }
        }

        // build longest sequence
        int[] longestSequence = new int[count];
        for (int i = 0, idx = 0; i < exists.length; i++)
            if (exists[i]) longestSequence[idx++] = i + min;

        // print prefixes as arrays
        for (int len = longestSequence.length; len >= 2; len--) {
            System.out.print("[");
            for (int i = 0; i < len; i++) {
                System.out.print(longestSequence[i]);
                if (i < len - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
}

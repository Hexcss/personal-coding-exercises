import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = getValidFibonacciCount(scanner);

        FibonacciPrinter fibonacciPrinter = new FibonacciPrinter(n);
        fibonacciPrinter.print();
        scanner.close();
    }

    /**
     * Prompts the user to enter a valid Fibonacci count.
     *
     * @param scanner The scanner to read user input.
     * @return A valid Fibonacci count.
     */
    private static int getValidFibonacciCount(Scanner scanner) {
        int n;

        while (true) {
            System.out.print("Enter the number of Fibonacci numbers to generate: ");
            n = scanner.nextInt();

            if (n >= 0 && n <= Fibonacci.UPPER_LIMIT) {
                break;
            } else {
                System.out.println("Please enter a valid positive integer between 0 and " + Fibonacci.UPPER_LIMIT + ".");
            }
        }

        return n;
    }


    static class Fibonacci {
        private static final int UPPER_LIMIT = 50;

        // The memo array stores the calculated Fibonacci numbers to avoid recalculating them.
        private static final int[] memo = new int[UPPER_LIMIT + 1];

        /**
         * Returns the nth value in the Fibonacci sequence.
         *
         * @param n The index of the Fibonacci sequence.
         * @return The nth value in the Fibonacci sequence.
         * @throws IllegalArgumentException If the provided index is out of range.
         */
        public static int sequence(int n) {
            if (n < 0 || n > UPPER_LIMIT) {
                throw new IllegalArgumentException("n must be between 0 and " + UPPER_LIMIT);
            }

            if (n == 0 || n == 1) {
                return n;
            }

            // If the nth Fibonacci number is not already stored in the memo array, calculate and store it.
            if (memo[n] == 0) {
                memo[n] = sequence(n - 1) + sequence(n - 2);
            }

            // Return the nth Fibonacci number from the memo array.
            return memo[n];
        }
    }


    static class FibonacciPrinter {
        private final int n;

        public FibonacciPrinter(int n) {
            this.n = n;
        }

        /**
         * Prints the first n Fibonacci numbers.
         */
        public void print() {
            for (int i = 0; i < n; i++) {
                try {
                    System.out.println(Fibonacci.sequence(i));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
    }
}

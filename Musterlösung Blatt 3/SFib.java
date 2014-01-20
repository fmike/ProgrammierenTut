/**
 * A utility class for calculating the first n Semi-Fibonacci numbers.
 *
 * @author Felix Hofmann
 * @version 1.1
 */
public final class SFib {
    /**
     * Utility class should not be instantiable. Therefore all constructors are private.
     */
    private SFib() {

    }

    /**
     * Calculates and prints the first n Semi-Fibonacci numbers.
     *
     * @param args Count of the numbers to calculate in args[0].
     */
    public static void main(String[] args) {
        int argument = Integer.parseInt(args[0]);

        if (argument < 1) {
            System.out.println("The argument must be a number greater than zero");
            // you will learn about more ways to secure your program against unexpected input soon (-> Exceptions)
        } else {
            long[] sFibNumbers = calc(argument);
            print(sFibNumbers);
        }
    }

    /**
     * Calculates the first n Semi-Fibonacci numbers.
     *
     * @param n Count of the numbers to calculate.
     * @return An array of size n that contains the calculated numbers. Semi-Fibonacci number x is at index x - 1.
     */
    public static long[] calc(int n) {
        long[] sFibNumbers = new long[n]; // "memory" ;)
        sFibNumbers[0] = 1; // f(1) = 1

        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) { // even
                sFibNumbers[i - 1] = sFibNumbers[(i / 2) - 1];
            } else { // odd
                sFibNumbers[i - 1] = sFibNumbers[i - 2] + sFibNumbers[i - 3];
            }
        }

        return sFibNumbers;
    }

    /**
     * Prints a sequence of numbers comma separated.
     * The array must be initialized and its size be at least one.
     *
     * @param numbers Numbers to print.
     */
    public static void print(long[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            System.out.printf("%d, ", numbers[i]);
        }
        // Last number without tailing comma and with new line.
        System.out.printf("%d%n", numbers[numbers.length - 1]);
    }

}

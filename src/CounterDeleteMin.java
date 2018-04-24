/**
 * Counter class only count key comparision for deletemin operation
 */
public class CounterDeleteMin {
    private static int counter;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        CounterDeleteMin.counter = counter;
    }
}

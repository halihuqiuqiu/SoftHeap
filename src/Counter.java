/**
 * Counter class used to count all key comparision
 */
public class Counter {
    private static int counter;
    private static int insertCounter;
    private static int deleteCounter;
    private static int meldCounter;
    static int i;
    static int m;
    static int d;

    public static void reset(){
        i=0;
        m=0;
        d=0;
        counter=0;
        insertCounter=0;
        deleteCounter=0;
        meldCounter=0;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Counter.counter = counter;
    }

    public static int getInsertCounter() {
        return insertCounter;
    }

    public static void setInsertCounter(int insertCounter) {
        Counter.insertCounter = insertCounter;
    }

    public static int getDeleteCounter() {
        return deleteCounter;
    }

    public static void setDeleteCounter(int deleteCounter) {
        Counter.deleteCounter = deleteCounter;
    }

    public static int getMeldCounter() {
        return meldCounter;
    }

    public static void setMeldCounter(int meldCounter) {
        Counter.meldCounter = meldCounter;
    }
}

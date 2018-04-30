/**
 * Counter class used to count all key comparision
 */
public class Counter {
    /*total cost of all operation*/
    private static int counter;
    /*cost of insert*/
    private static int insertCounter;
    /*cost of delete*/
    private static int deleteCounter;
    /*cost of meld*/
    private static int meldCounter;
    /*number of insert operation*/
    private static int numInsert;
    /*number of delete operation*/
    private static int numDelete;
    /*number of meld operation*/
    private static int numMeld;

    public static void reset() {
        counter = 0;
        insertCounter = 0;
        deleteCounter = 0;
        meldCounter = 0;
        numInsert=0;
        numDelete=0;
        numMeld=0;
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

    public static int getNumInsert() {
        return numInsert;
    }

    public static void setNumInsert(int numInsert) {
        Counter.numInsert = numInsert;
    }

    public static int getNumDelete() {
        return numDelete;
    }

    public static void setNumDelete(int numDelete) {
        Counter.numDelete = numDelete;
    }

    public static int getNumMeld() {
        return numMeld;
    }

    public static void setNumMeld(int numMeld) {
        Counter.numMeld = numMeld;
    }
}

/**
 * Key list class
 */
public class ILCell {
    /* key value */
    private int key;
    /* next ILCell after this */
    private ILCell next;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ILCell getNext() {
        return next;
    }

    public void setNext(ILCell next) {
        this.next = next;
    }


    /**
     * ILcell to String
     *
     * @return
     */
    public String toString() {
        String s = "(";
        ILCell l = this;
        if (l != null) {
            s += l.getKey();
            l = l.getNext();
        }
        while (l != null) {
            s += "," + l.getKey();
            l = l.getNext();
        }
        s += ")";

        return s;
    }
}

package Simulation;

public class ILCell {
    private int key;
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
}

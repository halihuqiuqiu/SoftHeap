package Simulation;

public class Node {
    private int ckey,rank;
    private Node next, child;
    private ILCell il, il_tail;

    public int getCkey() {
        return ckey;
    }

    public void setCkey(int ckey) {
        this.ckey = ckey;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public ILCell getIl() {
        return il;
    }

    public void setIl(ILCell il) {
        this.il = il;
    }

    public ILCell getIl_tail() {
        return il_tail;
    }

    public void setIl_tail(ILCell il_tail) {
        this.il_tail = il_tail;
    }
}

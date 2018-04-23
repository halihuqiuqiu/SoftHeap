package Simulation;

public class SoftHeap {
    private Head header, tail;
    private int r;
    private double error;

    public SoftHeap(double error){
        this.header=new Head();
        this.tail = new Head();
        tail.setRank(Integer.MAX_VALUE);
        header.setNext(tail);
        tail.setPrev(header);
    }
}

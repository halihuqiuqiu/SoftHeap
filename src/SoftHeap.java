import sun.jvm.hotspot.oops.MethodCounters;

/**
 * Soft heap class
 */
public class SoftHeap {

    /* lable of the Heap */
    private String label;
    /* Header and tail of the heap */
    private Head header, tail;
    /* rank exceed threshold r, can has corruption, sift() again */
    private int r;
    /* error rate */
    private double error;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Head getHeader() {
        return header;
    }

    public void setHeader(Head header) {
        this.header = header;
    }

    public Head getTail() {
        return tail;
    }

    public void setTail(Head tail) {
        this.tail = tail;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    /**
     * Constructor only get error rate
     *
     * @param error
     */
    public SoftHeap(double error) {
        this.label = "H0";
        this.header = new Head();
        this.tail = new Head();
        header.setRank(-1);    // set rank of empty hearder to -1
        tail.setRank(Integer.MAX_VALUE); // set rank of empty tail to infinity
        header.setNext(tail);
        tail.setPrev(header);
    }

    /**
     * Insert key to heap
     *
     * @param newkey
     */
    public void insert(int newkey) {
        ILCell l = new ILCell();
        l.setKey(newkey);
        l.setNext(null);
        Node q = new Node();
        q.setRank(0);
        q.setCkey(newkey);
        q.setIl(l);
        q.setIl_tail(l);
        meld(q);
    }

    /**
     * meld a queue to the current heap
     *
     * @param q
     */
    public void meld(Node q) {
        Head tohead = header.getNext();
        Head prevhead = tohead.getPrev();
        //find first queue has euqal or larger rank
        while (tohead.getQueue() != null && tohead.getRank() < q.getRank()) {
            prevhead = tohead;
            tohead = tohead.getNext();
        }
        //union queue until no queue has same rank
        while (tohead.getQueue() != null && tohead.getRank() == q.getRank()) {
            Node top, bottom;
            Counter.setCounter(Counter.getCounter()+1);
            if (tohead.getQueue().getCkey() > q.getCkey()) {
                top = q;
                bottom = tohead.getQueue();
            } else {
                top = tohead.getQueue();
                bottom = q;
            }
            q = new Node();
            q.setRank(top.getRank() + 1);
            q.setCkey(top.getCkey());
            q.setChild(bottom);
            q.setNext(top);
            q.setIl(top.getIl());
            q.setIl_tail(top.getIl_tail());

            tohead = tohead.getNext();
        }

        // connect new queue into the heap
        Head h = new Head();
        h.setQueue(q);
        h.setRank(q.getRank());
        h.setPrev(prevhead);
        h.setNext(tohead);
        prevhead.setNext(h);
        if (tohead != null) {
            tohead.setPrev(h);
        }

    }


    /**
     * soft heap to string
     *
     * @return
     */
    public String toString() {
        String s = "";
        s += this.label + "\n";
        Head tohead = header.getNext();
        int i = 1;
        while (tohead.getQueue() != null) {
            s += "Q" + i + "\n";
            s += tohead.getQueue().toString() + "\n";
            tohead = tohead.getNext();
            i++;
        }
        return s;
    }


}

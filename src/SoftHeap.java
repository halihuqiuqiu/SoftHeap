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
        this.error = error;
        if (error == 0) {
            this.r = Integer.MAX_VALUE;
            //System.out.println("r:"+r);
        } else {
            this.r = 2 + 2 * (int) Math.ceil(Math.log(1 / error) / Math.log(2));
            //System.out.println("r:"+r);
        }
        this.label = "H";
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
            Counter.setCounter(Counter.getCounter() + 1);
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

        fixMinList(h);

    }


    public void meld(SoftHeap sheap) {
        Head tohead = sheap.getHeader().getNext();
        while (tohead.getQueue() != null) {
            meld(tohead.getQueue());
            tohead = tohead.getNext();
        }
    }

    public void fixMinList(Head h) {
        Head tmpmin;
        if (h.getNext() == tail) {
            tmpmin = h;
        } else {
            tmpmin = h.getNext().getSuffix_min();
        }

        while (h != header) {
            Counter.setCounter(Counter.getCounter() + 1);
            if (h.getQueue().getCkey() < tmpmin.getQueue().getCkey()) {
                tmpmin = h;
            }
            h.setSuffix_min(tmpmin);
            h = h.getPrev();
        }
    }

    public Node sift(Node v) {
        Node tmp;
        v.setIl(null);
        v.setIl_tail(null);
        if (v.getNext() == null && v.getChild() == null) {
            v.setCkey(Integer.MAX_VALUE);
            return v;
        }
        v.setNext(sift(v.getNext()));
        Counter.setCounter(Counter.getCounter() + 1);   //counter +1
        if (v.getNext().getCkey() > v.getChild().getCkey()) {
            tmp = v.getChild();
            v.setChild(v.getNext());
            v.setNext(tmp);
        }

        v.setIl(v.getNext().getIl());
        v.setIl_tail(v.getNext().getIl_tail());
        v.setCkey(v.getNext().getCkey());

        //second sift
        if (v.getRank() > r && (v.getRank() % 2 == 1 || v.getChild().getRank() < v.getRank() - 1)) {
            v.setNext(sift(v.getNext()));
            Counter.setCounter(Counter.getCounter() + 1);  //counter +1
            if (v.getNext().getCkey() > v.getChild().getCkey()) {
                tmp = v.getChild();
                v.setChild(v.getNext());
                v.setNext(tmp);
            }
            if (v.getNext().getCkey() != Integer.MAX_VALUE && v.getNext().getIl() != null) {
                v.getNext().getIl_tail().setNext(v.getIl());
                v.setIl(v.getNext().getIl());
                if(v.getIl_tail()==null){
                    v.setIl_tail(v.getNext().getIl_tail());
                }
                v.setCkey(v.getNext().getCkey());
            }

        }

        if(v.getChild().getCkey()==Integer.MAX_VALUE){
            if(v.getNext().getCkey()==Integer.MAX_VALUE){
                v.setChild(null);
                v.setNext(null);
            }else{
                v.setChild(v.getNext().getChild());
                v.setNext(v.getNext().getNext());

            }
        }



        return v;
    }

    public int deletemin (){
        if(header.getNext()==tail){
            throw new IllegalStateException();
        }
        Head h = header.getNext().getSuffix_min();

        while (h.getQueue().getIl()==null){
            Node tmp=h.getQueue();
            int childcount=0;
            while (tmp.getNext()!=null){   //count childen
                tmp=tmp.getNext();
                childcount++;
            }
            if(childcount<h.getRank()/2){
                h.getPrev().setNext(h.getNext());
                h.getNext().setPrev(h.getPrev()); // delete this head
                fixMinList(h.getPrev());
                tmp=h.getQueue();
                while (tmp.getNext()!=null){
                    meld(tmp.getChild());
                    tmp=tmp.getNext();     //meld each child to heap
                }
            }else {
                h.setQueue(sift(h.getQueue()));
                if(h.getQueue().getCkey()==Integer.MAX_VALUE){
                    h.getPrev().setNext(h.getNext());
                    h.getNext().setPrev(h.getPrev()); // delete this head
                    h=h.getPrev();
                    fixMinList(h);
                }
            }
            h=header.getNext().getSuffix_min();

        }

        int min = h.getQueue().getIl().getKey();
        h.getQueue().setIl(h.getQueue().getIl().getNext());
        if(h.getQueue().getIl()==null){
            h.getQueue().setIl_tail(null);   //empty item list
        }
        //System.out.println("delete:"+min);
        return min;

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


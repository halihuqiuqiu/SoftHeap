package Simulation;

public class SoftHeap {
    private Head header, tail;
    private int r;
    private double error;

    public SoftHeap(double error){
        this.header=new Head();
        this.tail = new Head();
        header.setRank(-1);    // set rank of empty hearder to -1
        tail.setRank(Integer.MAX_VALUE); // set rank of empty tail to infinity
        header.setNext(tail);
        tail.setPrev(header);
    }

    public void insert(int newkey){
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

    public void meld(Node q){
        Head tohead = header.getNext();
        Head prevhead=tohead;
        while (tohead!=null && tohead.getRank()< q.getRank()){
            prevhead= tohead;
            tohead=tohead.getNext();
        }
        while (tohead!=null && tohead.getRank()== q.getRank()){
            Node top,bottom;
            if(tohead.getQueue().getRank()>q.getRank()){
                top = q;
                bottom = tohead.getQueue();
            }else{
                top=tohead.getQueue();
                bottom=q;
            }
            q=new Node();
            q.setRank(top.getRank()+1);
            q.setCkey(top.getCkey());
            q.setChild(bottom);
            q.setNext(top);
            q.setIl(top.getIl());
            q.setIl_tail(top.getIl_tail());

            tohead=tohead.getNext();
        }

        Head h = new Head();
        h.setQueue(q);
        h.setRank(q.getRank());
        h.setPrev(prevhead);
        h.setNext(tohead);
        prevhead.setNext(h);
        if(tohead!=null){
            tohead.setPrev(h);
        }

    }
}

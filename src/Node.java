/**
 * Node class
 */
public class Node {
    /* upper bound of key list and rank */
    private int ckey, rank;
    /* two k-1 queue */
    private Node next, child;
    /* head and tail of key list */
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

    /**
     * Node to string
     *
     * @return
     */
    public String toString() {
        Node q = this;
        String start = "[";
        if(q.getIl()==null){
            start+="(),";
        }else {
            start+=q.getIl().toString() + "," + q.getCkey() + ",";
        }

        String end = "]";
        if (q.getNext() == null && q.getChild() == null) {   //no subtree
            end = "-" + end;
        } else {
            end = ")" + end;
            Node child = q.getChild();
            Node root = q;
            if (child != null) {
                end = child.toString() + end;
                if (root.getNext() != null) {
                    root = root.getNext();
                    child = root.getChild();
                } else {
                    child = null;
                }
            }
            while (child != null) {
                end = child.toString() + "," + end; //from second subtree, sperate by ,
                if (root.getNext() != null) {
                    root = root.getNext();
                    child = root.getChild();
                } else {
                    child = null;
                }
            }
            end = "(" + end;
        }
        return start + end;
    }
}

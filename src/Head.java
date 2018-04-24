public class Head {
    /* the queue head point to */
    private Node queue;
    /* previousm, next and the head has min after current head */
    private Head prev, next, suffix_min;
    /* rand of the queue it point to */
    private int rank;

    public Node getQueue() {
        return queue;
    }

    public void setQueue(Node queue) {
        this.queue = queue;
    }

    public Head getPrev() {
        return prev;
    }

    public void setPrev(Head prev) {
        this.prev = prev;
    }

    public Head getNext() {
        return next;
    }

    public void setNext(Head next) {
        this.next = next;
    }

    public Head getSuffix_min() {
        return suffix_min;
    }

    public void setSuffix_min(Head suffix_min) {
        this.suffix_min = suffix_min;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

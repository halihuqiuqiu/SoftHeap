public class TestDelete {
    public static void main(String[] args) {
        SoftHeap sheap = new SoftHeap(0);
        sheap.insert(3);
        sheap.insert(2);
        sheap.insert(1);
        String s = sheap.toString();
        //System.out.println(s);
        //System.out.println(sheap.getHeader().getNext().getSuffix_min().getQueue());
        sheap.deletemin();
        System.out.println(sheap.toString());
        sheap.deletemin();
        System.out.println(sheap.toString());
    }
}

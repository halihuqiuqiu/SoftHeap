package Simulation;

public class TestToString {
    public static void main(String[] args) {

        SoftHeap sheap = new SoftHeap(0.01);
        sheap.insert(1);
        sheap.insert(2);
        sheap.insert(3);
        sheap.insert(4);
        sheap.insert(5);
        sheap.insert(6);
        sheap.insert(7);
        sheap.insert(8);
        sheap.insert(9);
        sheap.insert(10);
        sheap.insert(11);
        sheap.insert(12);
        sheap.insert(13);
        sheap.insert(14);
        sheap.insert(15);
        sheap.insert(16);
        sheap.insert(17);
        sheap.insert(18);
        sheap.insert(19);
        sheap.insert(20);
        sheap.insert(21);



        Head h = sheap.getHeader().getNext();
        Node n = h.getQueue();
        ILCell l = n.getIl();
        //String s =l.toString();

        String s = sheap.toString();
        System.out.println(s);

        //System.out.println(sheap.toString());
    }
}

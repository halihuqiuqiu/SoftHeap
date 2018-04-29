import java.util.*;

public class Simulation2 {
    public static void main(String[] args) {


        double i=0.0;
        while (i<=0.51){
            simulate2(i,100000);
            //System.out.println(i);
            if(i<0.1){
                i+=0.00125;
            }else if(i<0.2){
                i+=0.0025;
            }else if(i<0.3){
                i+=0.005;
            }else if(i<0.4){
                i+=0.01;
            }else{
                i+=0.02;
            }
        }
        /*
        simulate2(0.00125,100000);
        simulate2(0.01,100000);
        simulate2(0.02,100000);
        simulate2(0.04,100000);
        simulate2(0.08,100000);
        simulate2(0.1,100000);
        simulate2(0.2,100000);
        simulate2(0.3,100000);
        simulate2(0.4,100000);
        simulate2(0.5,100000);

*/



    }

    public static void simulate2(double error, int n){
            Random ran = new Random();
            Counter.reset();  // initialize all counter to 0
            SoftHeap sheapc = new SoftHeap(error
            );
            for (int i = 0; i < n; i++) {

                int before = Counter.getCounter();
                sheapc.insert(ran.nextInt(100000));  //insert random number
                int after = Counter.getCounter();
                Counter.setInsertCounter(Counter.getInsertCounter() + after - before);
                //System.out.println(heapMap.size());

            }

            for (int i = 0; i < n/2; i++) {

                int before = Counter.getCounter();
                sheapc.deletemin();
                int after = Counter.getCounter();
                Counter.setDeleteCounter(Counter.getDeleteCounter()+after-before);
                //System.out.println(i+";"+Counter.getDeleteCounter());

            }
            System.out.println(error + "," + Counter.getCounter() +
                    "," + Counter.getInsertCounter() +
                    "," + Counter.getDeleteCounter());
        }
    }


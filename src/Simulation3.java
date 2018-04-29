import java.util.*;

public class Simulation3 {

    public static void main(String[] args) {

        simulate(0.00125,100000);
        simulate(0.01,100000);
        simulate(0.05,100000);
        simulate(0.1,100000);
        simulate(0.3,100000);
        simulate(0.5,100000);





        /*
        simulate(0.1,100000);
        simulate(0.2,100000);
        simulate(0.3,100000);
        simulate(0.4,100000);
        simulate(0.5,100000);
        */

        /*
        double error=0.0;
        simulate(error,60000);
        simulate(error,70000);
        simulate(error,80000);
        simulate(error,90000);
        simulate(error,100000);
        */

        /*
        double error=0.01;
        while (error<=0.2){
            simulate(error,60000);
            simulate(error,70000);
            simulate(error,80000);
            simulate(error,90000);
            simulate(error,100000);
            error+=0.02;
        }
        */
        /*
        double error=0.1;
        while (error<=0.5){
            simulate(error,60000);
            simulate(error,70000);
            simulate(error,80000);
            simulate(error,90000);
            simulate(error,100000);
            error+=0.1;
        }
        */

        /*
        System.out.println("Error,Counter,DeleteMinCounter");

        double i=0.0;
        while (i<=0.51){
            simulate(i);
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
        */

    }

    public static void simulate(double error, int n){

        Random ran = new Random();
        Counter.reset();  // initialize all counter to 0
        SoftHeap sheapc = new SoftHeap(error);
        for(int i=0; i<n;i++){
            if (i==50000){
                Counter.reset();
            }
            int r = ran.nextInt(10);
            //System.out.println("r:"+r);

            if(r<8){
                Counter.i++;
                int before = Counter.getCounter();
                sheapc.insert(ran.nextInt(100000));  //insert random number
                int after = Counter.getCounter();
                Counter.setInsertCounter(Counter.getInsertCounter() + after - before);
                //System.out.println(heapMap.size());

            }else{
                Counter.d++;
                try{
                    int before = Counter.getCounter();
                    sheapc.deletemin();
                    int after = Counter.getCounter();
                    Counter.setDeleteCounter(Counter.getDeleteCounter()+after-before);
                    //System.out.println(i+";"+Counter.getDeleteCounter());
                }catch (Exception e){
                    continue;
                }



            }

        }

        System.out.println(error+","+Counter.getCounter()+
                ","+Counter.getInsertCounter()+
                ","+Counter.getMeldCounter()+
                ","+Counter.getDeleteCounter()+
                ","+Counter.i+","+Counter.m+","+Counter.d);

        //System.out.println(heapMap.size());
        //System.out.println(heapMap.get("H10000"));

        /**
         for (String label: heapMap.keySet()){

         System.out.println(heapMap.get(label).toString());
         }
         */
    }
}

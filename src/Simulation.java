import java.util.*;

public class Simulation {

    public static void main(String[] args) {


        double i=0.0;
        int n =100000;
        while (i<=0.51){
            simulate(i,n);
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
        simulate(0.00125,100000);
        simulate(0.01,100000);
        simulate(0.05,100000);
        simulate(0.1,100000);
        simulate(0.3,100000);
        simulate(0.5,100000);
        */

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



    }

    public static void simulate(double error, int n){

        int numHeap=0;
        Map<String, SoftHeap> heapMap = new LinkedHashMap<>();
        Counter.reset();  // initialize all counter to 0

        Random ran = new Random();
        for(int i=0; i<n;i++){
            if (i==50000){
                Counter.reset();
            }
            int r = ran.nextInt(10);
            //System.out.println("r:"+r);

            if(r<3){
                int before = Counter.getCounter();
                String labelc = "H"+(++numHeap);
                SoftHeap sheapc = new SoftHeap(error);     //create new heap to insert
                sheapc.setLabel(labelc);
                sheapc.insert(ran.nextInt(100000));  //insert random number
                heapMap.put(labelc,sheapc);
                int after= Counter.getCounter();
                Counter.setInsertCounter(Counter.getInsertCounter()+after-before);
                //System.out.println(heapMap.size());

            }else if(r<6){
                if(heapMap.size()==0){
                    continue;            //no heap exist
                }
                int before = Counter.getCounter();
                List<String> keylist = new ArrayList<String>(heapMap.keySet());
                String heap = keylist.get(ran.nextInt(keylist.size()));    //radom  choose an exist heap
                heapMap.get(heap).insert(ran.nextInt(100000)); //insert random number
                int after= Counter.getCounter();
                Counter.setInsertCounter(Counter.getInsertCounter()+after-before);
                //System.out.println(heapMap.size());

            }else if(r<7){
                if(heapMap.size()<2){      // no heap to meld
                    continue;
                }
                int before = Counter.getCounter();
                List<String> keylist = new ArrayList<String>(heapMap.keySet());
                String heap1 = keylist.get(ran.nextInt(keylist.size()));
                String heap2 = keylist.get(ran.nextInt(keylist.size()));
                while(heap1==heap2){        //not meld same heap
                    heap2 = keylist.get(ran.nextInt(keylist.size()));
                }
                heapMap.get(heap1).meld(heapMap.get(heap2));
                heapMap.remove(heap2);//remove the melded heap
                int after= Counter.getCounter();
                Counter.setMeldCounter(Counter.getMeldCounter()+after-before);
                //System.out.println(heapMap.size());
            }else{
                try{
                    int before = Counter.getCounter();
                    List<String> keylist = new ArrayList<String>(heapMap.keySet());
                    String heap = keylist.get(ran.nextInt(keylist.size()));    //radom  choose an exist heap
                    heapMap.get(heap).deletemin();
                    int after= Counter.getCounter();
                    Counter.setDeleteCounter(Counter.getDeleteCounter()+after-before);
                }catch (Exception e){
                    continue;
                }

            }

        }


        System.out.println(error+","+Counter.getCounter()+
                ","+Counter.getInsertCounter()+
                ","+Counter.getMeldCounter()+
                ","+Counter.getDeleteCounter());

        //System.out.println(heapMap.size());
        //System.out.println(heapMap.get("H10000"));

        /**
         for (String label: heapMap.keySet()){

         System.out.println(heapMap.get(label).toString());
         }
         */
    }
}

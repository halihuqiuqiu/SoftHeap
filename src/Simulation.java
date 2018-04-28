import java.util.*;

public class Simulation {

    public static void main(String[] args) {


        simulate(0.25,10);
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

        int numHeap=0;
        Map<String, SoftHeap> heapMap = new LinkedHashMap<>();
        Counter.setCounter(0);  // initialize Counter counter to 0
        CounterDeleteMin.setCounter(0);  // initialize DeleteMin counter to 0

        Random ran = new Random();
        for(int i=0; i<n;i++){
            if (i<50000){
                Counter.setCounter(0);
                CounterDeleteMin.setCounter(0);
            }
            int r = ran.nextInt(10);
            //System.out.println("r:"+r);

            if(r<3){
                String labelc = "H"+(++numHeap);
                SoftHeap sheapc = new SoftHeap(error);     //create new heap to insert
                sheapc.setLabel(labelc);
                sheapc.insert(ran.nextInt(100000));  //insert random number
                heapMap.put(labelc,sheapc);
                //System.out.println(heapMap.size());

            }else if(r<6){
                if(heapMap.size()==0){
                    continue;            //no heap exist
                }
                List<String> keylist = new ArrayList<String>(heapMap.keySet());
                String heap = keylist.get(ran.nextInt(keylist.size()));    //radom  choose an exist heap
                heapMap.get(heap).insert(ran.nextInt(100000)); //insert random number
                //System.out.println(heapMap.size());

            }else if(r<7){
                if(heapMap.size()<2){      // no heap to meld
                    continue;
                }
                List<String> keylist = new ArrayList<String>(heapMap.keySet());
                String heap1 = keylist.get(ran.nextInt(keylist.size()));
                String heap2 = keylist.get(ran.nextInt(keylist.size()));
                while(heap1==heap2){        //not meld same heap
                    heap2 = keylist.get(ran.nextInt(keylist.size()));
                }
                heapMap.get(heap1).meld(heapMap.get(heap2));
                heapMap.remove(heap2);//remove the melded heap
                //System.out.println(heapMap.size());
            }else{
                try{
                    int before = Counter.getCounter();
                    List<String> keylist = new ArrayList<String>(heapMap.keySet());
                    String heap = keylist.get(ran.nextInt(keylist.size()));    //radom  choose an exist heap
                    heapMap.get(heap).deletemin();
                    int after= Counter.getCounter();
                    CounterDeleteMin.setCounter(CounterDeleteMin.getCounter()+after-before);
                }catch (Exception e){
                    continue;
                }

            }

        }

        System.out.println(error+","+Counter.getCounter()+","+CounterDeleteMin.getCounter());

        //System.out.println(heapMap.size());
        //System.out.println(heapMap.get("H10000"));

        /**
         for (String label: heapMap.keySet()){

         System.out.println(heapMap.get(label).toString());
         }
         */
    }
}

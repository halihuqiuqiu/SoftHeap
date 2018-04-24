import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Simulation {

    public static void main(String[] args) {

        simulate(0);
        simulate(0.01);
        simulate(0.05);
        simulate(0.1);
        simulate(0.2);
        simulate(0.3);
        simulate(0.5);


    }

    public static void simulate(double error){

        Map<String, SoftHeap> heapMap = new LinkedHashMap<>();
        Counter.setCounter(0);  // initialize Counter counter to 0
        CounterDeleteMin.setCounter(0);  // initialize DeleteMin counter to 0

        Random ran = new Random();
        for(int i=0; i<100000;i++){
            int r = ran.nextInt(10);
            if(r<3){
                String labelc = "H"+heapMap.size();
                SoftHeap sheapc = new SoftHeap(error);     //create new heap to insert
                sheapc.setLabel(labelc);
                sheapc.insert(ran.nextInt(100000));  //insert random number
                heapMap.put(labelc,sheapc);
            }else if(r<6){
                if(heapMap.size()==0){
                    continue;            //no heap exist
                }
                int choose = ran.nextInt(heapMap.size());    //radom  choose an exist heap
                heapMap.get("H"+choose).insert(ran.nextInt(100000)); //insert random number
            }else if(r<7){
                if(heapMap.size()<2){      // no heap to meld
                    continue;
                }
                int heap1 = ran.nextInt(heapMap.size());
                int heap2 = ran.nextInt(heapMap.size());
                while(heap1==heap2){        //not meld same heap
                    heap2 = ran.nextInt(heapMap.size());
                }
                heapMap.get("H"+heap1).meld(heapMap.get("H"+heap2));
            }else{
                continue;
            }

        }

        System.out.println(Counter.getCounter());
        //System.out.println(heapMap.get("H1"));

        /**
         for (String label: heapMap.keySet()){

         System.out.println(heapMap.get(label).toString());
         }
         */
    }
}

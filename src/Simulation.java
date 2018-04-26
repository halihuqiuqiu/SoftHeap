import java.util.*;

public class Simulation {

    public static void main(String[] args) {




        simulate(0.4);

        /**
         *
         simulate(1.0/2);
         simulate(1.0/4);
         simulate(1.0/8);
         simulate(1.0/16);
         simulate(1.0/32);
        simulate(0.01);
        simulate(0.05);
        simulate(0.1);
        simulate(0.2);
        simulate(0.3);
        simulate(0.5);
         */

    }

    public static void simulate(double error){

        int numHeap=0;
        Map<String, SoftHeap> heapMap = new LinkedHashMap<>();
        Counter.setCounter(0);  // initialize Counter counter to 0
        CounterDeleteMin.setCounter(0);  // initialize DeleteMin counter to 0

        Random ran = new Random();
        for(int i=0; i<100000;i++){
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

        //System.out.println(Counter.getCounter());
        System.out.println(CounterDeleteMin.getCounter());

        //System.out.println(heapMap.size());
        //System.out.println(heapMap.get("H10000"));

        /**
         for (String label: heapMap.keySet()){

         System.out.println(heapMap.get(label).toString());
         }
         */
    }
}

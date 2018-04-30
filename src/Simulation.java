import java.util.*;

/**
 * Simulation class for different error rate
 * with prob .3 insert into a new empty heap
 * with prob .3 insert into a randomly chosen heap
 * with prob .1 meld two randomly chosen heaps
 * with prob .3 delete(min) from a randomly chosen heap
 */
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
        simulate(0.00125, 100000);
        simulate(0.005, 100000);
        simulate(0.01, 100000);
        simulate(0.05, 100000);
        simulate(0.1, 100000);
        simulate(0.2, 100000);
        simulate(0.3, 100000);
        simulate(0.4, 100000);
        simulate(0.5, 100000);
        */

    }

    /**
     * simulate method
     * @param error, error rate
     * @param n, number of total operation
     */

    public static void simulate(double error, int n){

        Counter.reset();  // initialize all counter to 0
        int numHeap=0;
        Map<String, SoftHeap> heapMap = new LinkedHashMap<>();
        Random ran = new Random();

        for(int i=0; i<n;i++){
            if (i==50000){
                Counter.reset();  //reset count at 50000 iteration
            }
            int r = ran.nextInt(10);
            if(r<3){  //with prob 0.3 insert into a new empty heap
                String labelc = "H"+(++numHeap);
                SoftHeap sheapc = new SoftHeap(error);     //create new heap to insert
                sheapc.setLabel(labelc);
                heapMap.put(labelc,sheapc);

            }else if(r<6){ //with prob 0.3 insert into a randomly chosen heap
                if(heapMap.size()==0){
                    continue;            //no heap exist
                }
                int before = Counter.getCounter();
                List<String> keylist = new ArrayList<String>(heapMap.keySet());
                String heap = keylist.get(ran.nextInt(keylist.size()));    //radom  choose an exist heap
                heapMap.get(heap).insert(ran.nextInt(100000)); //insert random number
                int after= Counter.getCounter();
                Counter.setInsertCounter(Counter.getInsertCounter()+after-before);
                Counter.setNumInsert(Counter.getNumInsert()+1);

            }else if(r<7){ //with prob 0.1 meld two randomly chosen heaps
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
                Counter.setNumMeld(Counter.getNumMeld()+1);
            }else{  //with prob 0.3 delete(min) from a randomly chosen heap
                try{
                    int before = Counter.getCounter();
                    List<String> keylist = new ArrayList<String>(heapMap.keySet());
                    String heap = keylist.get(ran.nextInt(keylist.size()));    //radom  choose an exist heap
                    heapMap.get(heap).deletemin();
                    int after= Counter.getCounter();
                    Counter.setDeleteCounter(Counter.getDeleteCounter()+after-before);
                    Counter.setNumDelete(Counter.getNumDelete()+1);
                }catch (Exception e){
                    continue;
                }

            }

        }
        int numOperation = Counter.getNumInsert()+Counter.getDeleteCounter()+Counter.getNumMeld();

        System.out.println(error+","+(double)Counter.getCounter()/numOperation+
                ","+(double)Counter.getInsertCounter()/Counter.getNumInsert()+
                ","+(double)Counter.getMeldCounter()/Counter.getNumMeld()+
                ","+(double)Counter.getDeleteCounter()/Counter.getNumDelete()+
                ","+Counter.getCounter()+
                ","+Counter.getInsertCounter()+
                ","+Counter.getMeldCounter()+
                ","+Counter.getDeleteCounter()+
                ","+Counter.getNumInsert()+
                ","+Counter.getNumMeld()+
                ","+Counter.getNumDelete()
        );
        /**
         for (String label: heapMap.keySet()){
         System.out.println(heapMap.get(label).toString());
         }
         */
    }
}

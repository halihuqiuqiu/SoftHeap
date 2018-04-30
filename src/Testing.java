import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Test class
 * input file with error rate and operation
 * after all operation, print heap
 */
public class Testing {
    public static void main(String[] args) {
        String filename = args[0];
        double error=0.0;
        ArrayList<String> input = new ArrayList<>();
        Map<String, SoftHeap> heapMap = new LinkedHashMap<>();
        try {
            Scanner sc = new Scanner(new File(filename));
            error = Double.parseDouble(sc.nextLine());
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] s = line.split(",");
                input.addAll(Arrays.asList(s));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not find");
        }
        for (int i = 0; i < input.size(); i++) {
            switch (input.get(i)) {
                case "c":
                    String labelc = "H"+input.get(++i);
                    SoftHeap sheapc = new SoftHeap(error);
                    sheapc.setLabel(labelc);
                    heapMap.put(labelc,sheapc);
                    break;
                case "i":
                    int key = Integer.parseInt(input.get(++i));
                    String labeli = "H"+input.get(++i);
                    try{
                        SoftHeap sheapi= heapMap.get(labeli);
                        sheapi.insert(key);
                    }catch (Exception e){
                        break;
                    }
                    break;
                case "d":
                    String labeld = "H"+input.get(++i);
                    try {
                        SoftHeap sheapd= heapMap.get(labeld);
                        sheapd.deletemin();
                    }catch (Exception e){
                        break;
                    }

                    break;
                case "m":
                    //new heap label is the min(label1, label2)
                    int label1=Integer.parseInt(input.get(++i));
                    int label2=Integer.parseInt(input.get(++i));
                    String heap1="H"+(label1<=label2?label1:label2);
                    String heap2="H"+(label1>label2?label1:label2);
                    try{
                        heapMap.get(heap1).meld(heapMap.get(heap2));
                        heapMap.remove(heap2);//remove the melded heap
                    }catch (Exception e){
                        break;
                    }

                    break;
                default:
                    i++;  //ignore
                    break;
            }
        }
        for (String label: heapMap.keySet()){
            //System.out.println(heapMap.get(label).getHeader().getNext().getRank());
            System.out.println(heapMap.get(label).toString());
        }


    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Testing {
    public static void main(String[] args) {
        String filename = args[0];
        ArrayList<String> input = new ArrayList<>();
        Map<String, SoftHeap> heapMap = new LinkedHashMap<>();
        try {
            Scanner sc = new Scanner(new File(filename));
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
            switch (input.get(i)) {  ///////////////////////////////不存在的考虑下
                case "c":
                    String labelc = "H"+input.get(++i);
                    SoftHeap sheapc = new SoftHeap(0);
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
                    String heap1= "H"+input.get(++i);
                    String heap2= "H"+input.get(++i);
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

            System.out.println(heapMap.get(label).getHeader().getNext().getRank());
            System.out.println(heapMap.get(label).toString());
        }


    }
}

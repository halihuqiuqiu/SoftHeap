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
            switch (input.get(i)) {
                case "c":
                    String labelc = input.get(++i);
                    SoftHeap sheapc = new SoftHeap(0);
                    sheapc.setLabel("H"+labelc);
                    heapMap.put(labelc,sheapc);
                    break;
                case "i":
                    int key = Integer.parseInt(input.get(++i));
                    String labeli = input.get(++i);
                    SoftHeap sheapi= heapMap.get(labeli);
                    sheapi.insert(key);
                    break;
                case "d":
                    i++;
                    break;
                case "m":
                    i++;
                    break;
                default:
                    i++;  //ignore
                    break;
            }
        }

        for (String label: heapMap.keySet()){

            System.out.println(heapMap.get(label).toString());
        }


    }
}

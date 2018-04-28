public class TestGenerator {
    public static void main(String[] args) {
        for (int i=1;i<=1024;i++){
            System.out.print("i,"+i+",1,");
            if (i%20==1){
                System.out.println();
            }
        }

    }
}

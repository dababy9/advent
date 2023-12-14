import java.io.File;
import java.util.Scanner;
public class Part2 {

    public static int lowestWrap(int[] arr){
        int num1, num2;
        if(arr[0] > arr[1] && arr[0] > arr[2]){
            num1 = arr[1];
            num2 = arr[2];
        } else if(arr[1] > arr[2]) {
            num1 = arr[0];
            num2 = arr[2];
        } else {
            num1 = arr[0];
            num2 = arr[1];
        }
        return 2 * (num1 + num2);
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0;
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split("x");
                int[] dimensions = new int[line.length];
                for(int i = 0; i < 3; i++) dimensions[i] = Integer.parseInt(line[i]);
                sum += dimensions[0]*dimensions[1]*dimensions[2];
                sum += lowestWrap(dimensions);
            }
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
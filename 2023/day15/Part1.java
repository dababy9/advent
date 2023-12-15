import java.io.File;
import java.util.Scanner;
public class Part1 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            String[] s = scan.nextLine().split(",");
            int total = 0;
            for(int i = 0; i < s.length; i++){
                int sum = 0;
                char[] curr = s[i].toCharArray();
                for(int j = 0; j < curr.length; j++)
                    sum = ((sum + curr[j]) * 17) % 256;
                total += sum;
            }
            System.out.println(total);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 
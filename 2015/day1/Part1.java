import java.io.File;
import java.util.Scanner;
public class Part1 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            char[] c = scan.nextLine().toCharArray();
            int sum = 0;
            for(int i = 0; i < c.length; i++)
                sum += (c[i] == '(' ? 1 : -1);
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 
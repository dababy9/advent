import java.io.File;
import java.util.Scanner;
public class Part2 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            char[] c = scan.nextLine().toCharArray();
            int sum = 0;
            for(int i = 0; i < c.length; i++){
                sum += (c[i] == '(' ? 1 : -1);
                if(sum < 0){
                    System.out.println(i+1);
                    break;
                }
            }
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
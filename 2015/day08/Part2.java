import java.io.File;
import java.util.Scanner;
public class Part2 {

    public static int count(String s){
        char[] c = s.toCharArray();
        int r = 0;
        for(int i = 0; i < c.length; i++, r++)
            if(c[i] == '\\' || c[i] == '"') r++;
        return r + 2;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0;
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                sum += count(line);
                sum -= line.length();
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
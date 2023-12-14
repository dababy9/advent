import java.io.File;
import java.util.Scanner;
public class Part1 {

    public static String lookAndSay(String s){
        String r = "";
        char[] c = s.toCharArray();
        int run;
        char prev = 0;
        for(int i = run = 0; i < c.length; i++, run++){
            if(i != 0 && c[i] != prev){
                r += Integer.toString(run);
                r += prev;
                run = 0;
            }
            prev = c[i];
        }
        r += Integer.toString(run);
        r += prev;
        return r;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            String s = scan.nextLine();
            for(int i = 0; i < 40; i++)
                s = lookAndSay(s);
            System.out.println(s.length());
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 
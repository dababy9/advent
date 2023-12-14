import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Part2 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            Pattern nice1 = Pattern.compile(".*(\\w\\w).*\\1.*");
            Pattern nice2 = Pattern.compile(".*(\\w).\\1.*");
            int sum = 0;
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                Matcher nice1Match = nice1.matcher(line);
                Matcher nice2Match = nice2.matcher(line);
                if(nice1Match.matches() && nice2Match.matches())
                    sum++;
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
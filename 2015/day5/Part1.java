import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Part1 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            Pattern nice1 = Pattern.compile(".*[aeiou].*[aeiou].*[aeiou].*");
            Pattern nice2 = Pattern.compile(".*(\\w)\\1.*");
            Pattern naughty1 = Pattern.compile(".*(ab).*");
            Pattern naughty2 = Pattern.compile(".*(cd).*");
            Pattern naughty3 = Pattern.compile(".*(pq).*");
            Pattern naughty4 = Pattern.compile(".*(xy).*");
            int sum = 0;
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                Matcher nice1Match = nice1.matcher(line);
                Matcher nice2Match = nice2.matcher(line);
                Matcher naughtyMatch1 = naughty1.matcher(line);
                Matcher naughtyMatch2 = naughty2.matcher(line);
                Matcher naughtyMatch3 = naughty3.matcher(line);
                Matcher naughtyMatch4 = naughty4.matcher(line);
                if(nice1Match.matches() && nice2Match.matches() && !(naughtyMatch1.matches() || naughtyMatch2.matches() || naughtyMatch3.matches() || naughtyMatch4.matches()))
                    sum++;
            }
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
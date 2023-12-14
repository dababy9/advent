import java.io.File;
import java.util.Scanner;
public class Part1 {
    public static void main(String[] args){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 1;
            String[] times = scan.nextLine().split("\\s+");
            String[] dists = scan.nextLine().split("\\s+");
            for(int i = 1; i < times.length; i++){
                int time = Integer.parseInt(times[i]);
                int dist = Integer.parseInt(dists[i]);
                int wins = 0;
                for(int j = 0; j < time; j++)
                    if(j*(time-j) > dist) wins++;
                sum *= wins;
            }
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static int charsToNum(char n1, char n2){
        return (n1-48)*10 + (n2-48);
    }
} 
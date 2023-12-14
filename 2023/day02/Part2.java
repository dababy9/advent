import java.io.File;
import java.util.Scanner;
public class Part2 {
    public static void main(String[] args){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0;
            while(scan.hasNextLine()){
                String s = scan.nextLine().split(" ", 3)[2];
                String[] trials = s.split(";");
                int rMax = 0, gMax = 0, bMax = 0;
                for(int i = 0; i < trials.length; i++){
                    String[] colors = trials[i].split(",");
                    for(int j = 0; j < colors.length; j++){
                        String[] tuple = colors[j].trim().split(" ");
                        int num = Integer.parseInt(tuple[0]);
                        if(tuple[1].equals("red") && num > rMax)
                            rMax = num;
                        if(tuple[1].equals("green") && num > gMax)
                            gMax = num;
                        if(tuple[1].equals("blue") && num > bMax)
                            bMax = num;
                    }
                }
                sum += rMax * gMax * bMax;
            }
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }
} 
import java.io.File;
import java.util.Scanner;
public class Part1 {
    public static void main(String[] args){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0, count = 1;
            while(scan.hasNextLine()){
                String s = scan.nextLine().split(" ", 3)[2];
                String[] trials = s.split(";");
                boolean flag = true;
                for(int i = 0; i < trials.length; i++){
                    String[] colors = trials[i].split(",");
                    for(int j = 0; j < colors.length; j++){
                        String[] tuple = colors[j].trim().split(" ");
                        if(tuple[1].equals("red") && Integer.parseInt(tuple[0]) > 12)
                            flag = false;
                        if(tuple[1].equals("green") && Integer.parseInt(tuple[0]) > 13)
                            flag = false;
                        if(tuple[1].equals("blue") && Integer.parseInt(tuple[0]) > 14)
                            flag = false;
                    }
                }
                if(flag){
                    sum += count;
                }
                count++;
            }
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }
} 
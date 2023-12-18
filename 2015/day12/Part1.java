import java.io.File;
import java.util.Scanner;
public class Part1 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            char[] file = scan.nextLine().toCharArray();
            int sum = 0;
            for(int i = 0; i < file.length; i++)
                if(file[i] >= '0' && file[i] <= '9'){
                    String num = "";
                    if(file[i-1] == '-') num = "-";
                    int j;
                    for(j = i; j < file.length && file[j] >= '0' && file[j] <= '9'; j++) num += file[j];
                    sum += Integer.parseInt(num);
                    i = j-1;
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
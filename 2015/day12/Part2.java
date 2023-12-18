import java.io.File;
import java.util.Scanner;
public class Part2 {

    public static int numSum(char[] c){
        int sum = 0;
        for(int i = 0; i < c.length; i++){
            if(c[i] == '{'){
                int open = 1, endIndex;
                boolean skip = false;
                for(endIndex = i+1; endIndex < c.length && open > 0; endIndex++){
                    if(c[endIndex] == '{') open++;
                    if(c[endIndex] == '}') open--;
                    if(c[endIndex] == 'r' && endIndex+2 < c.length && c[endIndex+1] == 'e' && c[endIndex+2] == 'd' && endIndex >= 2 && c[endIndex-2] == ':' && open == 1) skip = true;
                }
                if(skip) i = endIndex;
            }
            if(c[i] >= '0' && c[i] <= '9'){
                String num = "";
                if(c[i-1] == '-') num = "-";
                int j;
                for(j = i; j < c.length && c[j] >= '0' && c[j] <= '9'; j++) num += c[j];
                sum += Integer.parseInt(num);
                i = j-1;
            }
        }
        return sum;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            char[] c = scan.nextLine().toCharArray();
            System.out.println(numSum(c));
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
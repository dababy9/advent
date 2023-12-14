import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
public class Part2 {

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
            String s = (new Scanner(new File("input.txt"))).nextLine();
            File g = new File("table.txt");
            Scanner scan = new Scanner(new File("table.txt"));
            HashMap<String, Integer> eToS = new HashMap<>();
            HashMap<String, String> eToE = new HashMap<>();
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split("\t");
                if(line[2].equals(s)) s = line[1];
                eToS.put(line[1], line[2].length());
                eToE.put(line[1], line[3]);
            }
            for(int i = 0; i < 50; i++){
                String[] arr;
                if(s.contains(".")) arr = s.split("\\.");
                else arr = new String[]{s};
                s = "";
                for(int j = 0; j < arr.length; j++){
                    s += eToE.get(arr[j]);
                    if(j != arr.length - 1) s += ".";
                }
            }
            String[] arr = s.split("\\.");
            long sum = 0;
            for(int i = 0; i < arr.length; i++)
                sum += eToS.get(arr[i]);
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
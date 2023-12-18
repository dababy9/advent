import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
public class Part2 {

    HashMap<String, String> reduces = new HashMap<>();

    public String replace(String s, String key){
        int rIndex = s.lastIndexOf(key);
        return s.substring(0, rIndex) + reduces.get(key) + s.substring(rIndex + key.length());
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            LinkedList<Character> stack = new LinkedList<>();
            for(String line = scan.nextLine(); line != ""; line = scan.nextLine()){
                String[] s = line.split(" ");
                reduces.put(s[2], s[0]);
            }
            String seq = scan.nextLine();
            int total = 0;
            while(!seq.equals("e")){
                for(String key : reduces.keySet()){
                    if(seq.contains(key)){
                        seq = replace(seq, key);
                        total++;
                    }
                }
            }
            System.out.println(total);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
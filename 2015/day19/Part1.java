import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
public class Part1 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            HashMap<String, LinkedList<String>> map = new HashMap<>();
            for(String line = scan.nextLine(); line != ""; line = scan.nextLine()){
                String[] s = line.split(" ");
                LinkedList<String> list = map.get(s[0]);
                if(list == null)
                    list = new LinkedList<>();
                list.add(s[2]);
                map.put(s[0], list);
            }
            String seq = scan.nextLine();
            HashSet<String> set = new HashSet<>();
            for(int i = 0; i < seq.length(); i++){
                LinkedList<String> list = map.get(seq.substring(i, i+1));
                if(list != null){
                    for(String replace : list){
                        String newSeq = seq.substring(0, i);
                        newSeq += replace;
                        newSeq += seq.substring(i+1);
                        set.add(newSeq);
                    }
                } else if(i+1 < seq.length()){
                    list = map.get(seq.substring(i, i+2));
                    if(list != null){
                        for(String replace : list){
                            String newSeq = seq.substring(0, i);
                            newSeq += replace;
                            newSeq += seq.substring(i+2);
                            set.add(newSeq);
                        }
                        i++;
                    }
                }
            }
            System.out.println(set.size());
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 
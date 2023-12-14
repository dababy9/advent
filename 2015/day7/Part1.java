import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part1 {

    public class Wire {
        public int value;
        public String w1, w2;
        public char op;
        public static TreeMap<String, Wire> map = new TreeMap<>();

        public Wire(String[] rule){
            switch(rule.length){
                case 3: {
                    try { value = Integer.parseInt(rule[0]); op = 'v'; }
                    catch(Exception e){ w1 = rule[0]; op = 'V'; }
                }; break;
                case 4: w1 = rule[1]; op = 'n'; break;
                case 5: {
                    w1 = rule[0];
                    if(rule[1].equals("AND") || rule[1].equals("OR")){
                        w2 = rule[2];
                        op = (rule[1].equals("AND") ? 'a' : 'o');
                    } else {
                        value = Integer.parseInt(rule[2]);
                        op = (rule[1].equals("LSHIFT") ? 'l' : 'r');
                    }
                }; break;
            }
        }

        public int getValue(){
            System.out.println("RETRIEVING VALUE");
            if(op != 'v') System.out.println("GETTING " + w1);
            if(op == 'a' || op == 'o') System.out.println("ALSO GETTING " + w2);
            switch(op){
                case 'v': return value;
                case 'V': return map.get(w1).getValue();
                case 'n': return (~map.get(w1).getValue());
                case 'a': return (map.get(w1).getValue() & map.get(w2).getValue());
                case 'o': return (map.get(w1).getValue() | map.get(w2).getValue());
                case 'l': return (map.get(w1).getValue() << value);
                case 'r': return (map.get(w1).getValue() >> value);
            }
            return 0;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            System.out.println(scan.hasNextLine());
            while(scan.hasNextLine()){
                
                String[] line = scan.nextLine().split(" ");
                Wire.map.put(line[line.length-1], new Wire(line));
                System.out.println("CREATED '" + line[line.length-1] + "'");
            }
            System.out.println("DONE");
            Wire w = Wire.map.get("a");
            System.out.println(w.value);
            System.out.println("DONE2");
            System.out.println((Wire.map.get("a").getValue() & 0x0000FFFF));
        } catch(Exception e){
            System.out.println("File does not exist.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 
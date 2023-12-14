import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part2 {

    public class Wire {
        public int value;
        public String name, w1, w2;
        public char op;
        public static TreeMap<String, Wire> map = new TreeMap<>();
        public static TreeMap<String, Integer> memo = new TreeMap<>();

        public Wire(String[] rule){
            name = rule[rule.length-1];
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
            int rValue = 0;
            try { rValue = memo.get(name); }
            catch(Exception e){
                switch(op){
                    case 'v': rValue = value; break;
                    case 'V': rValue = map.get(w1).getValue(); break;
                    case 'n': rValue = (~map.get(w1).getValue()); break;
                    case 'a': rValue = (map.get(w1).getValue() & map.get(w2).getValue()); break;
                    case 'o': rValue = (map.get(w1).getValue() | map.get(w2).getValue()); break;
                    case 'l': rValue = (map.get(w1).getValue() << value); break;
                    case 'r': rValue = (map.get(w1).getValue() >> value); break;
                }
                memo.put(name, rValue);
            }
            return rValue;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(" ");
                Wire.map.put(line[line.length-1], new Wire(line));
            }
            Wire.map.put("1", new Wire(new String[]{"1", "->", "1"}));
            Wire.map.put("b", new Wire(new String[]{Integer.toString(Wire.map.get("a").getValue() & 0x0000FFFF), "->", "b"}));
            Wire.memo = new TreeMap<>();
            System.out.println(Wire.map.get("a").getValue() & 0x0000FFFF);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
}
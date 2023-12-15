import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
public class Part2 {

    public class Tuple {
        public String label;
        public int focalLength;

        public Tuple(String l, int f){
            label = l;
            focalLength = f;
        }

        @Override
        public boolean equals(Object o){
            if(o == null || getClass() != o.getClass()) return false;
            Tuple t = (Tuple)o;
            return label.equals(t.label);
        }
    }

    public static int hash(String s){
        int sum = 0;
        char[] c = s.toCharArray();
        for(int j = 0; j < c.length; j++)
            sum = ((sum + c[j]) * 17) % 256;
        return sum;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            String[] s = scan.nextLine().split(",");
            HashMap<Integer, LinkedList<Tuple>> map = new HashMap<>();
            for(int i = 0; i < 256; i++)
                map.put(i, new LinkedList<Tuple>());
            for(int i = 0; i < s.length; i++){
                String curr = s[i];
                if(curr.charAt(curr.length()-1) == '-'){
                    String label = curr.substring(0, curr.length()-1);
                    map.get(hash(label)).remove(new Tuple(label, 0));
                } else {
                    String label = curr.substring(0, curr.length()-2);
                    LinkedList<Tuple> list = map.get(hash(label));
                    Tuple t = new Tuple(label, curr.charAt(curr.length()-1)-48);
                    if(list.contains(t)) list.set(list.indexOf(t), t);
                    else list.add(t);
                }
            }
            long total = 0;
            for(int i : map.keySet()){
                LinkedList<Tuple> list = map.get(i);
                if(list.size() != 0)
                    for(int j = 0; j < list.size(); j++)
                        total += (i+1)*(j+1)*(list.get(j).focalLength);
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
import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
public class Part2 {

    HashMap<Couple, Integer> map = new HashMap<>();
    String[] names;

    public class Couple {
        public String n1, n2;

        public Couple(String s1, String s2){
            if(s1.compareTo(s2) < 0){
                n1 = s1;
                n2 = s2;
            } else {
                n1 = s2;
                n2 = s1;
            }
        }

        @Override
        public boolean equals(Object o){
            if(o == null || getClass() != o.getClass()) return false;
            Couple c = (Couple)o;
            return n1.equals(c.n1) && n2.equals(c.n2);
        }

        @Override
        public int hashCode(){
            String hash = n1 + n2;
            return hash.hashCode();
        }
    }

    public int maxHappiness(String[] arr, int workingIndex){
        int usedMask = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == null) break;
            for(int j = 0; j < names.length; j++)
                if(arr[i].equals(names[j])) usedMask = usedMask ^ (1 << j);
        }
        if(workingIndex == arr.length-1){
            String last = "";
            for(int i = 0; i < names.length; i++)
                if((usedMask & (1 << i)) == 0) last = names[i];
            arr[workingIndex] = last;
            int totalHappiness = 0;
            for(int i = 0; i < arr.length-1; i++)
                totalHappiness += map.get(new Couple(arr[i], arr[i+1]));
            return totalHappiness + map.get(new Couple(arr[0], arr[arr.length-1]));
        } else {
            int maxValue = Integer.MIN_VALUE;
            for(int i = 0; i < names.length; i++)
                if((usedMask & (1 << i)) == 0){
                    String[] newArr = new String[arr.length];
                    System.arraycopy(arr, 0, newArr, 0, arr.length);
                    newArr[workingIndex] = names[i];
                    int happiness = maxHappiness(newArr, workingIndex + 1);
                    if(happiness > maxValue) maxValue = happiness;
                }
            return maxValue;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            HashSet<String> namesSet = new HashSet<>();
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(" ");
                namesSet.add(line[0]);
                int happiness = Integer.parseInt(line[3]);
                if(line[2].equals("lose")) happiness *= -1;
                Couple c = new Couple(line[0], line[10].substring(0, line[10].length()-1));
                if(map.containsKey(c))
                    map.put(c, map.get(c) + happiness);
                else
                    map.put(c, happiness);
            }
            for(String s : namesSet)
                map.put(new Couple(s, "me"), 0);
            namesSet.add("me");
            names = new String[namesSet.size()];
            int i = 0;
            for(String s : namesSet)
                names[i++] = s;
            String[] arrangement = new String[names.length];
            arrangement[0] = names[0];
            System.out.println(maxHappiness(arrangement, 1));
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
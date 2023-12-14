import java.io.File;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Objects;
public class Part2 {

    char[] s;
    int[] groups, substringMask;
    HashMap<Tuple, Long> map;

    public class Tuple {
        int a, b;

        public Tuple(int x, int y){
            a = x;
            b = y;
        }

        public boolean equals(Object o){
            if(o == null || getClass() != o.getClass()) return false;
            Tuple t = (Tuple)o;
            return a == t.a && b == t.b;
        }

        public int hashCode(){
            return Objects.hash(a, b);
        }
    }

    public long count(int gIndex, int sIndex){
        long rValue = 1;
        try { return map.get(new Tuple(gIndex, sIndex)); } catch(Exception e){
            if(gIndex == groups.length * 5){
                for(int i = sIndex; i < s.length; i++)
                    if(s[i] == '#'){ rValue = 0; break; }
                map.put(new Tuple(gIndex, sIndex), rValue);
            } else {
                int lengthLeft = 0;
                for(int i = gIndex; i < groups.length * 5; i++) lengthLeft += groups[i % groups.length] + 1;
                if(lengthLeft >= s.length - sIndex){
                    map.put(new Tuple(gIndex, sIndex), (long)0);
                    return 0;
                }
                rValue = 0;
                int operationalMask = 0, damagedMask = 0;
                for(int i = 0; i < groups[gIndex % groups.length] + 2; i++){
                    operationalMask <<= 1;
                    damagedMask <<= 1;
                    if(s[i + sIndex] == '.') operationalMask++;
                    if(s[i + sIndex] == '#') damagedMask++;
                }
                int currMask = substringMask[gIndex % groups.length];
                int futureDamagedMask = damagedMask << 1;
                if(futureDamagedMask % 2 == 1) futureDamagedMask--;
                if((currMask & operationalMask) == 0 && (currMask | damagedMask) == currMask){
                    long n = count(gIndex + 1, sIndex + groups[gIndex % groups.length] + 1);
                    if(n == 0 && (futureDamagedMask | currMask) != currMask){
                        map.put(new Tuple(gIndex, sIndex), (long)0);
                        return 0;
                    }
                    rValue += n;
                }
                if((futureDamagedMask | currMask) == currMask)
                    rValue += count(gIndex, sIndex + 1);
                map.put(new Tuple(gIndex, sIndex), rValue);
            }
        }
        return rValue;
    }

    public long num(String line){
        map = new HashMap<>();
        String repeat = line.split(" ")[0];
        String total = repeat + "?";
        for(int i = 0; i < 4; i++) total += repeat + "?";
        char[] rawS = total.toCharArray();
        s = new char[rawS.length+1];
        for(int i = 0; i < rawS.length; i++) s[i+1] = rawS[i];
        s[0] = '?';

        String[] stringGroups = line.split(" ")[1].split(",");
        groups = new int[stringGroups.length];
        substringMask = new int[groups.length];
        for(int i = 0; i < stringGroups.length; i++){
            int n = Integer.parseInt(stringGroups[i]);
            int sMask = 0;
            for(int j = 0; j < n; j++) sMask = (++sMask << 1);
            groups[i] = n;
            substringMask[i] = sMask;
        }

        return count(0, 0);
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            long sum = 0;
            while(scan.hasNextLine())
                sum += num(scan.nextLine());
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
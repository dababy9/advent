import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Part1 {

    public int calculate(int[] map){
        for(int i = 1; i < map.length; i++){
            boolean mirrored = true;
            for(int j = 0; j+i < map.length && i-j > 0; j++)
                if((map[i+j]^map[i-j-1]) != 0){ mirrored = false; break; }
            if(mirrored) return i;
        }
        return 0;
    }

    public int[] transform(int[] oldMap, int l){
        int[] map = new int[l];
        for(int i = map.length-1; i >= 0; i--){
            int num = 0;
            for(int k = oldMap.length-1; k >= 0; k--){
                num = (num + oldMap[k] % 2) << 1;
                oldMap[k] >>= 1;
            }
            map[i] = num >> 1;
        }
        return map;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0;
            while(scan.hasNextLine()){
                ArrayList<char[]> charMap = new ArrayList<>();
                for(String s = scan.nextLine(); true; s = scan.nextLine()){
                    if(s == "") break;
                    charMap.add(s.toCharArray());
                    if(!scan.hasNextLine()) break;
                }
                int[] map = new int[charMap.size()];
                int i = 0, l = charMap.get(0).length;
                for(char[] c : charMap){
                    int num = 0;
                    for(int k = 0; k < c.length; k++)
                        num = (c[k] == '#' ? num+1 : num) << 1;
                    map[i++] = num >>= 1;
                }
                int result = calculate(map) * 100;
                if(result == 0){
                    map = transform(map, l);
                    result = calculate(map);
                }
                sum += result;
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
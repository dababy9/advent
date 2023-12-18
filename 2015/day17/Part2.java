import java.io.File;
import java.util.Scanner;
import java.util.LinkedList;
public class Part2 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            LinkedList<Integer> list = new LinkedList<>();
            while(scan.hasNextLine())
                list.add(Integer.parseInt(scan.nextLine()));
            int index = 0;
            int[] containers = new int[list.size()];
            for(int i : list)
                containers[index++] = i;
            int maxCombo = (1 << containers.length) - 1;
            int target = 150;
            list = new LinkedList<>();
            for(int i = 1; i < maxCombo; i++){
                int contained = 0, used = 0;
                for(int j = 0; j < containers.length; j++)
                    if((i >> j) % 2 == 1){
                        contained += containers[j];
                        used++;
                    }
                if(contained == target) list.add(used++);
            }
            int minValue = Integer.MAX_VALUE;
            for(int i : list)
                if(i < minValue) minValue = i;
            int total = 0;
            for(int i : list)
                if(i == minValue) total++;
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
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Part1 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            ArrayList<Integer> list = new ArrayList<>();
            while(scan.hasNextLine())
                list.add(Integer.parseInt(scan.nextLine()));
            int index = 0;
            int[] containers = new int[list.size()];
            for(int i : list)
                containers[index++] = i;
            int maxCombo = (1 << containers.length) - 1;
            int total = 0, target = 150;
            for(int i = 1; i < maxCombo; i++){
                int contained = 0;
                for(int j = 0; j < containers.length; j++)
                    if((i >> j) % 2 == 1) contained += containers[j];
                if(contained == target) total++;
            }
            System.out.println(total);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 
import java.io.File;
import java.util.Scanner;
public class Part2 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int target = Integer.parseInt(scan.nextLine());
            int maxHouse = 1000000;
            int[] houses = new int[maxHouse];
            for(int elf = 1; elf < maxHouse; elf++)
                for(int currHouse = elf; currHouse < maxHouse && currHouse <= elf*50; currHouse += elf)
                    houses[currHouse] += elf;
            for(int i = 0; i < maxHouse; i++)
                if(houses[i]*11 >= target){
                    System.out.println(i);
                    break;
                }
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
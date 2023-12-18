import java.io.File;
import java.util.Scanner;
public class Part1 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int maxDistance = 0;
            int raceTime = 2503;
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(" ");
                int rate = Integer.parseInt(line[3]);
                int flyTime = Integer.parseInt(line[6]);
                int restTime = Integer.parseInt(line[13]);
                int period = flyTime + restTime;
                int periodDist = rate * flyTime;
                int distance = 0, sec;
                for(sec = 0; sec + period < raceTime; sec += period)
                    distance += periodDist;
                for(int lastSec = 0; lastSec < flyTime && sec < raceTime; lastSec++, sec++)
                    distance += rate;
                if(distance > maxDistance) maxDistance = distance;
            }
            System.out.println(maxDistance);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 
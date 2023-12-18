import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Part2 {

    public class Reindeer {
        int rate, flyTime, restTime;
        boolean flying = true;
        int secLeft, totalDistance = 0, points = 0;

        public Reindeer(int r, int ft, int rt){
            rate = r;
            flyTime = secLeft = ft;
            restTime = rt;
        }

        public void move(){
            if(flying) totalDistance += rate;
            if(--secLeft == 0){
                flying = !flying;
                secLeft = (flying ? flyTime : restTime);
            }
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int raceTime = 2503;
            ArrayList<Reindeer> racers = new ArrayList<>();
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(" ");
                int rate = Integer.parseInt(line[3]);
                int flyTime = Integer.parseInt(line[6]);
                int restTime = Integer.parseInt(line[13]);
                racers.add(new Reindeer(rate, flyTime, restTime));
            }
            for(int i = 0; i < raceTime; i++){
                int furthestDist = 0;
                for(Reindeer r : racers){
                    r.move();
                    if(r.totalDistance > furthestDist)
                        furthestDist = r.totalDistance;
                }
                for(Reindeer r : racers)
                    if(r.totalDistance == furthestDist) r.points++;
            }
            int maxPoints = 0;
            for(Reindeer r : racers)
                if(r.points > maxPoints) maxPoints = r.points;
            System.out.println(maxPoints);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
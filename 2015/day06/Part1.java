import java.io.File;
import java.util.Scanner;
public class Part1 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            boolean[][] map = new boolean[1000][1000];
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                char op = 0;
                int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
                if(line.split(" ")[0].equals("turn")){
                    String[] pair1 = line.split(" ")[2].split(",");
                    String[] pair2 = line.split(" ")[4].split(",");
                    x1 = Integer.parseInt(pair1[0]);
                    x2 = Integer.parseInt(pair2[0]);
                    y1 = Integer.parseInt(pair1[1]);
                    y2 = Integer.parseInt(pair2[1]);
                    if(line.split(" ")[1].equals("on")){
                        op = '1';
                    } else {
                        op = '0';
                    }
                } else {
                    op = 'X';
                    String[] pair1 = line.split(" ")[1].split(",");
                    String[] pair2 = line.split(" ")[3].split(",");
                    x1 = Integer.parseInt(pair1[0]);
                    x2 = Integer.parseInt(pair2[0]);
                    y1 = Integer.parseInt(pair1[1]);
                    y2 = Integer.parseInt(pair2[1]);
                }
                for(int i = x1; i <= x2; i++)
                    for(int j = y1; j <= y2; j++){
                        switch(op){
                            case '1': map[i][j] = true; break;
                            case '0': map[i][j] = false; break;
                            case 'X': map[i][j] = !map[i][j]; break;
                        }
                    }
            }
            int sum = 0;
            for(int i = 0; i < 1000; i++)
                for(int j = 0; j < 1000; j++)
                    if(map[i][j]) sum++;
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
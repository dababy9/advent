import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part1 {

    public class Player {
        public int hp, dmg, armor;

        public Player(int h, int d, int a){
            hp = h;
            dmg = d;
            armor = a;
        }

        public Player(Player p){
            hp = p.hp;
            dmg = p.dmg;
            armor = p.armor;
        }
    }

    public boolean fight(Player p, Player b){
        while(true){
            b.hp -= (p.dmg - b.armor <= 0 ? 1 : p.dmg - b.armor);
            if(b.hp <= 0) return true;
            p.hp -= (b.dmg - p.armor <= 0 ? 1 : b.dmg - p.armor);
            if(p.hp <= 0) return false;
        }
    }

    public int ringDmg(int r1, int r2){
        int result = 0;
        if(r1 <= 3) result += r1;
        if(r2 <= 3) result += r2;
        return result;
    }

    public int ringArmor(int r1, int r2){
        int result = 0;
        if(r1 > 3) result += r1-3;
        if(r2 > 3) result += r2-3;
        return result;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            Player boss = new Player(Integer.parseInt(scan.nextLine().split(" ")[2]), Integer.parseInt(scan.nextLine().split(" ")[1]), Integer.parseInt(scan.nextLine().split(" ")[1]));
            Player p = new Player(100, 0, 0);
            int[] wCosts = new int[]{8, 10, 25, 40, 74};
            int[] aCosts = new int[]{0, 13, 31, 53, 75, 102};
            int[] rCosts = new int[]{0, 25, 50, 100, 20, 40, 80};
            int minCost = Integer.MAX_VALUE;
            for(int w = 0; w < wCosts.length; w++)
                for(int a = 0; a < aCosts.length; a++)
                    for(int r1 = 0; r1 < rCosts.length-1; r1++)
                        for(int r2 = (r1 == 0 ? 0 : r1+1); r2 < rCosts.length; r2++){
                            int cost = wCosts[w] + aCosts[a] + rCosts[r1] + rCosts[r2];
                            if(cost > minCost) continue;
                            int dmg = w+4 + ringDmg(r1, r2);
                            int armor = a + ringArmor(r1, r2);
                            if(fight(new Player(100, dmg, armor), new Player(boss)) && cost < minCost) minCost = cost;
                        }
            System.out.println(minCost);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 
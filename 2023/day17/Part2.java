import java.io.File;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashMap;
public class Part2 {

    int[][] grid;
    HashMap<Tuple, Integer> posCosts = new HashMap<>();

    public class Tuple {
        Point pos;
        int steps;
        char dir;

        public Tuple(Point p, int s, char d){
            pos = p;
            steps = s;
            dir = d;
        }

        @Override
        public boolean equals(Object o){
            if(o == null || getClass() != o.getClass()) return false;
            Tuple t = (Tuple)o;
            return steps == t.steps && pos.equals(t.pos);
        }

        @Override
        public int hashCode(){
            return pos.hashCode() + steps + dir;
        }
    }

    public class Point {
        int row, col;

        public Point(int r, int c){
            row = r;
            col = c;
        }

        public Point move(char dir){
            Point p = new Point(row, col);
            switch(dir){
                case 'N': p.row--; break;
                case 'S': p.row++; break;
                case 'W': p.col--; break;
                case 'E': p.col++; break;
            }
            return p;
        }

        @Override
        public boolean equals(Object o){
            if(o == null || getClass() != o.getClass()) return false;
            Point p = (Point)o;
            return row == p.row && col == p.col;
        }

        @Override
        public int hashCode(){
            int hash = 7;
            hash = 71 * hash + row;
            hash = 71 * hash + col;
            return hash;
        }
    }

    public class Trail {
        Point pos;
        char dir;
        int totalCost, stepsInDir;

        public Trail(Point p, char d, int t, int s){
            pos = new Point(p.row, p.col);
            dir = d;
            totalCost = t;
            stepsInDir = s;
        }

        public Trail turnTrail(char turnDir){
            char nextDir = turn(dir, turnDir);
            int accumCost = 0;
            Point nextPos = pos;
            try{
                for(int i = 0; i < 4; i++){
                    nextPos = nextPos.move(nextDir);
                    accumCost += grid[nextPos.row][nextPos.col];
                }
                return new Trail(nextPos, nextDir, totalCost + accumCost, 4);
            } catch(Exception e){ return null; }
        }

        public LinkedList<Trail> offspring(){
            LinkedList<Trail> list = new LinkedList<>();
            Trail child = turnTrail('L');
            if(child != null)
                list.add(child);
            child = turnTrail('R');
            if(child != null)
                list.add(child);
            if(stepsInDir != 10){
                Point nextPos = pos.move(dir);
                try { list.add(new Trail(nextPos, dir, totalCost + grid[nextPos.row][nextPos.col], stepsInDir + 1)); } catch(Exception e){}
            }
            return list;
        }

        public static char turn(char dir, char turnDir){
            if(turnDir == 'L'){
                switch(dir){
                    case 'N': return 'W';
                    case 'S': return 'E';
                    case 'W': return 'S';
                    case 'E': return 'N';
                }
            } else {
                switch(dir){
                    case 'N': return 'E';
                    case 'S': return 'W';
                    case 'W': return 'N';
                    case 'E': return 'S';
                }
            }
            return 0;
        }

        public String toString(){
            return "At: (row: " + pos.row + ", col: " + pos.col + "), cost: " + totalCost;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            LinkedList<int[]> oldGrid = new LinkedList<>();
            while(scan.hasNextLine()){
                char[] line = scan.nextLine().toCharArray();
                int[] row = new int[line.length];
                for(int i = 0; i < line.length; i++)
                    row[i] = line[i] - '0';
                oldGrid.add(row);
            }
            grid = new int[oldGrid.size()][oldGrid.get(0).length];
            int index = 0;
            for(int[] row : oldGrid)
                grid[index++] = row;
            Point end = new Point(grid.length-1, grid[0].length-1);
            LinkedList<Trail> trails = new LinkedList<>();
            int sCost = 0, eCost = 0;
            for(int i = 1; i <= 4; i++){
                sCost += grid[i][0];
                eCost += grid[0][i];
            }
            trails.add(new Trail(new Point(4, 0), 'S', sCost, 4));
            trails.add(new Trail(new Point(0, 4), 'E', eCost, 4));
            int minDist = Integer.MAX_VALUE;
            while(!trails.isEmpty()){
                LinkedList<Trail> next = new LinkedList<>();
                for(Trail t : trails){
                    if(t.pos.equals(end))
                        if(t.totalCost < minDist) minDist = t.totalCost;
                    for(Trail child : t.offspring()){
                        try {
                            int dist = posCosts.get(new Tuple(child.pos, child.stepsInDir, child.dir));
                            if(child.totalCost < dist){
                                for(int i = 0; child.stepsInDir + i < 11; i++)
                                    if(child.totalCost < posCosts.get(new Tuple(child.pos, child.stepsInDir + i, child.dir)))
                                        posCosts.put(new Tuple(child.pos, child.stepsInDir + i, child.dir), child.totalCost);
                                next.add(child);
                            }
                        } catch(Exception e){
                            posCosts.put(new Tuple(child.pos, child.stepsInDir, child.dir), child.totalCost);
                            for(int i = 1; child.stepsInDir + i < 11; i++){
                                try {
                                    int dist = posCosts.get(new Tuple(child.pos, child.stepsInDir + i, child.dir));
                                    if(child.totalCost < dist)
                                        posCosts.put(new Tuple(child.pos, child.stepsInDir + i, child.dir), child.totalCost);
                                } catch(Exception ee){
                                    posCosts.put(new Tuple(child.pos, child.stepsInDir + i, child.dir), child.totalCost);
                                }
                            }
                            next.add(child);
                        }
                    }
                }
                trails = new LinkedList<>();
                for(Trail t : next)
                    if(t.totalCost == posCosts.get(new Tuple(t.pos, t.stepsInDir, t.dir))) trails.add(t);
            }
            System.out.println(minDist);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashSet;
public class Part2 {

    public int nodes = 0;
    public HashMap<Tuple2, Integer> memo = new HashMap<>();
    public HashMap<String, LinkedList<Tuple>> graph = new HashMap<>();

    public class Tuple {
        String city;
        int dist;

        public Tuple(String c, int d){
            city = c;
            dist = d;
        }
    }

    public class Tuple2 {
        String s;
        HashSet<String> v;

        @SuppressWarnings("unchecked")
        public Tuple2(String name, HashSet<String> visited){
            s = name;
            v = (HashSet)visited.clone();
        }

        @Override
        public boolean equals(Object o){
            if(o == null || getClass() != o.getClass()) return false;
            Tuple2 t = (Tuple2)o;
            return s.equals(t.s) && v.equals(t.v);
        }

        @Override
        public int hashCode(){
            return s.hashCode() + 10*v.size();
        }
    }

    public int pathFind(String start, HashSet<String> visited){
        int maxDist = Integer.MIN_VALUE;
        if(visited.size() == nodes) return 0;
        try { return memo.get(new Tuple2(start, visited)); }
        catch(Exception e){
            for(Tuple t : graph.get(start)){
                if(!visited.contains(t.city)){
                    @SuppressWarnings("unchecked")
                    HashSet<String> newVisited = (HashSet)visited.clone();
                    newVisited.add(t.city);
                    int dist = pathFind(t.city, newVisited) + t.dist;
                    if(dist > maxDist) maxDist = dist;
                }
            }
            memo.put(new Tuple2(start, visited), maxDist);
        }
        return maxDist;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(" ");
                if(!graph.containsKey(line[0])) graph.put(line[0], new LinkedList<Tuple>());
                if(!graph.containsKey(line[2])) graph.put(line[2], new LinkedList<Tuple>());
                graph.get(line[0]).add(new Tuple(line[2], Integer.parseInt(line[4])));
                graph.get(line[2]).add(new Tuple(line[0], Integer.parseInt(line[4])));
            }
            nodes = graph.keySet().size();
            int longestDistance = Integer.MIN_VALUE;
            for(String s : graph.keySet()){
                HashSet<String> visited = new HashSet<>();
                visited.add(s);
                int distance = pathFind(s, visited);
                if(distance > longestDistance) longestDistance = distance;
            }
            System.out.println(longestDistance);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part2 {

    public class Node {
        public String name;
        public Node left, right;

        public Node(String n){
            name = n;
        }

        public void setLR(Node l, Node r){
            left = l;
            right = r;
        }
    }

    public Node getNode(String n, TreeMap<String, Node> map){
        Node N = map.get(n);
        if(N == null){
            N = new Node(n);
            map.put(n, N);
        }
        return N;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            TreeMap<String, Node> map = new TreeMap<>();
            HashSet<Node> start = new HashSet<>();
            String directions = scan.nextLine();
            scan.nextLine();
            long sum = 1;
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String n = line.substring(0, 3);
                String l = line.substring(7, 10);
                String r = line.substring(12, 15);
                Node N = getNode(n, map);
                Node L = getNode(l, map);
                Node R = getNode(r, map);
                N.setLR(L, R);
                if(n.charAt(2) == 'A') start.add(N);
            }
            for(Node n : start){
                Node curr = n;
                int steps;
                boolean found = false;
                for(steps = 0; !found; steps++){
                    curr = (directions.charAt(steps % directions.length()) == 'L' ? curr.left : curr.right);
                    found = curr.name.charAt(2) == 'Z';
                }
                sum *= (steps/directions.length());
            }
            System.out.println(sum*directions.length());
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 
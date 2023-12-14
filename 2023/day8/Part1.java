import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part1 {

    public class Node {
        public Node left, right;

        public void setLR(Node l, Node r){
            left = l;
            right = r;
        }
    }

    public Node getNode(String n, TreeMap<String, Node> map){
        Node N = map.get(n);
        if(N == null){
            N = new Node();
            map.put(n, N);
        }
        return N;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            TreeMap<String, Node> map = new TreeMap<>();
            Node start = null, end = null;
            String directions = scan.nextLine();
            scan.nextLine();
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String n = line.substring(0, 3);
                String l = line.substring(7, 10);
                String r = line.substring(12, 15);
                Node N = getNode(n, map);
                Node L = getNode(l, map);
                Node R = getNode(r, map);
                N.setLR(L, R);
                if(n.equals("AAA")) start = N;
                if(n.equals("ZZZ")) end = N;
            }
            boolean found = false;
            int steps;
            for(steps = 0; !found; steps++){
                start = (directions.charAt(steps % directions.length()) == 'L' ? start.left : start.right);
                if(start == end) found = true;
            }
            System.out.println(steps);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 
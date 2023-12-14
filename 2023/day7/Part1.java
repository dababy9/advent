import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part1 {

    public class Hand implements Comparable<Hand> {
        public String cards;
        public int type;

        public Hand(String s){
            cards = s;
            HashSet<Character> chars = new HashSet<>();
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < s.length(); i++){
                int matches = 1;
                if(!chars.contains(s.charAt(i))){
                    chars.add(s.charAt(i));
                    for(int j = i+1; j < s.length(); j++){
                        if(s.charAt(i) == s.charAt(j)) matches++;
                    }
                    list.add(matches);
                }
            }
            if(list.size() == 1) type = 7;
            if(list.size() == 2){
                if(list.contains(4)) type = 6;
                if(list.contains(3)) type = 5;   
            }
            if(list.size() == 3){
                if(list.contains(3)) type = 4;
                if(list.contains(2)) type = 3;
            }
            if(list.size() == 4) type = 2;
            if(list.size() == 5) type = 1;
        }

        public int compareTo(Hand h){
            if(h.type == type){
                for(int i = 0; i < 5; i++)
                    if(cards.charAt(i) != h.cards.charAt(i))
                        return valueOf(cards.charAt(i)) - valueOf(h.cards.charAt(i));
            }
            return type - h.type;
        }

        public static int valueOf(char c){
            switch(c){
                case '2': return 2;
                case '3': return 3;
                case '4': return 4;
                case '5': return 5;
                case '6': return 6;
                case '7': return 7;
                case '8': return 8;
                case '9': return 9;
                case 'T': return 10;
                case 'J': return 11;
                case 'Q': return 12;
                case 'K': return 13;
                case 'A': return 14;
            }
            return 0;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            TreeMap<Hand, Integer> hands = new TreeMap<>();
            while(scan.hasNextLine()){
                String[] s = scan.nextLine().split(" ");
                hands.put(new Hand(s[0]), Integer.parseInt(s[1]));
            }
            int sum = 0;
            int count = 1;
            for(Hand h : hands.keySet()){
                sum += hands.get(h) * count++;
            }
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
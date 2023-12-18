import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
public class Part2 {

    public class Sue {
        int number;
        HashMap<String, Integer> attributes = new HashMap<>();

        public Sue(String[] line){
            number = Integer.parseInt(line[1].substring(0, line[1].length()-1));
            for(int i = 2; i < line.length; i += 2){
                try {
                    attributes.put(line[i], Integer.parseInt(line[i+1]));
                } catch(Exception e){
                    attributes.put(line[i], Integer.parseInt(line[i+1].substring(0, line[i+1].length()-1)));
                }
            }
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);

            HashMap<String, Integer> MFCSAM = new HashMap<>();
            MFCSAM.put("children:", 3);
            MFCSAM.put("cats:", 7);
            MFCSAM.put("samoyeds:", 2);
            MFCSAM.put("pomeranians:", 3);
            MFCSAM.put("akitas:", 0);
            MFCSAM.put("vizslas:", 0);
            MFCSAM.put("goldfish:", 5);
            MFCSAM.put("trees:", 3);
            MFCSAM.put("cars:", 2);
            MFCSAM.put("perfumes:", 1);

            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(" ");
                Sue s = new Sue(line);
                boolean match = true;
                for(String attr : s.attributes.keySet())
                    if(attr.equals("cats:") || attr.equals("trees:")){
                        if(s.attributes.get(attr) <= MFCSAM.get(attr)) match = false;
                    } else if(attr.equals("pomeranians:") || attr.equals("goldfish:")){
                        if(s.attributes.get(attr) >= MFCSAM.get(attr)) match = false;
                    } else {
                        if(s.attributes.get(attr) != MFCSAM.get(attr)) match = false;
                    }
                if(match){
                    System.out.println(s.number);
                    break;
                }
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
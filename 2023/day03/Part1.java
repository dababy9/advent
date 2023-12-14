import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Part1 {

    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<>();
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine())
                list.add(scan.nextLine());
        } catch(Exception e){
            System.out.println("File does not exist.");
        }

        int sum = 0;
        Pattern pattern = Pattern.compile("[^.]");
        for(int row = 0; row < list.size(); row++){
            String s = list.get(row);
            for(int col = 0; col < s.length(); col++){
                if(s.charAt(col) < 48 || s.charAt(col) > 57)
                    continue;
                int stop;
                for(stop = col+1; stop < s.length() && s.charAt(stop) >= 48 && s.charAt(stop) <= 57; stop++);

                String upper = "", lower = "";
                char left = '.', right = '.';
                if(row != 0) upper = list.get(row-1).substring((col == 0 ? col : col-1), (stop == s.length() ? stop : stop+1));
                if(row != list.size()-1) lower = list.get(row+1).substring((col == 0 ? col : col-1), (stop == s.length() ? stop : stop+1));
                if(col != 0) left = s.charAt(col-1);
                if(stop != s.length()) right = s.charAt(stop);

                if(left != '.' || right != '.' || pattern.matcher(upper).find() || pattern.matcher(lower).find())
                    sum += Integer.parseInt(s.substring(col, stop));
                col = stop;
            }
        }
        System.out.println(sum);
    }
} 
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.*;
public class WordStatInput {
    public static void main(String [] args) throws IOException  {
       BufferedReader reader = new BufferedReader (new InputStreamReader(new FileInputStream(args[0]),"UTF-8"));
       String s = "";
       try {
           String line;
           while ((line = reader.readLine()) != null) {
               s += line + " ";
           }
       } catch (FileNotFoundException | UnsupportedEncodingException | InputMismatchException  e) {
           System.err.println("I feel sorry");
       } finally {
           reader.close();
       }
       Map <String, Integer> mp = new LinkedHashMap<String, Integer>();
       Pattern pt = Pattern.compile("[\\p{L}\\p{Pd}\\']+");
       Matcher m = pt.matcher(s);
       while (m.find()) {
           String st = m.group(0);
           if (st.length() > 0) {
               st = st.toLowerCase();
               if (mp.containsKey(st))
                   mp.put(st, mp.get(st) + 1);
               else
                   mp.put(st, 1);
           }
       }
       PrintWriter out = new PrintWriter (args[1], "UTF-8");

       for (Map.Entry<String, Integer> entry : mp.entrySet()) {
           out.println(entry.getKey() + " " + entry.getValue());
       }
       out.close();
    }
}
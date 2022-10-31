import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class WordStatInput {
    public static boolean is_Symbol_normal(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }
  public static void main(String[] args) throws IOException {
        Map<String, Integer> linked_hash = new LinkedHashMap<>();

        Scanner reader = new Scanner(new InputStreamReader(new FileInputStream(args[0]), "utf-8"));
        String line = reader.nextLine();
        while (line != "") {
            int pos = 0;
            line += " ";
            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);
                if (!(is_Symbol_normal(ch))) {
                    String str1 = line.substring(pos, j).toLowerCase();
					String str = str1.toLowerCase();
                    if (!str.isEmpty()) {
                        if (linked_hash.containsKey(str)) {
                            int kkk = linked_hash.get(str);
                            kkk += 1;
                            linked_hash.replace(str, kkk);
                        } else {
                            linked_hash.put(str, 1);
                        }
                    }
              pos = j + 1;
            
                }
            }
            line = reader.nextLine();
        }
        reader.close();
        
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"));
        
    for (Map.Entry<String, Integer> words : linked_hash.entrySet()) {
            writer.write(words.getKey() + " " + words.getValue());
            writer.newLine();
        }
        writer.close();  
    }
}
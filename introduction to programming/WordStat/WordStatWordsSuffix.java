import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WordStatWordsSuffix {

    public static boolean is_Symbol_normal(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    public static void main(String[] args) throws IOException {
        Map<String, Integer> hash_map = new HashMap<>();
        Scanner input = new Scanner(
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(args[0]), "utf-8")));
        StringBuilder string = new StringBuilder();
        input.useDelimiter("");
        try {
            while (input.hasNext()) {
                String s = input.next();
                int len_s = s.length();
                for (int i = 0; i < len_s; i++) {
                    char symbol = s.charAt(i);
                    int length_string = string.length();
                    if (is_Symbol_normal(symbol)) {
                        string.append(s.charAt(i));
                    } else if (length_string > 0) {
                        String cur = string.toString().toLowerCase();
                        int len_cur = cur.length();
                        if (cur.length() >= 3) {
                            String add_string = cur.substring(len_cur - 3);
                            if (hash_map.containsKey(add_string)) {
                                hash_map.put(add_string, hash_map.get(add_string) + 1);
                            } else {
                                hash_map.put(add_string, 1);
                            }
                            string.setLength(0);
                        } else {
                            if (hash_map.containsKey(cur)) {
                                hash_map.put(cur, hash_map.get(cur) + 1);
                            } else {
                                hash_map.put(cur, 1);
                            }
                            string.setLength(0);
                        }
                    }
                }
            }
        } finally {
            input.close();
        }
        Map<String, Integer> tree_map = new TreeMap<String, Integer>(hash_map);
        Writer output = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(args[1]), "utf-8"));
        for (String word : tree_map.keySet()) {
            output.write(word + " " + tree_map.get(word) + System.lineSeparator());
        }
        output.close();
    }
}
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppCountPosition {

    public static boolean is_Symbol_normal(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    public static void buildFrequencyFile(List<Map.Entry<String, ArrayList<Word>>> list,
                                          String filename) throws IOException {
        Writer writer = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8);

        for (int i = 0; i < list.size(); i++) {

            writer.write(list.get(i).getKey() + " " + list.get(i).getValue().size() + " ");

            for (int j = 0; j < list.get(i).getValue().size(); j++) {
                writer.write(list.get(i).getValue().get(j).getLine() + ":" + list.get(i).getValue().get(j).getWord());
                if (j != list.get(i).getValue().size() - 1) writer.write(' ');
            }

            writer.write('\n');
        }
        writer.close();
    }

    public static void countPosition(String infile, String outfile) throws IOException {
        Scanner reader = new Scanner(new InputStreamReader(new FileInputStream(infile), "utf-8"));
        HashMap<String, ArrayList<Word>> hash = new HashMap<String, ArrayList<Word>>();

        ArrayList<Word> arr = reader.getWords();

        for (int i = 0; i < arr.size(); i++) {
            String word = arr.get(i).getText();

            if (!hash.containsKey(word)) {
                hash.put(word, new ArrayList<Word>());
            }

            hash.get(word).add(arr.get(i));
        }

        reader.close();
        buildFrequencyFile(sortMap(hash), outfile);
    }

    public static List<Map.Entry<String, ArrayList<Word>>> sortMap(HashMap<String, ArrayList<Word>> hash) {
        List list = new ArrayList(hash.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<Word>>>() {
            @Override
            public int compare(Map.Entry<String, ArrayList<Word>> a, Map.Entry<String, ArrayList<Word>> b) {
                if (a.getValue().size() != b.getValue().size())
                    return Integer.compare(a.getValue().size(), b.getValue().size());

                return Integer.compare(a.getValue().get(0).getPos(), b.getValue().get(0).getPos());
            }
        });
        return list;
    }

    public static void main(String[] args) throws IOException {
        countPosition(args[0], args[1]);
    }
}

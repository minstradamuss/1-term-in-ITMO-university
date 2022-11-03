import java.io.*;
import java.lang.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Scanner;

public class Wspp {

	public static boolean is_Symbol_normal(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

	public static void buildFrequencyFile(LinkedHashMap<String, ArrayList<Integer>> hash, String filename) throws IOException {
		Writer  writer = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8);

		for (Map.Entry<String, ArrayList<Integer>> entry : hash.entrySet()) {
			writer.write(entry.getKey() + " " + entry.getValue().size() + " ");

			for (int j = 0; j < entry.getValue().size(); j++) {
				writer.write(Integer.toString( entry.getValue().get(j) ));
				if (j != entry.getValue().size() - 1) writer.write(' ');
			}

			writer.write('\n');
		}
		writer.close();
	}

	public static void wordCounter(String infile, String outfile) throws IOException {
		Scanner reader = new Scanner(new InputStreamReader(new FileInputStream(infile), "utf-8"));
		String line;
		int offset = 0;

		LinkedHashMap<String, ArrayList<Integer>> hash = new LinkedHashMap<String, ArrayList<Integer>>();

		int wordCounter = 0;
		while (reader.hasNextLine()) {
			line = reader.nextLine();
			line = line.concat(" ");

			int start = -1;
			for (int i = 0; i < line.length(); i++) {

				if (is_Symbol_normal(line.charAt(i))) {
					if (start == -1) {
						start = i;
					}
				} else {
					if (start != -1) {
						wordCounter++;
						var str = line.substring(start, i).toLowerCase();

						if (!hash.containsKey(str)) {
							hash.put(str, new ArrayList<Integer>());
						}
						hash.get(str).add(wordCounter);

						start = -1;
					}
				}
			}

			offset += line.length() - 1;
		}

		reader.close();
		buildFrequencyFile(hash, outfile);
	}

	public static void main(String[] args) throws IOException {
		wordCounter(args[0], args[1]);
	}
}
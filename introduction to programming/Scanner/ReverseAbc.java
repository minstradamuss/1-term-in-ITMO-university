import java.util.*;
import java.io.*;

public class ReverseAbc {
	public static void main (String[] args) {
		try {
			Scanner scanner1 = new Scanner(System.in);
			List <String> Array_List = new ArrayList<>();
			while (scanner1.hasNextLine()) {
				String str = scanner1.nextLine();
				Scanner scanner = new Scanner(str);
				Array_List.add(".");
				try {
					while (scanner.hasNextWord()) {
					String word = scanner.nextWord();
					StringBuilder String_Builder = new StringBuilder();
					int Word_Len = word.length();
					for (int i = 0; i < Word_Len; i++) {
						String_Builder.append(word.charAt(i));
					}
					Array_List.add(String_Builder.toString());
					}
				} catch (IOException e) {
					System.out.println ("Look, this is IOException" + e.getMessage());
				} finally {
					scanner.close();
				}
			}
			
			for (int i = Array_List.size() - 2; i >= 0; i--) {
				if (Array_List.get(i).equals(".")) {
					System.out.println("");
				} else {
					System.out.print(Array_List.get(i) + " ");
				}
			}
		}
		catch (IOException e) {
			System.out.println ("Look, this is IOException" + e.getMessage());
		}
	}
		
} 

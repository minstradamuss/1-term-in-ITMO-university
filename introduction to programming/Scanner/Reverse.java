import java.util.*;
import java.io.*;

public class Reverse {
    public static void main (String[] args) {
		try {
			Scanner scanner1 = new Scanner(System.in);
			List Array_List = new ArrayList();
			
			while (scanner1.hasNextLine()) {
				Array_List.add(".");
				String str = scanner1.nextLine();
				Scanner scanner = new Scanner(str);
				while (scanner.hasNextInt()) {
					Array_List.add(scanner.nextInt());
				}
			}
			int Size_Of_Array = Array_List.size();
			for (int i = Size_Of_Array - 2; i > -1; i--) {
				if (Array_List.get(i) != ".") {
					System.out.print(Array_List.get(i) + " ");
				} else {
				System.out.println("");
				}
			}
		}
		catch (IOException e) {
			System.out.println ("Look, this is IOException" + e.getMessage());
		}
	} 
}
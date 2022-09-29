
//Author: minstradamuss

import java.util.*;

public class Reverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] massive = new int[1000][1000];
        int[] box = new int[1000];
		
		int n = 0;
		while (sc.hasNextLine()) {
            Scanner input = new Scanner(sc.nextLine());
			
			int m = 0;
            while (input.hasNextInt()) {
                int len_box = box.length;
				if (len_box <= m) {
                    box = Arrays.copyOf(box, len_box * 2);
                }
				else {
					box[m] = input.nextInt();
					m++;
				}
            }
			
			int len_massive = massive.length;
            if (len_massive <= n) {
                massive = Arrays.copyOf(massive, len_massive * 2);
            }
			else {
				massive[n] = Arrays.copyOf(box, m);
				n++;
			}
        }
        massive = Arrays.copyOf(massive, n);
        
		int len_massive = massive.length;
		for (int i = len_massive - 1; i > -1; i--) {
			int len_mas_i = massive[i].length;
            for (int j = len_mas_i - 1; j > -1; j--) {
                System.out.print(massive[i][j] + " ");
            }
            System.out.println();
        }
    }
}

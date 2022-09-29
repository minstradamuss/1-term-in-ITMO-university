import java.util.*;
public class ReverseTranspose {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] massive = new int[100][100];
		int n = 0;
		int maxi = 0;
		
		while (sc.hasNextLine()) {
			int i = 0;
			massive[n] = new int[100];
			Scanner input = new Scanner (sc.nextLine());
			while (input.hasNextInt()) {
				int len_mas_n = massive[n].length;
				if (len_mas_n == i + 1) {
					massive[n] = Arrays.copyOf(massive[n], len_mas_n * 2);
				}
				else {
					massive[n][i] = input.nextInt();
					i++;
				}
			}
			if (maxi < i) {
				maxi = i;
			}
			massive[n] = Arrays.copyOf(massive[n], i);
			n++;
			int len_massive = massive.length;
			if (len_massive == n) {
				massive = Arrays.copyOf(massive, len_massive * 2);
			}
		}
		massive = Arrays.copyOf(massive, n);
		
		for (int i = 0; i < maxi; i++) {
			int len_mas = massive.length;
			for (int j = 0; j < len_mas; j++) {
				int len_mas_j = massive[j].length;
				if (i < len_mas_j) {
					System.out.print(massive[j][i] + " ");
				}
			}
			System.out.println();
		}
	}
}
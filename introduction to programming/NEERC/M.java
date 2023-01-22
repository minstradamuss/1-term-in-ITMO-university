import java.util.*;
import java.io.*;
import java.nio.*;
import java.lang.*;
import java.math.*;

public class M {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while (test > 0) {
            int n = sc.nextInt();
            int[] mas = new int [n];
            for (int i = 0; i < n; i++) {
                mas[i] = sc.nextInt();
            }
            HashMap <Integer, Integer> hash = new HashMap<>();
            int result = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (hash.containsKey(2 * mas[i] - mas[j])) {
                        result += hash.get(2 * mas[i] - mas[j]);
                    }
                }
                if (hash.containsKey(mas[i])) {
                    hash.put(mas[i], hash.get(mas[i]) + 1);
                }
				else {
                    hash.put(mas[i], 1);
                }
            }
            System.out.println(result);
			test--;
        }
    }
}
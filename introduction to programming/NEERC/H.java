import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.nio.*;
import java.lang.*;
import java.math.*;

public class H {
    private static int size = 0;
    private static int[] trans;

    private static int solve(int t) {
        int count = 0;
        for (int i = 0; i < size; i++) {

            int start = i;
            int end = size - 1;

            int before = 0;
            if (i > 0) before = trans[i - 1];

            while (start < end) {
                int m = end - (end - start) / 2;
                if (trans[m] - before > t) {
                    end = m - 1;
                } else {
                    start = m;
                }
            }

            i = start;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();

        trans = new int[size];
        int maxVal = 0;
        for (int i = 0; i < size; i++) {
            trans[i] = scanner.nextInt();
            if (trans[i] > maxVal) maxVal = trans[i];

            if (i > 0)
                trans[i] += trans[i - 1];
        }

        int q = scanner.nextInt();
        HashMap<Integer, Integer> cached = new HashMap<>();
        for (int i = 0; i < q; i++) {
            int t = scanner.nextInt();
            
            if (cached.containsKey(t)) {
                System.out.println(cached.get(t));
                continue;
            }
            if (t < maxVal) {
                System.out.println("Impossible");
                continue;
            }
            cached.put(t, solve(t));
            System.out.println(cached.get(t));
        }
    }
}
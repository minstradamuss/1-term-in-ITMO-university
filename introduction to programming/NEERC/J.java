import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.nio.*;
import java.lang.*;
import java.math.*;

public class J {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] mas = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < n; j++) {
                mas[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mas[i][j] > 0) {
                    for (int k = 0; k < n; k++) {
                        mas[i][k] = (mas[i][k] + 10 - mas[j][k]) % 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mas[i][j]);
            }
            System.out.println("");
        }
    }
}
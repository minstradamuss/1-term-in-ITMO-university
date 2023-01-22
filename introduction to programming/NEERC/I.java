import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.nio.*;
import java.lang.*;
import java.math.*;

public class I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int xR = Integer.MAX_VALUE;
        int yR = Integer.MAX_VALUE;
        int xL = Integer.MAX_VALUE;
        int yL = Integer.MAX_VALUE;
		int deltaLx = Integer.MAX_VALUE;
		int deltaLy = Integer.MAX_VALUE;
		int deltaRx = Integer.MAX_VALUE;
		int deltaRy = Integer.MAX_VALUE;
		
		while (n > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int h = sc.nextInt();
            
			deltaLx = x - h;
			if (xL > deltaLx) {
                xL = deltaLx;
            }
			
			deltaLy = y - h;
			if (yL > deltaLy) {
                yL = deltaLy;
            }
			
			deltaRx = x + h;
            if (xR < deltaRx) {
                xR = deltaRx;
            }
			
			deltaRy = y + h;
            if (yR < deltaRy) {
                yR = deltaRy;
            }
			n--;
        }
        //the remainder of dividing!!!!!!!
		System.out.print(((xL + xR) / 2) + " " + ((yL + yR) / 2) + " " + ((Math.max(xR - xL, yR - yL) / 2) + (Math.max(xR - xL, yR - yL) % 2)));
    }
}
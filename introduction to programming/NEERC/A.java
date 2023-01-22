import java.util.*;
public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
		double a = sc.nextDouble();
        double b = sc.nextDouble();
        double n = sc.nextDouble();
        double d = 2 * Math.ceil((n - b) / (b - a)) + 1;
		int result = (int) d;
        System.out.println(result);
    }
}
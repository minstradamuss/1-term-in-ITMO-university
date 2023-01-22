import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = -25000; i < n - 25000; i++) {
			int result = 710 * i;
            System.out.println(result);
        }
    }
}
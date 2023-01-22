import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.nio.*;
import java.lang.*;
import java.math.*;

public class D {
	private static final long MOD = 998244353;

	private static long helper(long p, long k) {
		if (p == 0)	return 1;
		if (p % 2 == 1)	return k * helper(p - 1, k) % MOD;

		long temp = helper(p / 2, k);
		return temp * temp % MOD;
	}

	private static ArrayList<Integer> divs(int n) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = 1; i * i <= n; i++) if (n % i == 0) {
			ret.add(i);
			if (i * i != n) ret.add(n / i);
		}
		return ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		long[] all = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			if (i % 2 == 0)
				all[i] = helper((i + 2) / 2, k) * ((i) / 2) + helper(i / 2, k) * ((i) / 2);
			else
				all[i] = i * helper((i + 1) / 2, k);
			all[i] %= MOD;
		}

		long[] corr = new long[n + 1];
		corr[1] = k;
		for (int i = 2; i <= n; i++) {
			for (Integer l : divs(i)) {
				if (l != i) corr[i] += (i / l) * corr[l];
				corr[i] %= MOD;
			}
			corr[i] = ((all[i] - corr[i]) % MOD + MOD) % MOD;
		}

		long res = 0;
		for (int i = 1; i <= n; i++) {
			for (Integer l : divs(i)) {
				res += corr[l];
				res %= MOD;
			}
		}
		System.out.println(res);
	}
}

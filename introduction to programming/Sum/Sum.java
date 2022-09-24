//Author: minstradamuss

public class Sum {
    public static void main(String[] args) {
        int summa = 0;
	for (String str : args) {
            int res = 0;
            for (int j = 0; j < str.length(); j++) {
                int left = j;
                int right = j;
                if (Character.isWhitespace(str.charAt(j))) {
                    continue;
                }
                if (str.charAt(j) == '-') { 
                    right++;
                }
                while (right < str.length() && Character.isDigit(str.charAt(right))) {
                    right++;
                }
                if (left != right) {                 
		    res += Integer.parseInt(str.substring(left, right));
                }
                j = right;
            }
            summa += res;
        }
    System.out.println(summa);	
    }
}

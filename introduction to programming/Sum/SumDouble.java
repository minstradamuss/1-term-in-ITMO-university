public class SumDouble {
    public static void main(String[] args) {
        double summa = 0;
	for (String str : args) {
            double res = 0;
            for (int j = 0; j < str.length(); j++) {
                int right = j;
                int left = j;
                if (Character.isWhitespace(str.charAt(j))) {
                    continue;
                }
                if (str.charAt(j) == '-') { 
                    right++;
                }
                while (right < str.length() && (Character.isDigit(str.charAt(right)) || str.charAt(right) == '.' || str.charAt(right) == '-' || str.charAt(right) == 'e' || str.charAt(right) == 'E')) {
                    right++;
                }
                if (left != right) {              
		    res += Double.parseDouble(str.substring(j, right));
		}
                j = right;
            }
            summa += res;
        }
    System.out.println(summa);	
    }
}

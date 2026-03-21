import java.util.Scanner;

public class Eighth {
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        System.out.println(checkDigits(s, 0)); 
    }

    public static String checkDigits(String s, int index) {
        if (index == s.length()) {
            return "Yes";
        }
        if (!Character.isDigit(s.charAt(index))) {
            return "No";
        }
        return checkDigits(s, index + 1);
    }
}

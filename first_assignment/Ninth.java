import java.util.Scanner;
public class Ninth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        System.out.println(count(number)); 
    }
    public static int count(String n) {
        if (n.length() == 0) {
            return 0;
        }
        return count(n.substring(1)) + 1;
    }
}

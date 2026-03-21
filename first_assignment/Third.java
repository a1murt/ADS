import java.util.Scanner;
public class Third {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println(rec(number, number - 1)); 
    }
    public static String rec(int n, int count) {
        if (count == 1) {
            return "Prime";
        }
        if (n % count == 0) {
            return "Composite";
        }
        return rec(n, count - 1);
    }
}
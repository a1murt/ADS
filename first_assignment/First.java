import java.util.Scanner;
public class First {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        rec(number); 
    }
    public static void rec(int n) {
        if (n == 0) {
            return;
        }
        rec(n / 10);
        System.out.println(n % 10);
    }
}
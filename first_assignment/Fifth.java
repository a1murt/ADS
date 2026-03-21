import java.util.Scanner;
public class Fifth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println(fib(number)); 
    }
    public static int fib(int n) {
        if (n == 0) {
            return 0;   
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}

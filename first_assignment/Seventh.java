import java.util.Scanner;

public class Seventh {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        rec(arr, n);
    }
    public static void rec(int[] arr, int n) {
        if (n == 0) {
            return;
        }
        System.out.print(arr[n - 1] + " "); 
        rec(arr, n - 1); 
    }
}

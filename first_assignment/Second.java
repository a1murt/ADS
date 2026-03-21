import java.util.Scanner;
public class Second {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); 
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt(); 
        }
        double totalSum = sum(arr, n); 
        double average = totalSum / n;
        System.out.println(average);
    }
    public static int sum(int[] arr, int n) {
        if (n == 0) {
            return 0;   
        }
        return arr[n - 1] + sum(arr, n - 1);
    }
}
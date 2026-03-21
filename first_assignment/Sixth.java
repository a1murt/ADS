import java.util.Scanner;

public class Sixth {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int count= scanner.nextInt();
        System.out.println(rec(number, count)); 
    }
    public static int rec(int n, int count) {
        if (count == 0) {
            return 1;
        }
        return rec(n, count-1)*n;

    }
}

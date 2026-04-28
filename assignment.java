import java.util.Scanner;
public class assignment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number1 = scanner.nextInt();
        for (int i = 0; i < number1; i++) {
            int number = scanner.nextInt();
        }
        rec(number);
    }
    public static void rec(int number) {
        if (number == 0) {
            return;
        }
        System.out.println(number);
        rec(number - 1);


    }
}
    


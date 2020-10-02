import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();
        scanner.close();

        boolean res = false;

        if (-15 < N && N <= 12) res = true;
        else if (14 < N && N < 17) res = true;
        else if (19 <= N) res = true;

        System.out.println(res ? "True" : "False");
    }
}
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();

        scanner.close();

        System.out.println(
                ((N + 1) * N + 2) * N + 3
        );
    }
}
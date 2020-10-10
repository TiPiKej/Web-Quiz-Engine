import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(toFahrenheit(scanner.nextDouble()));
    }

    public static double toFahrenheit(double celsius) {
        return celsius * 1.8 + 32;
    }
}
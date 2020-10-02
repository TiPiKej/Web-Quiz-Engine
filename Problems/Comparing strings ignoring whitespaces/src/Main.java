import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();

        scanner.close();

        final StringBuilder line1WithoutSpaces = new StringBuilder();
        final StringBuilder line2WithoutSpaces = new StringBuilder();

        for (char c : line1.toCharArray()) {
            if (c != ' ') line1WithoutSpaces.append(c);
        }

        for (char c : line2.toCharArray()) {
            if (c != ' ') line2WithoutSpaces.append(c);
        }

        System.out.println(line1WithoutSpaces.toString().equals(line2WithoutSpaces.toString()));
    }
}
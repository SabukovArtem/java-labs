import java.util.Scanner;


void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Введите число a: ");
    double a = scanner.nextDouble();
    System.out.print("Введите число b: ");
    double b = scanner.nextDouble();


    double max = Math.max(a, b);
    System.out.println("Наибольшее число: " + max);


    double d = Math.cos(max);
    System.out.println("d = cos(" + max + ") = " + d);

    scanner.close();
}
import java.util.Scanner;

public class Task1 {

    // Метод для задачи 1
    public static double task1(double x, double y) {
        return Math.max(x, y) * 5;
    }

    // Метод для задачи 2
    public static double task2(double x, double y) {
        return Math.min(x, y) / 10;
    }

    // Метод для задачи 3
    public static String task3(double x, double y) {
        if (x * y > 100) {
            return "Условие выполняется: 2 * " + x + "³ = " + (2 * Math.pow(x, 3));
        }
        return "Условие не выполняется";
    }

    // Метод для задачи 4
    public static double task4(double x, double y) {
        return (x + y > 20) ? 3 * Math.pow(x, 2) : Math.pow(y, 3);
    }

    // Метод для задачи 5
    public static String task5(double x, double y) {
        if (x * y > 50) {
            return "Условие выполняется: 2 * √" + x + " = " + (2 * Math.sqrt(x));
        }
        return "Условие не выполняется";
    }

    // Метод для задачи 6
    public static String task6(double a, double b) {
        if (a + b > 100) {
            return "Условие выполняется: 2 * sin(" + a + ") = " + (2 * Math.sin(a));
        }
        return "Условие не выполняется";
    }

    // Метод для задачи 7
    public static double task7(double x, double y) {
        return Math.pow(Math.max(x, y), 2);
    }

    // Метод для задачи 8
    public static double task8(double a, double b) {
        return (a * b > 100) ? 3 * Math.tan(b) : a * 5;
    }

    // Метод для задачи 9
    public static String task9(double a, double b) {
        if (a * b > 20) {
            if (Math.tan(b) != 0) {
                return "Условие выполняется: ctg(" + b + ") = " + (1 / Math.tan(b));
            }
            return "Условие выполняется, но ctg не существует (деление на 0)";
        }
        return "Условие не выполняется: " + a + " / 3 = " + (a / 3);
    }

    // Метод для задачи 10
    public static double task10(double x, double y) {
        return Math.min(x, y) / 2;
    }

    // Метод для задачи 11
    public static String task11(double a, double b) {
        if (a * b > 30) {
            if (Math.tan(a) != 0) {
                return "Условие выполняется: 2 * ctg(" + a + ") = " + (2 * (1 / Math.tan(a)));
            }
            return "Условие выполняется, но ctg не существует (деление на 0)";
        }
        return "Условие не выполняется: " + a + " / 2 = " + (a / 2);
    }

    // Метод для задачи 12
    public static double task12(double x, double y) {
        return Math.sqrt(Math.min(x, y));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        double num1 = scanner.nextDouble();
        System.out.print("Введите второе число: ");
        double num2 = scanner.nextDouble();

        System.out.println("\n=== Результаты всех заданий ===\n");

        System.out.println("1. " + task1(num1, num2));
        System.out.println("2. " + task2(num1, num2));
        System.out.println("3. " + task3(num1, num2));
        System.out.println("4. " + task4(num1, num2));
        System.out.println("5. " + task5(num1, num2));
        System.out.println("6. " + task6(num1, num2));
        System.out.println("7. " + task7(num1, num2));
        System.out.println("8. " + task8(num1, num2));
        System.out.println("9. " + task9(num1, num2));
        System.out.println("10. " + task10(num1, num2));
        System.out.println("11. " + task11(num1, num2));
        System.out.println("12. " + task12(num1, num2));

        scanner.close();
    }
}
public class Task10 {
    public static void main(String[] args) {
        // Проверяем, переданы ли два аргумента
        if (args.length < 2) {
            System.out.println("Ошибка: необходимо передать два числа в качестве аргументов.");
            return;
        }

        try {
            // Парсим аргументы в числа
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);

            // Находим меньшее число и делим на 2
            double min = Math.min(x, y);
            double result = min / 2;

            // Выводим результат
            System.out.println("Меньшее число: " + min);
            System.out.println("Результат деления на 2: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: аргументы должны быть числами.");
        }
    }
}
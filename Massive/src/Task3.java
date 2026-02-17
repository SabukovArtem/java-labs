// Сортировка массива

import java.util.*;

public class Task3 {
    private static final int SIZE = 15;
    private static final int MIN = 1;
    private static final int MAX = 20;
    private static final int TARGET = 10;
    private static final int N = 3;
    private static final int[] ALLOWED = {2, 5, 8, 12, 15};

    public static void main(String[] args) {
        int[] arr = createArray();
        System.out.println("Исходный массив:");
        printArray(arr);
        System.out.println();

        task1(arr);
        task2(arr);
        task3(arr);
        task4(arr);
        task5(arr);
        task6(arr);
        task7(arr);
        task8(arr);
        task9(arr);
        task10(arr);
        task11(arr);
        task12(arr);
    }

    private static int[] createArray() {
        int[] arr = new int[SIZE];
        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = rand.nextInt(MAX - MIN + 1) + MIN;
        }
        return arr;
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    private static void task1(int[] arr) {
        System.out.print("1. Номера элементов = " + TARGET + ": [");
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == TARGET) {
                System.out.print(i + " ");
                found = true;
            }
        }
        System.out.println(found ? "]" : "нет]");
    }

    private static void task2(int[] arr) {
        int[] copy = arr.clone();
        int minIdx = 0, maxIdx = 0;
        for (int i = 1; i < copy.length; i++) {
            if (copy[i] < copy[minIdx]) minIdx = i;
            if (copy[i] > copy[maxIdx]) maxIdx = i;
        }
        int temp = copy[minIdx];
        copy[minIdx] = copy[maxIdx];
        copy[maxIdx] = temp;
        System.out.println("2. После замены min и max:");
        printArray(copy);
    }

    private static void task3(int[] arr) {
        List<Integer> values = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < TARGET) {
                values.add(arr[i]);
                indices.add(i);
            }
        }
        System.out.println("3. Элементы < " + TARGET + ": " + values);
        System.out.println("   Их номера: " + indices);
    }

    private static void task4(int[] arr) {
        int count = 0;
        for (int num : arr) {
            for (int allowed : ALLOWED) {
                if (num == allowed) {
                    count++;
                    break;
                }
            }
        }
        System.out.println("4. Элементов с разрешенными значениями: " + count);
    }

    private static void task5(int[] arr) {
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            boolean allowed = false;
            for (int allowedVal : ALLOWED) {
                if (num == allowedVal) {
                    allowed = true;
                    break;
                }
            }
            if (!allowed) result.add(num);
        }
        System.out.println("5. Элементы с неразрешенными значениями: " + result);
    }

    private static void task6(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int[] maxValues = new int[N];
        for (int i = 0; i < N; i++) {
            maxValues[i] = sorted[sorted.length - 1 - i];
        }
        System.out.println("6. " + N + " максимальных: " + Arrays.toString(maxValues));
    }

    private static void task7(int[] arr) {
        int[] reversed = arr.clone();
        for (int i = 0; i < reversed.length / 2; i++) {
            int temp = reversed[i];
            reversed[i] = reversed[reversed.length - 1 - i];
            reversed[reversed.length - 1 - i] = temp;
        }
        System.out.println("7. В обратном порядке:");
        printArray(reversed);
    }

    private static void task8(int[] arr) {
        List<Integer> unique = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            boolean duplicate = false;
            for (int j = 0; j < arr.length; j++) {
                if (i != j && arr[i] == arr[j]) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) unique.add(arr[i]);
        }
        System.out.println("8. Неповторяющиеся элементы: " + unique);
    }

    private static void task9(int[] arr) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) count++;
            }
            if (count == 1) indices.add(i);
        }
        System.out.println("9. Номера уникальных элементов: " + indices);
    }

    private static void task10(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) count++;
            }
            if (count > 1 && !duplicates.contains(arr[i])) {
                duplicates.add(arr[i]);
            }
        }
        System.out.println("10. Повторяющиеся элементы: " + duplicates);
    }

    private static void task11(int[] arr) {
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) count++;
        }
        System.out.println("11. Элементов > предыдущего: " + count);
    }

    private static void task12(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        System.out.print("12. После деления на max(" + max + "): [");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%.3f", (double) arr[i] / max);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
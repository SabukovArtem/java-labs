//Обработка массивов в Java


import java.util.*;

public class Task4 {
    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static final int MIN_VAL = -10;
    private static final int MAX_VAL = 10;
    private static final int TARGET = 0;

    public static void main(String[] args) {
        int[][] matrix = createMatrix();
        System.out.println("Матрица " + ROWS + "x" + COLS + ":");
        printMatrix(matrix);
        System.out.println();

        int[] b1 = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            int count = 0;
            for (int j = 0; j < COLS; j++) {
                if (matrix[i][j] >= 0) count++;
            }
            b1[i] = count;
        }
        System.out.println("1. Число неотрицательных в строках: " + Arrays.toString(b1));

        double[] b2 = new double[COLS];
        for (int j = 0; j < COLS; j++) {
            int sum = 0, count = 0;
            for (int i = 0; i < ROWS; i++) {
                if (matrix[i][j] > 0) {
                    sum += matrix[i][j];
                    count++;
                }
            }
            b2[j] = count > 0 ? (double) sum / count : 0;
        }
        System.out.println("2. Среднее арифметическое положительных в столбцах: " + Arrays.toString(b2));

        int[] b3 = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            int min = matrix[i][0];
            for (int j = 1; j < COLS; j++) {
                if (matrix[i][j] < min) min = matrix[i][j];
            }
            b3[i] = min;
        }
        System.out.println("3. Минимальное значение в строках: " + Arrays.toString(b3));

        int[] b4 = new int[COLS];
        for (int j = 0; j < COLS; j++) {
            int max = matrix[0][j];
            for (int i = 1; i < ROWS; i++) {
                if (matrix[i][j] > max) max = matrix[i][j];
            }
            b4[j] = max;
        }
        System.out.println("4. Максимальное значение в столбцах: " + Arrays.toString(b4));

        int[] b5 = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            int maxIdx = 0;
            for (int j = 1; j < COLS; j++) {
                if (matrix[i][j] > matrix[i][maxIdx]) maxIdx = j;
            }
            b5[i] = maxIdx;
        }
        System.out.println("5. Номер максимального в строках: " + Arrays.toString(b5));

        int[] b6 = new int[COLS];
        for (int j = 0; j < COLS; j++) {
            int minIdx = 0;
            for (int i = 1; i < ROWS; i++) {
                if (matrix[i][j] < matrix[minIdx][j]) minIdx = i;
            }
            b6[j] = minIdx;
        }
        System.out.println("6. Номер минимального в столбцах: " + Arrays.toString(b6));

        int[] b7 = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            int count = 0;
            for (int j = 0; j < COLS; j++) {
                if (matrix[i][j] < TARGET) count++;
            }
            b7[i] = count;
        }
        System.out.println("7. Число элементов < " + TARGET + " в строках: " + Arrays.toString(b7));

        int[][] b8 = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (matrix[i][j] != TARGET) {
                    b8[i][j] = matrix[i][j];
                }
            }
        }
        System.out.println("8. Элементы ≠ " + TARGET + " (0 означает = " + TARGET + "):");
        printMatrix(b8);

        int[] b9 = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            boolean sorted = true;
            for (int j = 1; j < COLS; j++) {
                if (matrix[i][j] < matrix[i][j-1]) {
                    sorted = false;
                    break;
                }
            }
            b9[i] = sorted ? 1 : 0;
        }
        System.out.println("9. Строки упорядочены по возрастанию (1-да, 0-нет): " + Arrays.toString(b9));

        int[] b10 = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            int count = 0;
            for (int j = 0; j < COLS; j++) {
                if (matrix[i][j] % 2 == 0) count++;
            }
            b10[i] = count;
        }
        System.out.println("10. Количество четных чисел в строках: " + Arrays.toString(b10));

        int[] b11 = new int[COLS];
        for (int j = 0; j < COLS; j++) {
            int sum = 0;
            for (int i = 0; i < ROWS; i++) {
                if (matrix[i][j] > 0) sum += matrix[i][j];
            }
            b11[j] = sum;
        }
        System.out.println("11. Сумма положительных в столбцах: " + Arrays.toString(b11));

        int[] b12 = new int[COLS];
        Arrays.fill(b12, 1);
        for (int j = 0; j < COLS; j++) {
            for (int i = 0; i < ROWS; i++) {
                if (matrix[i][j] > 0) b12[j] *= matrix[i][j];
            }
        }
        System.out.println("12. Произведение положительных в столбцах: " + Arrays.toString(b12));
    }

    private static int[][] createMatrix() {
        int[][] matrix = new int[ROWS][COLS];
        Random rand = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = rand.nextInt(MAX_VAL - MIN_VAL + 1) + MIN_VAL;
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
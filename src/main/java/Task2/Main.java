package Task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //устанавливаем пробел как разделитель
        scanner.useDelimiter("\\s+");
        //
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        int[][] booklet = new int[m][n];

        int counter = 1;

        for (int k = 0; k < (m + n); k++) {
            int i = k;
            int j = 0;

            while ((i >= 0) && (j >= 0)) {
                if ((j < m) && (i < n)) {
                    booklet[j][i] = counter;
                    counter++;
                }
                i--;
                j++;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(booklet[i][j] + " ");
            }
            System.out.println(); // Переход на новую строку для следующей строки матрицы
        }
    }
}

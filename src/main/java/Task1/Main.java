package Task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //читаем входную строку
        int min = scanner.nextInt();
        //оперделяем переменную где будем хранить итоговое значение
        int umberOfSquares = 0;
        if (min == 1) {
            umberOfSquares = 1;
        } else {
            umberOfSquares = (min * 2) + ((min - 2) * 2);
        }

        System.out.println(umberOfSquares);

        scanner.close();
    }
}

package Task3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[] shelves = new int[m];

        for (int i = 1; i <= m; i++) {
            String line = scanner.next();
            char action = line.charAt(0);
            int detailNumber = Integer.parseInt(line.substring(2));

            if (action == '+') {
                for (int j = 0; j < shelves.length; j++) {
                    if (shelves[j] == 0) {
                        System.out.println(j + 1);
                        shelves[j] = detailNumber;
                        break;
                    }
                }
            } else {
                for (int j = 0; j < shelves.length; j++) {
                    if (shelves[j] == detailNumber) {
                        shelves[j] = 0;
                        break;
                    }
                }
            }
        }
    }
}


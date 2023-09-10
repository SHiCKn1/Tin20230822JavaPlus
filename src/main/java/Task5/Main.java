package Task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static boolean findWay(int map[][], int n, int m) {
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE / 2);
            graph[i][i] = 0;
        }


        for (int i = 0; i < m; i++) {
            int from = map[i][0];
            int to = map[i][1];
            graph[from][to] = 1; // Дорожка между домами
            graph[to][from] = 1; // Обратное направление
        }

        // Алгоритм Флойда-Уоршалла
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        // Проверка возможности добраться из каждого дома в каждый
        boolean possible = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == Integer.MAX_VALUE / 2) {
                    possible = false;
                    break;
                }
            }
            if (!possible) {
                break;
            }
        }

        return possible;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //устанавливаем пробел как разделитель
        scanner.useDelimiter("\\s+");
        //
        int n = scanner.nextInt(); //домики
        int m = scanner.nextInt(); //дорожки

        int[][] map = new int[m][3];

        for (int i = 0; i < m; i++) {
            //Заполним матрицу входными данными
            map[i][0] = scanner.nextInt(); //начальная точка
            map[i][1] = scanner.nextInt(); //конечная точка
            map[i][2] = scanner.nextInt(); //высота дорожки
        }

        //отсортируем по колонке 2
        Arrays.sort(map, Comparator.comparingInt(arr -> arr[2]));

        boolean thereIsАRoads = true;
        int height = 0;

        while (thereIsАRoads) {
            height += 100; //поднимаем высоту на 100
            int newM = 0; //количество отсавшихся дорожек

            for (int i = 0; i < m; i++) {
                if (map[i][2] > height) {
                    newM++;
                }
            }

            //создаем карту оставшихся дорожек
            int[][] mapForGraf = new int[newM][2];
            int j = 0;
            for (int i = 0; i < m; i++) {
                if (map[i][2] > height) {
                  mapForGraf[j][0] = map[i][0];
                  mapForGraf[j][1] = map[i][1];
                  j++;
                }
            }

            //проверяем можно ли добратся из всех домиков во все
            thereIsАRoads = findWay(mapForGraf,n,newM);
            if (!thereIsАRoads) {
                System.out.println(height);
                break;
            }

        }
    }
}

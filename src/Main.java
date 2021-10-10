import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Задание 2-1
        int[] arr_1 = {1, 0, 1, 1, 0, 1, 0, 0, 0, 1};
        System.out.print("\n\nTask 2-1\nStart values:\n\tarr -> [ ");
        for (int j : arr_1) {
            System.out.print(j + " ");
        }
        System.out.print(" ]\nResult ->\n\tarr -> [ ");
        for (int i =0; i < arr_1.length; i++) {
            if (arr_1[i] == 0)
                arr_1[i] = 1;
            else
                arr_1[i] = 0;
            System.out.print(arr_1[i] + " ");
        }
        System.out.print("]");

        //Задание 2-2
        int[] arr_2 = new int[8];
        System.out.print("\n\nTask 2-2\nResult ->\n\tarr -> [ ");
        for (int i = 0; i < 8; i++) {
            arr_2[i] = 3 * i;
            System.out.print(arr_2[i] + " ");
        }
        System.out.print("]");

        //Задание 2-3
        int[] arr_3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.print("\n\nTask 2-3\nStart values:\n\tarr -> [ ");
        for (int k : arr_3) {
            System.out.print(k + " ");
        }
        System.out.print(" ]\nResult ->\n\tarr -> [ ");
        for (int i = 0; i < arr_3.length; i++) {
            if(arr_3[i] < 6)
                arr_3[i] = arr_3[i] * 2;
            System.out.print(arr_3[i] + " ");
        }
        System.out.print("]");


        //Задание 2-4
        int countElement_arr_4 = 7;
        int[][] arr_4 = new int[countElement_arr_4][countElement_arr_4];
        for (int i = 0; i < arr_4.length; i++) {
            for (int j = 0; j < arr_4[i].length; j++){
                if (i == j || i + j == countElement_arr_4-1)
                    arr_4[i][j] = 1;
                else
                    arr_4[i][j] = 0;
            }
        }
        System.out.print("\n\nTask 2-4\nResult ->\n\tarr ->\n");
        for (int[] ints : arr_4) {
            System.out.print("\t\t\t[ ");
            for (int anInt : ints) System.out.print(anInt + " ");
            System.out.print("]\n");
        }
        System.out.print("]");


        //Задача 2-5
        int countElement_arr_5 = 10;
        double[] arr_5 = new double[countElement_arr_5];
        double minElement = 0;
        double maxElement = 0;
        System.out.print("\n\nTask 2-5\nStart values:\n\tarr -> [ ");
        for (int i = 0; i < arr_5.length; i++) {
            arr_5[i] = GetRandomValue(-100, 100);
            System.out.print(arr_5[i] + " ");
            if (i == 0 || arr_5[i] < minElement)
                minElement = arr_5[i];
            if (i == 0 || arr_5[i] > maxElement)
                maxElement = arr_5[i];
        }
        System.out.print("]");
        System.out.print("\nResult ->\n\tmin -> " + minElement + "\n\tmax -> " + maxElement);


        //Задача 2-6
        int countElement_arr_6 = 10;
        int[] arr_6 = new int[countElement_arr_6];
        System.out.print("\n\nTask 2-6\nStart values:\n\tarr -> [ ");
        for (int i = 0; i < arr_6.length; i++) {
            arr_6[i] = Math.round((float)GetRandomValue(-100, 100));
            System.out.print(arr_6[i] + " ");
        }
        System.out.print("]");
        System.out.print("\nResult -> " + checkBalance(arr_6));
    }

    public static double GetRandomValue(int min, int max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }

    public static boolean checkBalance(int[] arr) {
        int sumLeftPart = 0;
        int sumRightPart = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            // суммируем элементы левой части
            sumLeftPart += arr[i];
            if (i == 0)
                // если это первый проход, то суммируем все элементы в правой части
                for (int j = i + 1; j < arr.length; j++)
                    sumRightPart += arr[j];
            else
                // если это не первый проход, то вычитаем поселдний "актуальный" элемент
                sumRightPart -= arr[i];
            if (sumLeftPart == sumRightPart) {
                // если суммы равны
                System.out.print(sumLeftPart + " " + sumRightPart + "\t");
                return true;
            }
        }
        // суммы левой и правой частей ниразу не совпали
        return false;
    }
}

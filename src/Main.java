import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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
    }

}

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Задание 1-1
        boolean var_boolean = true;
        byte vat_byte = 1;
        short var_short = 1;
        int var_int = 1;
        long var_long = 1;
        double var_double = 1.1;
        float var_float = 1.1F;
        char var_char = '1';
        String var_string = "11";

        // Задание 1-2
        System.out.print("Task 1-2:\nInput:\n\ta -> ");
        double a = sc.nextDouble();
        System.out.print("\tb -> ");
        double b = sc.nextDouble();
        System.out.print("\tc -> ");
        double c = sc.nextDouble();
        System.out.print("\td -> ");
        double d = sc.nextDouble();

        double res_1 = CalcMethodForTask_1_2(a, b, c, d);
        System.out.print("Result ->\n\t" + res_1);
    }

    public static double CalcMethodForTask_1_2 (double a, double b, double c, double d) {
        return a * (b + (c/d));
    }

}

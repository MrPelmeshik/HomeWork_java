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

        //Задание 1-3
        System.out.print("\n\nTask 1-3\nInput:\n\tx1 -> ");
        double x1 = sc.nextDouble();
        System.out.print("\tx2 -> ");
        double x2 = sc.nextDouble();

        boolean res_2 = CalcMethodForTask_1_3(x1, x2);
        System.out.print("Result ->\n\t" + res_2);

        //Задание 1-4
        System.out.print("\n\nTask 1-4\nInput:\n\tx1 -> ");
        int x = sc.nextInt();

        System.out.print("Result ->\n\t" + CalcMethodForTask_1_4(x));


        //Задание 1-5
        System.out.print("\n\nTask 1-5\nInput:\n\tx1 -> ");
        String name = sc.next();

        System.out.print("Result ->\n\t");
        MethodForTask_1_5(name);


        //Задание 1-6
        System.out.print("\n\nTask 1-6\nInput:\n\tx1 -> ");
        int year = sc.nextInt();

        System.out.print("Result ->\n\tГод " + CalcMethodForTask_1_6(year));
    }

    public static double CalcMethodForTask_1_2 (double a, double b, double c, double d) {
        return a * (b + (c/d));
    }

    public static boolean CalcMethodForTask_1_3 (double x1, double x2) {
        if((x1 + x2) <= 20 && (x1 + x2) >= 10)
            return true;
        else
            return false;
    }

    public static String CalcMethodForTask_1_4 (int x) {
        if (x < 0)
            return "Число отрицательное";
        else
            return "Число положительное";
    }

    public static void MethodForTask_1_5 (String name) {
        System.out.print("Привет, " + name + "!");
    }

    public static String CalcMethodForTask_1_6 (int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
            return "високосный";
        else
            return "не високосный ";
    }

}

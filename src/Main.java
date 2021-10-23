import java.util.Random;
import java.util.Scanner;

public class Main {

    // 3. Определяем размеры массива
    static final int SIZE_X = 5;
    static final int SIZE_Y = 5;
    static final int N = 4;

    // 1. Создаем двумерный массив
    static char[][] field = new char[SIZE_Y][SIZE_X];

    // 2. Обозначаем кто будет ходить какими фишками
    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = '0';
    static final char EMPTY_DOT = ' ';

    // 8. Создаем сканер
    static Scanner scanner = new Scanner(System.in);
    // 12. Создаем рандом
    static final Random rand = new Random();

    // 4. Заполняем на массив
    private static void initField() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    // 5. Выводим на массив на печать
    private static void printField() {
        System.out.println("\nNow ->");
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }

    }

    // 7. Метод который устанавливает символ
    private static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    // 9. Ход игрока
    private static void playerStep() {
        // 11. с проверкой
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X Y (1-3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y, x));
        setSym(y, x, PLAYER_DOT);

    }

    // 13. Ход ПК
    private static void aiStep() {
        int x;
        int y;
        do {
            x = rand.nextInt(SIZE_X);
            y = rand.nextInt(SIZE_Y);
        } while (!isCellValid(y, x));
        setSym(y, x, AI_DOT);
    }

    // 14. Проверка победы
    private static boolean checkWin(char sym) {
        // Проперка горизонталей
        for (int i = 0; i < SIZE_Y; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == sym) {
                    counter++;
                    if (counter >= N)
                        return true; // победа
                }
                else
                    counter = 0;
            }
        }

        // Проверка вертикалей
        for (int i = 0; i < SIZE_X; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE_Y; j++) {
                if (field[j][i] == sym) {
                    counter++;
                    if (counter >= N)
                        return true;
                }
                else
                    counter = 0;
            }
        }

        // Проверка диагоналей от левой вертикали
        for (int i = 0; i < SIZE_Y; i++) {
            // Левая вертикаль
            int k_up = i, k_dwn = i; // итератор для диагоналей вверх и вниз
            int counter_up = 0, counter_dwn = 0; // счетчики комбинаций для диагоналей вверх и вниз
            for (int j = 0; j < SIZE_X; j++) {
                //Проверка для диагонали вверх
                if (k_up >= 0) {
                    if (field[k_up][j] == sym) {
                        counter_up++;
                        if (counter_up == N) {
                            System.out.println("check -> Проверка диагоналей/Левая вертикаль/Проверка для диагонали вверх");
                            return true;
                        }
                    }
                    else
                        counter_up = 0;

                    k_up--;
                }

                // Проверка для диагонали вниз
                if (k_dwn < SIZE_Y) {
                    if (field[k_dwn][j] == sym) {
                        counter_dwn++;
                        if (counter_dwn == N) {
                            System.out.println("check -> Проверка диагоналей/Левая вертикаль/Проверка для диагонали вниз");
                            return true;
                        }
                    }
                    else
                        counter_dwn = 0;

                    k_dwn++;
                }
            }
        }

        // Проверка диалоналей от верхней и нижней горизонталей
        for (int i = 1; i < SIZE_Y; i++) {
            int k_up = SIZE_Y - 1, k_dwn = 0; // итератор для диагоналей вверх и вниз
            int counter_up = 0, counter_dwn = 0; // счетчики комбинаций для диагоналей вверх и вниз
            for (int j = i; j < SIZE_Y; j++) {
                if (k_up >= 0) {
                    if (field[j][k_up] == sym) {
                        counter_up++;
                        if (counter_up == N) {
                            System.out.println("check -> Проверка диалоналей от верхней и нижней горизонталей");
                            return true;
                        }
                    } else
                        counter_up = 0;

                    k_up--;
                }

                // Проверка для диагонали вниз
                if (k_dwn < SIZE_Y) {
                    if (field[j][k_dwn] == sym) {
                        counter_dwn++;
                        if (counter_dwn == N) {
                            System.out.println("check -> Проверка для диагонали вниз");
                            return true;
                        }
                    } else
                        counter_dwn = 0;

                    k_dwn++;
                }
            }
        }

        return false; // Нет побудных комбинаций
    }

    // 16. Проверка полное ли поле? возможно ли ходить?
    private static boolean isFieldFull() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    // 10. Проверяем возможен ли ход
    private static boolean isCellValid(int y, int x) {
        // если вываливаемся за пределы возвращаем false
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1) {
            return false;
        }
        // если не путое поле тоже false
        return (field[y][x] == EMPTY_DOT);
    }

    public static void main(String[] args) {
        // 1 - 1 иницируем и выводим на печать
        initField();
        printField();
        // 1 - 1 иницируем и выводим на печать

        // 15 Основной ход программы

        while (true) {
            playerStep();
            printField();
            if (checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("Win SkyNet!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }
        }
    }

}

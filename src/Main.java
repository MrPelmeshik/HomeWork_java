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

    private static int[] checkSeq(char[] seq, char sym){
        int counter = 0;
        int countEmpty = 0, countSym = 0, num = -1;
        int maxCountSym = -1, maxNum = -1;
        for (int i = 0; i < seq.length; i++) {
            if(seq[i] != EMPTY_DOT || seq[i] != sym) {
                countSym = 0;
                countEmpty = 0;
                num = -1;
            } else {
                if(countSym + countEmpty == 0)
                    num = i;

                if (seq[i] == sym)
                    countSym++;
                else
                    countEmpty++;

                if(countSym + countEmpty >= N) {
                    if (maxCountSym < countSym || maxCountSym == -1) {
                        maxCountSym = countSym;
                        maxNum = num;
                    }
                }
            }
        }

        int[] result = new int[] {maxCountSym, maxNum};
        return result;
    }

    // Поиск координаты для победного хода
    private static int[] searchWinCoord(char sym) {
        int[] result = new int[] {-1, -1}; // массив для возврата {y, x}

        // Проперка горизонталей
        for (int i = 0; i < SIZE_Y; i++) {
            int counter = 0, counter_empty = 0;
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == sym) {
                    counter++;
                }
                else if (field[i][j] == EMPTY_DOT) {
                    counter_empty++;
                } else if (field[i][j] != EMPTY_DOT){
                    result[0] = -1;
                    result[1] = -1;
                    counter = 0;
                    counter_empty = 0;
                }
                if (counter == N - 1 && counter_empty == 1) {
                    result[0] = i;
                    result[1] = j;
                    System.out.println("check win combination -> Проперка горизонталей");
                    return result; // передаем координаты пустой клетки (победный ход)
                }
            }
        }

        // Проверка вертикалей
        for (int i = 0; i < SIZE_X; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE_Y; j++) {
                if (field[j][i] == sym) {
                    counter++;
                    if (counter == N) {
                        result[0] = i;
                        result[1] = j;
                        System.out.println("check win combination -> Проверка вертикалей");
                        return result; // передаем координаты пустой клетки (победный ход)
                    }
                } else {
                    result[0] = -1;
                    result[1] = -1;
                    counter = 0;
                }
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
                            result[0] = k_up;
                            result[1] = j;
                            System.out.println("check win combination -> Проверка диагоналей от левой вертикали/Левая вертикаль/Проверка для диагонали вверх");
                            return result; // передаем координаты пустой клетки (победный ход)
                        }
                    } else {
                        result[0] = -1;
                        result[1] = -1;
                        counter_up = 0;
                    }

                    k_up--;
                }

                // Проверка для диагонали вниз
                if (k_dwn < SIZE_Y) {
                    if (field[k_dwn][j] == sym) {
                        counter_dwn++;
                        if (counter_dwn == N) {
                            result[0] = k_dwn;
                            result[1] = j;
                            System.out.println("check win combination -> Проверка диагоналей от левой вертикали/Левая вертикаль/Проверка для диагонали вниз");
                            return result; // передаем координаты пустой клетки (победный ход)
                        }
                    } else {
                        result[0] = -1;
                        result[1] = -1;
                        counter_dwn = 0;
                    }

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
                            result[0] = j;
                            result[1] = k_up;
                            System.out.println("check win combination -> Проверка диалоналей от верхней и нижней горизонталей");
                            return result; // передаем координаты пустой клетки (победный ход)
                        }
                    } else {
                        result[0] = -1;
                        result[1] = -1;
                        counter_up = 0;
                    }

                    k_up--;
                }

                if (k_dwn < SIZE_Y) {
                    if (field[j][k_dwn] == sym) {
                        counter_dwn++;
                        if (counter_dwn == N) {
                            result[0] = j;
                            result[1] = k_dwn;
                            System.out.println("check win combination -> Проверка диалоналей от верхней и нижней горизонталей");
                            return result; // передаем координаты пустой клетки (победный ход)
                        }
                    } else {
                        result[0] = -1;
                        result[1] = -1;
                        counter_dwn = 0;
                    }

                    k_dwn++;
                }
            }
        }

        return result;
    }

    // Поиск координаты для хода (приближение к победе)
    private static int[] findingRightCoordMovement(char sym) {
        int[] result = new int[] {-1, -1}; // массив для возврата {y, x}

        // Проперка горизонталей
        for (int i = 0; i < SIZE_Y; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == sym) {
                    counter++;
                }
                else if (field[i][j] == EMPTY_DOT) {
                    counter++;
                    if (counter == N) {
                        result[0] = i;
                        result[1] = j;
                        return result; // передаем координаты пустой клетки (победный ход)
                    }
                } else {
                    result[0] = -1;
                    result[1] = -1;
                    counter = 0;
                }
            }
        }

        // Проверка вертикалей
        for (int i = 0; i < SIZE_X; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE_Y; j++) {
                if (field[j][i] == sym) {
                    counter++;
                    if (counter == N) {
                        result[0] = i;
                        result[1] = j;
                        return result; // передаем координаты пустой клетки (победный ход)
                    }
                } else {
                    result[0] = -1;
                    result[1] = -1;
                    counter = 0;
                }
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
                            result[0] = k_up;
                            result[1] = j;
                            return result; // передаем координаты пустой клетки (победный ход)
                        }
                    } else {
                        result[0] = -1;
                        result[1] = -1;
                        counter_up = 0;
                    }

                    k_up--;
                }

                // Проверка для диагонали вниз
                if (k_dwn < SIZE_Y) {
                    if (field[k_dwn][j] == sym) {
                        counter_dwn++;
                        if (counter_dwn == N) {
                            result[0] = k_dwn;
                            result[1] = j;
                            return result; // передаем координаты пустой клетки (победный ход)
                        }
                    } else {
                        result[0] = -1;
                        result[1] = -1;
                        counter_dwn = 0;
                    }

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
                            result[0] = j;
                            result[1] = k_up;
                            return result; // передаем координаты пустой клетки (победный ход)
                        }
                    } else {
                        result[0] = -1;
                        result[1] = -1;
                        counter_up = 0;
                    }

                    k_up--;
                }

                // Проверка для диагонали вниз
                if (k_dwn < SIZE_Y) {
                    if (field[j][k_dwn] == sym) {
                        counter_dwn++;
                        if (counter_dwn == N) {
                            result[0] = j;
                            result[1] = k_dwn;
                            return result; // передаем координаты пустой клетки (победный ход)
                        }
                    } else {
                        result[0] = -1;
                        result[1] = -1;
                        counter_dwn = 0;
                    }

                    k_dwn++;
                }
            }
        }

        return result;
    }

    // 13. Ход ПК
    private static void aiStep() {
        int[] coord = new int[2];

        do {

            coord = searchWinCoord(AI_DOT); // Проанализировать поле на наличие победного хода (аналогично проверки на победу)
            if (coord[0] >= 0 && coord[1] >= 0) { // Если мы можем победить в одни ход,
                System.out.println("ПК -> Победный ход");
                break; // делаем победный ход
            }

            coord = searchWinCoord(PLAYER_DOT); // Проанализировать поле на наличие победного хода пользователя (аналогично проверки на победу)
            if (coord[0] >= 0 && coord[1] >= 0) { //  Если противник может победить в один ход,
                System.out.println("ПК -> Не дать пользователю сделать победный ход");
                break; // недать пользователю сделать победный ход
            }

            coord = findingRightCoordMovement(AI_DOT); // Иначе (мы должны сделать осмысленный ход)
            if (coord[0] >= 0 && coord[1] >= 0) { // Если это возможно
                System.out.println("ПК -> Корректный ход");
                break; // то движемся по заданному пути
            }

            // ___Если мы прищли сюда, значит адекватных путей для победы нет
            // Тогда ставим на рандоме (мешать пользователю на этом этапе нет будем)
            coord[0] = rand.nextInt(SIZE_X);
            coord[1] = rand.nextInt(SIZE_Y);
            System.out.println("ПК -> Рандом, получается");

        } while (!isCellValid(coord[0], coord[1]));
        setSym(coord[0], coord[1], AI_DOT);
    }

    // 14. Проверка победы
    private static boolean checkWin(char sym) {
        // Проперка горизонталей
        for (int i = 0; i < SIZE_Y; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == sym) {
                    counter++;
                    if (counter == N)
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
                    if (counter == N)
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

        return false; // Нет победных комбинаций
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

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
    static final char BLOCK_DOT = 'W';

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

    // 7. Метод, который устанавливает символ
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
        int countEmpty = 0, countSym = 0, posEmpty = -1;
        int maxCountSym = -1, findPosEmpty = -1;
        for (int i = 0; i < seq.length; i++) {
            //System.out.print(seq[i] + ", ");
            if(seq[i] != EMPTY_DOT && seq[i] != sym) {
                countSym = 0;
                countEmpty = 0;
                posEmpty = -1;
            } else {
                if (seq[i] == sym)
                    countSym++;
                else {
                    countEmpty++;
                    posEmpty = i;
                }

                if(countSym + countEmpty >= N) {
                    if (maxCountSym < countSym || maxCountSym == -1) {
                        maxCountSym = countSym;
                        findPosEmpty = posEmpty;
                    }
                }
            }
        }

        //System.out.println();
        int[] result = new int[] {maxCountSym, findPosEmpty};
        return result;
    }

    // Поиск координаты для хода (приближение к победе)
    private static int[] findingRightCoordMovement(char sym) {
        int[] result = new int[] {-1, -1, 0}; // массив для возврата {y, x, wightSeq}
        int[] respondCheckSeq = new int[] {-1, -1}; // ответ checkSeq() -> {maxCountSym, posEmpty}

        // Проперка горизонталей
        for (int i = 0; i < SIZE_Y; i++) {
            char[] seq = new char[SIZE_X];
            for (int j = 0; j < SIZE_X; j++) {
                seq[j] = field[i][j];
            }
            respondCheckSeq = checkSeq(seq, sym);
            if (respondCheckSeq[0] >= result[2]) { // запоминаем предположительное место для совершения хода
                result[0] = i;
                result[1] = respondCheckSeq[1];
                result[2] = respondCheckSeq[0];
            }
            //System.out.println("\tПК (строка -> " + i + ") -> result[y, x, winSeq] = " + i + ", " + respondCheckSeq[1] + ", " + respondCheckSeq[0]);
        }

        // Проверка вертикалей
        for (int i = 0; i < SIZE_X; i++) {
            char[] seq = new char[SIZE_Y];
            for (int j = 0; j < SIZE_Y; j++) {
                seq[j] = field[j][i];
            }
            respondCheckSeq = checkSeq(seq, sym);
            if (respondCheckSeq[0] >= result[2]) { // запоминаем предположительное место для совершения хода
                result[0] = respondCheckSeq[1];
                result[1] = i;
                result[2] = respondCheckSeq[0];
            }
            //System.out.println("\tПК (столбец -> " + i + ") -> result[y, x, winSeq] = " + respondCheckSeq[1] + ", " + i + ", " + respondCheckSeq[0]);
        }

        // Проверка диагоналей от левой вертикали
        for (int i = 0; i < SIZE_Y; i++) {
            // Левая вертикаль
            int k_up = i, k_dwn = i; // итератор для диагоналей вверх и вниз
            int sizeDiagonal = (SIZE_X > SIZE_Y)? SIZE_X : SIZE_Y;
            char[] seq_up = new char[sizeDiagonal];
            char[] seq_dwn = new char[sizeDiagonal];
            for (int j = 0; j < SIZE_X; j++) {
                //Проверка для диагонали вверх
                if (k_up >= 0) {
                    seq_up[j] = field[k_up][j];
                    k_up--;
                }
                else {
                    seq_up[j] = BLOCK_DOT; // остаток последовательности забиваем блокирующими точками
                }

                // Проверка для диагонали вниз
                if (k_dwn < SIZE_Y) {
                    seq_dwn[j] = field[k_dwn][j];
                    k_dwn++;
                } else {
                    seq_dwn[j] = BLOCK_DOT; // остаток последовательности забиваем блокирующими точками
                }
            }

            // проверить последовательность элементов на диагонале вверх
            respondCheckSeq = checkSeq(seq_up, sym);
            if (respondCheckSeq[0] >= result[2]) { // запоминаем предположительное место для совершения хода
                result[0] = i - respondCheckSeq[1];
                result[1] = respondCheckSeq[1];
                result[2] = respondCheckSeq[0];
            }
            //System.out.println("\tПК (диагональ (строка -> " + i + ") вверх) -> result[y, x, winSeq] = " + (respondCheckSeq[1] - i) + ", " + respondCheckSeq[1] + ", " + respondCheckSeq[0]);

            // проверить последовательность элементов на диагонале вниз
            respondCheckSeq = checkSeq(seq_dwn, sym);
            if (respondCheckSeq[0] >= result[2]) { // запоминаем предположительное место для совершения хода
                result[0] = i + respondCheckSeq[1];
                result[1] = respondCheckSeq[1];
                result[2] = respondCheckSeq[0];
            }
            //System.out.println("\tПК (диагональ (строка -> " + i + ")  вниз) -> result[y, x, winSeq] = " + (respondCheckSeq[1] + i) + ", " + respondCheckSeq[1] + ", " + respondCheckSeq[0]);
        }

        // Проверка диагоналей от верхней и нижней горизонталей
        for (int i = 1; i < SIZE_X; i++) {
            int k_up = SIZE_Y - 1, k_dwn = 0; // итератор для диагоналей вверх и вниз
            int sizeDiagonal = (SIZE_X > SIZE_Y)? SIZE_X : SIZE_Y;
            char[] seq_up = new char[sizeDiagonal];
            char[] seq_dwn = new char[sizeDiagonal];
            int counter = 0; // счетчик запомненных элементов для последующего заполнения БЛОК символами
            for (int j = i; j < SIZE_X; j++) {
                if (k_up >= 0) {
                    seq_up[counter] = field[k_up][j];
                    k_up--;
                } else {
                    seq_up[counter] = BLOCK_DOT; // остаток последовательности забиваем блокирующими точками
                }

                if (k_dwn < SIZE_Y) {
                    seq_dwn[counter] = field[k_dwn][j];
                    k_dwn++;
                } else {
                    seq_dwn[counter] = BLOCK_DOT; // остаток последовательности забиваем блокирующими точками
                }
                counter++;
            }

            for (int j = counter; j < sizeDiagonal; j++) {
                seq_up[j] = BLOCK_DOT;
                seq_dwn[j] =BLOCK_DOT;
            }

            // проверить последовательность элементов на диагонале вверх
            respondCheckSeq = checkSeq(seq_up, sym);
            if (respondCheckSeq[0] >= result[2]) { // запоминаем предположительное место для совершения хода
                result[0] = SIZE_Y - respondCheckSeq[1] - 1;
                result[1] = respondCheckSeq[1] + i;
                result[2] = respondCheckSeq[0];
            }
            //System.out.println("\tПК (диагональ ГН (столбец -> " + i + ") вверх) -> result[y, x, winSeq] = " + (SIZE_Y - respondCheckSeq[1] - 1) + ", " + (respondCheckSeq[1] + i) + ", " + respondCheckSeq[0]);

            // проверить последовательность элементов на диагонале вниз
            respondCheckSeq = checkSeq(seq_dwn, sym);
            if (respondCheckSeq[0] >= result[2]) { // запоминаем предположительное место для совершения хода
                result[0] = respondCheckSeq[1];
                result[1] = respondCheckSeq[1] + i;
                result[2] = respondCheckSeq[0];
            }
            //System.out.println("\tПК (диагональ ГВ (столбец -> " + i + ")  вниз) -> result[y, x, winSeq] = " + (respondCheckSeq[1]) + ", " + (respondCheckSeq[1] + i) + ", " + respondCheckSeq[0]);

        }

        return result;
    }

    // 13. Ход ПК
    private static void aiStep() {
        int[] responseAI = new int[3]; // ответ функции по запросу анализа поля для бота (координаты точек и вес)
        int[] responsePlayer = new int[3]; // ответ функции по запросу анализа поля для игрока (координаты точек и вес)
        int[] coord = new int[2]; // финальные координаты для хода
        coord[0] = -1;
        coord[1] = -1;

        // анализируем поле от лица бота
        responseAI = findingRightCoordMovement(AI_DOT);
        if (responseAI[2] >= N - 1) { // если это победный ход
            // то делаем победный ход
            coord[0] = responseAI[0];
            coord[1] = responseAI[1];
            System.out.println("\tAI -> Сделать победный ход -> " + coord[0] + ", " + coord[1]);
        } else {
            // анализируем поле от лица игрока
            responsePlayer = findingRightCoordMovement(PLAYER_DOT);
            if (responsePlayer[2] >= N - 1) { // если это победный ход
                // то надо помешать этому
                coord[0] = responsePlayer[0];
                coord[1] = responsePlayer[1];
                System.out.println("\tAI -> Не дать пользователю сделать победный ход -> " + coord[0] + ", " + coord[1]);
            } else if (responseAI[2] > 0) { // Если есть хоть один ход для "улучшения" одной из последовательностей
                // то делаем ход
                coord[0] = responseAI[0];
                coord[1] = responseAI[1];
                System.out.println("\tAI -> Сделать ход -> " + coord[0] + ", " + coord[1]);
            } else if (responsePlayer[2] > 0) { // Если есть хоть один ход для "улучшения" одной из последовательностей
                // то надо помешать игроку
                coord[0] = responsePlayer[0];
                coord[1] = responsePlayer[1];
                System.out.println("\tAI -> Помешать игроку -> " + coord[0] + ", " + coord[1]);
            } else {
                do {
                    // Если мы прищли сюда, значит адекватных путей для победы нет.
                    // Тогда ставим на рандоме (мешать пользователю на этом этапе нет будем)
                    coord[0] = rand.nextInt(SIZE_X);
                    coord[1] = rand.nextInt(SIZE_Y);
                } while (!isCellValid(coord[0], coord[1]));
                System.out.println("\tAI -> Сделать ход (случайный) -> " + coord[0] + ", " + coord[1]);
            }
        }

        // проверка на то, что программа работает неверно
        if (!isCellValid(coord[0], coord[1])) {
            System.out.println("Упс...! Бот сломался! Но перед своей смертью он сообщил это -> " +
                    "\n\tresponseAI[int 3] -> " + responseAI[0] + ", " + responseAI[1] + ", " + responseAI[2] +
                    "\n\tresponsePlayer[int 3] -> " + responsePlayer[0] + ", " + responsePlayer[1] + ", " + responsePlayer[2] +
                    "\n\tcoord[int 2] -> " + coord[0] + ", " + coord[1]);
        }

        setSym(coord[0], coord[1], AI_DOT);
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
            int[] response = new int[3];
            playerStep();
            printField();
            response = findingRightCoordMovement(PLAYER_DOT);
            if (response[2] >= N) {
                System.out.println("Player WIN!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            response = findingRightCoordMovement(AI_DOT);
            if (response[2] >= N) {
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

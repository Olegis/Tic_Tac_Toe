import java.util.Random;
import java.util.Scanner;

public class Tic_Tac_Toe {

    private static final int SIZE = 3;

    private static final char DOT_EMPTY = '*';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';

    private static char[][] map;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {

            humanTurn();
            printMap();
            if (checkEnd(DOT_X, "Человек победил!"))
                break;

            aiTurn();
            printMap();
            if (checkEnd(DOT_0, "Компьютер победил!"))
                break;
        }

        System.out.println("Игра окончена!");
    }

    private static boolean checkEnd(char symbol, String winMessage) {
        if (checkWin(symbol)) {
            System.out.println(winMessage);
            return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin(char symbol) { //проверку на победу переделать
        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol) return true;
        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol) return true;
        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol) return true;

        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol) return true;
        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol) return true;
        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol) return true;

        if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) return true;
        if (map[0][2] == symbol && map[1][1] == symbol && map[2][0] == symbol) return true;

        return false;
    }

    private static void humanTurn() {
        int rowNumber, colNumber;
        do {
            System.out.println("Ход человека. Введите номер строки и столбца");
            System.out.println("Строка: ");
            rowNumber = scanner.nextInt();
            System.out.println("Столбец: ");
            colNumber = scanner.nextInt();
        } while (!isCellValid(rowNumber, colNumber));
        map[rowNumber - 1][colNumber - 1] = DOT_X;
    }

    private static void aiTurn() {
        System.out.println("Ход компьютера");
        int rowNumber, colNumber;
        do {
            rowNumber = random.nextInt(SIZE) + 1;
            colNumber = random.nextInt(SIZE) + 1;
        } while (!isCellValid(rowNumber, colNumber));
        map[rowNumber - 1][colNumber - 1] = DOT_0;
    }

    private static boolean isCellValid(int rowNumber, int colNumber) {
        if (rowNumber < 1 || rowNumber > SIZE)
            return false;
        if (colNumber < 1 || colNumber > SIZE)
            return false;
        return map[rowNumber - 1][colNumber - 1] == DOT_EMPTY;
    }

    private static void printMap() {
        printMapHeader();
        printMapRows();
    }

    private static void printMapRows() {
        for (int i = 0; i < SIZE; i++) {
            printColumnNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printColumnNumber(int i) {
        int columnNumber = i + 1;
        System.out.print(columnNumber + " ");
    }

    private static void printMapHeader() {
        printEmptyHeaderForFirstColumn();
        for (int i = 0; i < SIZE; i++) {
            printColumnNumber(i);
        }

        System.out.println();
    }

    private static void printEmptyHeaderForFirstColumn() {
        System.out.print("  ");
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
}


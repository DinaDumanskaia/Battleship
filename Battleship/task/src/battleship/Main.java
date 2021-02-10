package battleship;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final int FOG_OF_WAR = Integer.MAX_VALUE;
    public static final int SPACE = Integer.MIN_VALUE;
    public static final int[] LET = new int[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74};
    public static final int X_HIT_AMOUNT = 17;
    private static final int O_SHIP = 11;
    private static final int X_SHIP = 22;
    private static final int M_SHIP = 33;

    public static void main(String[] args) throws Exception {
        int[][] battleField = createTheBattleField();
        int[][] secondBattleField = createTheBattleField();

        System.out.println("Player 1, place your ships on the game field");
        System.out.println();
        printField(battleField);
        placeShipsOnField(battleField);
        System.out.println("Player 2, place your ships to the game field");
        System.out.println();
        printField(secondBattleField);
        placeShipsOnField(secondBattleField);

        makeOneShot(battleField, secondBattleField);
        //printField(/*battleField*/mockField); // dEm change
    }

    private static void makeOneShot(int[][] battleField, int[][] secondBattleField) throws NumberFormatException {
        int firstHitCounter = 0;
        int secondHitCounter = 0;
        while (firstHitCounter != X_HIT_AMOUNT || secondHitCounter != X_HIT_AMOUNT) {
            printTwoFields(battleField, secondBattleField);
            System.out.println("Player 1, it's your turn:");
            System.out.println();
            firstHitCounter = shoot(secondBattleField, firstHitCounter);
            printTwoFields(secondBattleField, battleField);
            System.out.println("Player 2, it's your turn:");
            System.out.println();
            secondHitCounter = shoot(battleField, secondHitCounter);
        }
//        System.out.println("The game starts!");
//        System.out.println();
//        printField(battleField, FieldStatus.COVERED);
//        System.out.println("Take a shot!");
//        System.out.println();
//        shoot(battleField, firstHitCounter);
    }

    private static int shoot(int[][] battleField, int hitCounter) {

            String shot;
            boolean coordinateIsOk;
            int xShot;
            int yShot;
            do {
                shot = new Scanner(System.in).nextLine();
                System.out.println();
                xShot = shot.charAt(0); // todo: здесь нужно сразу привести к координате 1-10
                yShot = Integer.parseInt(shot.substring(1));
                coordinateIsOk = checkCoordinateForShot(xShot, yShot);
            } while (!coordinateIsOk);

            if (checkCellForShot(xShot, yShot, battleField, hitCounter)) {
                hitCounter++;
            }
        return hitCounter;
    }

    private static void printTwoFields(int[][] battleField, int[][] secondBattleField) {
        printField(secondBattleField,FieldStatus.COVERED);
        printField(battleField);
    }

    private static boolean checkCoordinateForShot(int xShot, int yShot) {
        boolean isOk = true;
        if (!(xShot >= 65 && xShot <= 74 && yShot >= 1 && yShot <= 10)) {
            isOk = false;
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            System.out.println();
        }
        return isOk;
    }

    private static boolean checkCellForShot(int xShot, int yShot, int[][] battleField, int hitCounter) {
        boolean isXHit = false;
        if (battleField[xShot - 64][yShot] == O_SHIP) {
            isXHit = true;
            battleField[xShot - 64][yShot] = X_SHIP;
            printField(battleField, FieldStatus.COVERED);
            System.out.println();
            if (checkSurrounding(xShot, yShot, battleField)) {
                if (hitCounter < X_HIT_AMOUNT - 1) {
                    System.out.println("You sank a ship!");
                } else {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    return true;
                }
            } else {
                System.out.println("You hit a ship!");
            }

        } else if (battleField[xShot - 64][yShot] == FOG_OF_WAR || battleField[xShot - 64][yShot] == M_SHIP) {
            battleField[xShot - 64][yShot] = M_SHIP;
            printField(battleField, FieldStatus.COVERED);
            System.out.println("You missed!");
        } else {
            printField(battleField, FieldStatus.COVERED);
            System.out.println("You hit a ship!");
        }
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        pressEnter();
        return isXHit;
    }

    private static void placeShipsOnField(int[][] battleField) throws Exception {
        List<BattleShip> battleShips = new ArrayList<>();
        battleShips.add(new BattleShip(5, "Aircraft Carrier"));
        battleShips.add(new BattleShip(4, "Battleship"));
        battleShips.add(new BattleShip(3, "Submarine"));
        battleShips.add(new BattleShip(3, "Cruiser"));
        battleShips.add(new BattleShip(2, "Destroyer"));
        boolean isSaved;
        String userInput;
        for (BattleShip currentShip : battleShips) {
            System.out.println("Enter the coordinates of the " + currentShip.getName() + " (" + currentShip.getLength() + " cells):");
            System.out.println();
            do {
                userInput = new Scanner(System.in).nextLine();

                System.out.println();
                isSaved = saveShipCoordinates(userInput, currentShip, battleField);
                if (isSaved) {
                    addShipInBattleField(battleField, readAndSaveUsersInput(userInput));
                    printField(battleField);
                }
            } while(!isSaved);
        }
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        pressEnter();
    }

    private static void pressEnter() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!input.equals("\n")) {
            System.out.println("Error! Press Enter and pass the move to another player");
            System.out.println("...");
        }
    }

    private static void addShipInBattleField(int[][] battleField, List<Integer> readAndSaveUsersInput) {
        int x1;
        int x2;
        int y1;
        int y2;
        int X;
        int Y;
        if (readAndSaveUsersInput.get(0).equals(readAndSaveUsersInput.get(2))) {
            y1 = readAndSaveUsersInput.get(1);
            y2 = readAndSaveUsersInput.get(3);
            X = readAndSaveUsersInput.get(0) - 64;
            if (y1 < y2) {
                for (int i = y1; i <= y2; i++) {
                    battleField[X][i] = O_SHIP;
                }
            } else {
                for (int i = y2; i <= y1; i++) {
                    battleField[X][i] = O_SHIP;
                }
            }
        } else if (readAndSaveUsersInput.get(1).equals(readAndSaveUsersInput.get(3))) {
            x1 = readAndSaveUsersInput.get(0) - 64;
            x2 = readAndSaveUsersInput.get(2) - 64;
            Y = readAndSaveUsersInput.get(1);
            if (x1 < x2) {
                for (int i = x1; i <= x2; i++) {
                    battleField[i][Y] = O_SHIP;
                }
            } else {
                for (int i = x2; i <= x1; i++) {
                    battleField[i][Y] = O_SHIP;
                }
            }
        }
    }

    private static boolean saveShipCoordinates(String userInput, BattleShip currentShip, int[][] battleField) throws Exception {
        boolean isSaved = true;
        try {
            int forCheck;
            //получаем список координат в числовом эквиваленте
            List<Integer> numeralCoordinates = readAndSaveUsersInput(userInput);
            forCheck = checkIfVerticalAndHorizontal(numeralCoordinates);
            if (checkError(forCheck)) {
                forCheck = checkLength(numeralCoordinates, currentShip);
                if (checkError(forCheck)) {
                    forCheck = checkSurrounding(numeralCoordinates, battleField);
                    if (!checkError(forCheck)) {
                        printError(forCheck, currentShip);
                        isSaved = false;
                    }
                } else {
                    printError(forCheck, currentShip);
                    isSaved = false;
                }
            } else {
                printError(forCheck, currentShip);
                isSaved = false;
            }
        } catch (Exception a) {
            isSaved = false;
        }
        return isSaved;
    }

    private static void printError(int checkFreeLocation, BattleShip currentShip) {
        if (checkFreeLocation == 1) {
            System.out.println("Error! Wrong ship location! Try again:");
            System.out.println();
        } else if (checkFreeLocation == 2) {
            System.out.println("Error! Wrong length of the " + currentShip.getName() + "! Try again:");
            System.out.println();
        } else if (checkFreeLocation == 3) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            System.out.println();
        }
    }

    private static boolean checkError(int checkIfVerticalAndHorizontal) {
        return checkIfVerticalAndHorizontal == 0;
    }

    private static boolean checkSurrounding(int x, int y, int[][] battleField) {
        int x0 = x - 1;
        int y0 = y - 1;
        int x1 = x + 1;
        int y1 = y + 1;

        boolean isShipDead = true;

        for (int i = y0; i <= y1; i++) {
            isShipDead = checkColumn(i, x0, x1, battleField);
            if(!isShipDead) {
                break;
            }
        }
        return isShipDead;
    }

    private static int checkSurrounding(List<Integer> numeralCoordinates, int[][] battleField) {
        int isCorrect = 3;
        //система координат: ось Х - буквы в столбик, ось Y - цифры в строчку
        int x0;
        int y0;
        int x1;
        int y1;

        boolean result = true;
        if (numeralCoordinates.get(0) < numeralCoordinates.get(2) || numeralCoordinates.get(1) < numeralCoordinates.get(3)) {
            x0 = numeralCoordinates.get(0) - 1;
            y0 = numeralCoordinates.get(1) - 1;
            x1 = numeralCoordinates.get(2) + 1;
            y1 = numeralCoordinates.get(3) + 1;
        } else {
            x0 = numeralCoordinates.get(2) - 1;
            y0 = numeralCoordinates.get(3) - 1;
            x1 = numeralCoordinates.get(0) + 1;
            y1 = numeralCoordinates.get(1) + 1;
        }

        for (int i = y0; i <= y1; i++) {
            result = checkColumn(i, x0, x1, battleField);
            if(!result) {
                break;
            }
        }

        if (result) {
            isCorrect = 0;
        }
        return isCorrect;
    }

    private static boolean checkColumn(int y, int x0, int x1, int[][] battleField) throws IndexOutOfBoundsException {
        for (int o = x0 - 65; o <= x1 - 64; o++) {
            try {
                if (battleField[o][y] == O_SHIP) {
                    return false;
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        return true;
    }

    private static int checkLength(List<Integer> coordinates, BattleShip currentShip) {
        int isCorrect = 2;
        if (coordinates.get(0).equals(coordinates.get(2)) && (Math.abs(coordinates.get(1) - coordinates.get(3)) + 1) == currentShip.getLength()) {
            isCorrect = 0;
        } else if (((Math.abs(coordinates.get(0) - coordinates.get(2)) + 1) == currentShip.getLength())) {
            isCorrect = 0;
        }
        return isCorrect;
    }

    private static int checkIfVerticalAndHorizontal(List<Integer> coordinates) {
        int isCorrect = 1;
        if (coordinates.get(0).equals(coordinates.get(2)) || coordinates.get(1).equals(coordinates.get(3))) {
            isCorrect = 0;
        }
        return isCorrect;
    }

    private static List<Integer> readAndSaveUsersInput(String userInput) {
        String[] coordinates = userInput.split(" ");
        List<Integer> list = new ArrayList<>();
        if (coordinates.length > 0 && coordinates.length <= 7) {
            try {
                list.add((int)(coordinates[0].charAt(0)));
                list.add(Integer.parseInt(coordinates[0].substring(1)));
                list.add((int)(coordinates[1].charAt(0)));
                list.add(Integer.parseInt(coordinates[1].substring(1)));
            } catch (NumberFormatException e) {
                System.out.println("Error! Wrong coordinates! Try again:");
                System.out.println();
            }
        }
        return list;
    }
    private static void printField(int[][] battleField) {
        printField(battleField, FieldStatus.OPEN);
    }

    private static void printField(int[][] battleField, FieldStatus status) {
        for (int[] chars : battleField) { // extract line by line
            for (int x = 0; x < chars.length; x++) { // get and print letter from the line
                if (x == 0 && chars[x] != SPACE) {
                    printRowLetter(chars[x]);
                } else if (x == 0 && chars[x] == SPACE){
                    System.out.print("  ");
                // if it last cell print it without trailing space symbol
                } else if (x == chars.length - 1) {
                    printCell(chars[x], status);
                } else {
                    printCellWithSpace(chars[x], status);
                }
            }
            System.out.println();

        }
        if (status == FieldStatus.COVERED) {
            System.out.println("---------------------");
        } else {
            System.out.println();
        }
    }

    private static void printRowLetter(int letter) {
        System.out.print((char) letter);
        System.out.print(' ');
    }

    private static int[][] createTheBattleField() {
        int[][] field = new int[11][11];
        field[0][0] = SPACE;
        for (int i = 1; i < 11; i++) {
            field[i][0] = LET[i - 1]; // set letters charcodes to array cell
        }
        for (int i = 1; i < 11; i++) {
            field[0][i] = i;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[1][x] = FOG_OF_WAR;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[2][x] = FOG_OF_WAR;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[3][x] = FOG_OF_WAR;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[4][x] = FOG_OF_WAR;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[5][x] = FOG_OF_WAR;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[6][x] = FOG_OF_WAR;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[7][x] = FOG_OF_WAR;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[8][x] = FOG_OF_WAR;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[9][x] = FOG_OF_WAR;
        }
        for (int x = 1; x < field[0].length; x++) {
            field[10][x] = FOG_OF_WAR;
        }
        return field;
    }

    private static void printCell(int cell) {
        printCell(cell, FieldStatus.OPEN);
    }
    private static void printCell(int cell, FieldStatus status) {
        if (cell == FOG_OF_WAR) {
            System.out.print("~");
        } else if (cell == O_SHIP) {
            String symbol = (status == FieldStatus.COVERED) ? "~" : "O";
            System.out.print(symbol);
        } else if (cell == X_SHIP) {
            System.out.print("X");
        } else if (cell == M_SHIP) {
            System.out.print("M");
        } else {
            System.out.print(cell);
        }
    }

/*    private static void printCellWithSpace(int cell) {
        printCellWithSpace(cell, FieldStatus.OPEN);
    }*/

    private static void printCellWithSpace(int cell, FieldStatus status) {
        printCell(cell, status);
        System.out.print(' ');
    }
}

class BattleShip {
    private final int length;
    private final String name;

    BattleShip(int length, String name) {
        this.length = length;
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }
}

enum FieldStatus {
    COVERED, OPEN;
}
package tictactoe;

import java.util.Scanner;

public class Main {
    private final static char[][] stepsDone = new char[3][3];
    private final static Scanner scanner = new Scanner(System.in);

    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        // write your code here
        String steps = "_________";
        steps = steps.replace("_", " ");
        fillGrid(steps);
        showGrid();
        nextMove();
    }

    private static void nextMove() {

        boolean repeat = true;

        while (repeat) {
            String coordinates = scanner.nextLine().replace(" ", "");

            if (coordinates.matches("\\d+")) {

                int coordinateA = Integer.parseInt(coordinates.substring(0, 1));
                int coordinateB = Integer.parseInt(coordinates.substring(1));

                if (coordinateA < 1 || coordinateA > 3 ||
                        coordinateB < 1 || coordinateB > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {

                    if (stepsDone[coordinateA - 1][coordinateB - 1] == ' ') {
                        stepsDone[coordinateA - 1][coordinateB - 1] = currentPlayer;

                        currentPlayer = (currentPlayer == 'X'? 'O': 'X');

                        showGrid();

                        if (verifyState()) {
                            repeat = false;
                        }

                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }

            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }

    private static void showGrid() {

        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", stepsDone[0][0], stepsDone[0][1], stepsDone[0][2]);
        System.out.printf("| %c %c %c |%n", stepsDone[1][0], stepsDone[1][1], stepsDone[1][2]);
        System.out.printf("| %c %c %c |%n", stepsDone[2][0], stepsDone[2][1], stepsDone[2][2]);
        System.out.println("---------");

    }

    private static void fillGrid(String steps) {
        int counter = 0;

        for (int i = 0; i < stepsDone.length; i++) {
            for (int j = 0; j < stepsDone[i].length; j++) {
                stepsDone[i][j] = steps.charAt(counter++);
            }
        }
    }

    private static boolean verifyState() {

        if (isWinner('X')) {
            System.out.println("X wins");
            return true;
        } else if (isWinner('O')) {
            System.out.println("O wins");
            return true;
        } else if (isTheGameCompleted()) {
            System.out.println("Draw");
            return true;
        } else {
            return false;
        }

    }

    public static boolean isWinner(char player) {
        boolean win = false;

        if (stepsDone[0][0] == player && stepsDone[1][1] == player && stepsDone[2][2] == player) {
            win = true;
        } else if (stepsDone[0][0] == player && stepsDone[1][0] == player && stepsDone[2][0] == player) {
            win = true;
        } else if (stepsDone[0][0] == player && stepsDone[0][1] == player && stepsDone[0][2] == player) {
            win = true;
        } else if (stepsDone[0][2] == player && stepsDone[1][1] == player && stepsDone[2][0] == player) {
            win = true;
        } else if (stepsDone[0][2] == player && stepsDone[1][2] == player && stepsDone[2][2] == player) {
            win = true;
        } else if (stepsDone[1][0] == player && stepsDone[1][1] == player && stepsDone[1][2] == player) {
            win = true;
        } else if (stepsDone[2][0] == player && stepsDone[2][1] == player && stepsDone[2][2] == player) {
            win = true;
        } else if (stepsDone[0][1] == player && stepsDone[1][1] == player && stepsDone[2][1] == player) {
            win = true;
        }

        return win;
    }

    public static boolean isTheGameCompleted() {
        boolean result = true;

        for (char[] array: stepsDone) {
            for (char step : array) {
                if (step == ' ') {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }
}

package maradamark99.ketszemelyes;

import java.util.Map;
import java.util.Scanner;

import maradamark99.egyszemelyes.FourDirection;
import maradamark99.egyszemelyes.maze.CellPosition;

public class Main {
    public static void main(String[] args) {
        var gameState = new State();
        var ai = new TrialAndError(gameState);
        System.out.println(gameState);
        Scanner scanner = new Scanner(System.in);
        CellPosition newPlayerCellPosition;
        CellPosition newAiCellPosition;
        final Map<String, FourDirection> directionsMap = Map.of(
                "w", FourDirection.UP,
                "a", FourDirection.LEFT,
                "s", FourDirection.DOWN,
                "d", FourDirection.RIGHT);
        while (true) {
            System.out.println("Blue turn:\n");
            Operator op;
            int rowIndex = 0;
            int columnIndex = 0;
            FourDirection direction;
            do {
                String directionInput = "";
                do {
                    System.out.println("Please provide me a row index: ");
                    if (scanner.hasNextInt()) {
                        rowIndex = scanner.nextInt();
                    }
                    System.out.println("Please provide me a column index: ");
                    if (scanner.hasNextInt()) {
                        columnIndex = scanner.nextInt();
                    }
                    System.out.println("Please provide me a direction (w, a, s, d): ");
                    if (scanner.hasNext()) {
                        directionInput = scanner.next();
                    }
                } while (!directionsMap.containsKey(directionInput));
                direction = directionsMap.get(directionInput);
                op = new Operator(new CellPosition(rowIndex, columnIndex), Disc.BLUE, direction);
            } while (!op.isExistingState(gameState));
            newPlayerCellPosition = new CellPosition(rowIndex + direction.getPosition().getRow(),
                    columnIndex + direction.getPosition().getColumn());
            gameState = op.applyState(gameState);
            System.out.println(gameState);
            if (gameState.isTargetState(newPlayerCellPosition, Disc.BLUE)) {
                System.out.println("Blue won!");
                break;
            }
            System.out.println("Red turn:\n");
            newAiCellPosition = ai.getMove(gameState);
            System.out.println(ai.getState());
            if (gameState.isTargetState(newAiCellPosition, Disc.RED)) {
                System.out.println("Red won!");
                break;
            }
        }
        scanner.close();
    }
}

package maradamark99.egyszemelyes.solvers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import maradamark99.egyszemelyes.FourDirection;
import maradamark99.egyszemelyes.Operator;
import maradamark99.egyszemelyes.State;
import maradamark99.egyszemelyes.util.ConsoleUtil;

public class ManualSolver extends Solver {

    private final Map<String, FourDirection> directionsMap;

    public ManualSolver(State state) {
        super(state);
        this.directionsMap = new LinkedHashMap<String, FourDirection>() {
            {
                put("w", FourDirection.UP);
                put("a", FourDirection.LEFT);
                put("s", FourDirection.DOWN);
                put("d", FourDirection.RIGHT);
            }
        };
    }

    @Override
    public void solve() {
        var mazeAreaLength = state.getMaze().getMazeArea().length;
        var stepsAvailable = mazeAreaLength * 4;
        var stepsLeft = stepsAvailable;
        var scanner = new Scanner(System.in);
        while (stepsLeft > 0) {
            ConsoleUtil.clearConsole();
            System.out.println(state);
            System.out.println("Steps left: " + stepsLeft);
            var key = getDirectionFromUser(scanner);
            var operator = new Operator(directionsMap.get(key));
            if (operator.isExistingState(state)) {
                state = operator.applyState(state);
                if (state.isTargetState()) {
                    System.out.println("Congratulations, you have successfully solved the maze in " + (stepsAvailable
                            - stepsLeft) + " steps!");
                    return;
                }
            }
            stepsLeft--;
        }
        System.out.println("You have failed to solve the maze!");
        scanner.close();
    }

    private String getDirectionFromUser(Scanner scanner) {
        String key = "";
        do {
            askForDirection();
            if (scanner.hasNext()) {
                key = scanner.next();
            }
        } while (!directionsMap.containsKey(key));
        return key;
    }

    private void askForDirection() {
        System.out.print("\nPlease provide me a direction ( ");
        directionsMap.entrySet()
                .forEach((entry) -> System.out.print(entry.getKey() + " = " + entry.getValue().name() + " "));
        System.out.print("): ");
    }

}

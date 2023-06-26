package maradamark99.ketszemelyes;

import java.util.Random;

import maradamark99.egyszemelyes.maze.CellPosition;

public class TrialAndError extends Solver {

    public TrialAndError(State state) {
        super(state);
    }

    @Override
    CellPosition getMove(State stateParam) {
        Random rnd = new Random();
        while (true) {
            var operatorIndex = rnd.nextInt(0, getOperators().size());
            var currentOperator = getOperators().get(operatorIndex);
            if (currentOperator.isExistingState(stateParam)) {
                state = currentOperator.applyState(stateParam);
                return currentOperator.getPosition();
            }
        }
    }

}

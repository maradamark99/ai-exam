package maradamark99.egyszemelyes.solvers;

import java.util.Random;

import maradamark99.egyszemelyes.State;

public class TrialAndError extends Solver {

    public TrialAndError(State state) {
        super(state);
    }

    @Override
    public void solve() {
        Random random = new Random();
        System.out.println(state);
        for (int i = 0; i < 1000; i++) {
            var operatorIndex = random.nextInt(getOperators().size());
            var currentOperator = getOperators().get(operatorIndex);
            if (currentOperator.isExistingState(state)) {
                state = currentOperator.applyState(state);
                System.out.println(state);
                if (state.isTargetState()) {
                    System.out.println("Target reached");
                    return;
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Solver failed");
    }

}

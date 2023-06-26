package maradamark99.ketszemelyes;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import maradamark99.egyszemelyes.FourDirection;
import maradamark99.egyszemelyes.maze.CellPosition;

@Getter
public abstract class Solver {
    private List<Operator> operators;
    protected State state;

    public Solver(State state) {
        operators = new ArrayList<Operator>();
        this.state = state;
        generateOperators();
    }

    private void generateOperators() {
        for (int i = 0; i < state.getBoard().length; i++) {
            for (int j = 0; j < state.getBoard()[0].length; j++) {
                for (var direction : FourDirection.values())
                    operators.add(new Operator(new CellPosition(i, j), Disc.RED, direction));
            }
        }
    }

    abstract CellPosition getMove(State state);
}

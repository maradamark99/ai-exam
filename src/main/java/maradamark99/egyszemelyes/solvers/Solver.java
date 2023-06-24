package maradamark99.egyszemelyes.solvers;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import maradamark99.egyszemelyes.Direction;
import maradamark99.egyszemelyes.Operator;
import maradamark99.egyszemelyes.State;

@Getter
@Setter
public abstract class Solver {
    private final List<Operator> operators;
    protected State state;

    public Solver(State state) {
        this.state = state;
        this.operators = Arrays
                .stream(Direction.values())
                .map(Operator::new)
                .toList();
    }

    public abstract void solve();
}

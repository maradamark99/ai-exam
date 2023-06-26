package maradamark99.ketszemelyes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import maradamark99.egyszemelyes.FourDirection;
import maradamark99.egyszemelyes.maze.CellPosition;

@Getter
@RequiredArgsConstructor
public class Operator {
    private final CellPosition position;
    private final Disc disc;
    private final FourDirection direction;

    public boolean isExistingState(State state) {
        var newPositon = new CellPosition(position.getRow() + direction.getPosition().getRow(),
                position.getColumn() + direction.getPosition().getColumn());
        return newPositon.getRow() >= 0
                && newPositon.getRow() < state.getBoard().length
                && newPositon.getColumn() >= 0
                && newPositon.getColumn() < state.getBoard()[0].length
                && state.getBoard()[position.getRow()][position.getColumn()] == disc
                && state.getBoard()[newPositon.getRow()][newPositon.getColumn()] == null;
    }

    public State applyState(State state) {
        var newState = new State(state);
        var newPosition = new CellPosition(position.getRow() + direction.getPosition().getRow(),
                position.getColumn() + direction.getPosition().getColumn());
        newState.getBoard()[position.getRow()][position.getColumn()] = null;
        newState.getBoard()[newPosition.getRow()][newPosition.getColumn()] = disc;
        return newState;
    }
}

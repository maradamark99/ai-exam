package maradamark99.egyszemelyes;

import lombok.AllArgsConstructor;
import maradamark99.egyszemelyes.maze.CellPosition;
import maradamark99.egyszemelyes.maze.MazeCell;

@AllArgsConstructor
public class Operator {

    private Direction direction;

    public boolean isExistingState(State state) {
        var stateCurrRow = state.getCurrentPosition().getRow();
        var stateCurrCol = state.getCurrentPosition().getColumn();
        var stateMazeArea = state.getMaze().getMazeArea();
        var movementRow = direction.getPosition().getRow();
        var movementCol = direction.getPosition().getColumn();
        var currMazeCell = stateMazeArea[stateCurrRow][stateCurrCol];
        return isInsideBoundary(stateCurrRow, stateCurrCol, movementRow, movementCol, stateMazeArea)
                && isFreeDirection(currMazeCell);
    }

    private boolean isInsideBoundary(int stateCurrRow, int stateCurrCol, int movementRow, int movementCol,
            MazeCell[][] stateMazeArea) {
        return stateCurrRow + movementRow >= 0
                && stateCurrRow + movementRow < stateMazeArea.length
                && stateCurrCol + movementCol >= 0
                && stateCurrCol + movementCol < stateMazeArea[0].length;
    }

    private boolean isFreeDirection(MazeCell cell) {
        switch (direction) {
            case UP: {
                return !cell.isTop();
            }
            case DOWN: {
                return !cell.isBottom();
            }
            case LEFT: {
                return !cell.isLeft();
            }
            case RIGHT: {
                return !cell.isRight();
            }
            default:
                return true;
        }
    }

    public State applyState(State state) {
        var newState = new State(state);
        var newPosition = new CellPosition(
                newState.getCurrentPosition().getRow()
                        + direction.getPosition().getRow(),
                newState.getCurrentPosition().getColumn()
                        + direction.getPosition().getColumn());
        newState.setCurrentPosition(newPosition);
        return newState;
    }
}

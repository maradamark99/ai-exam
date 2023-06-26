package maradamark99.ketszemelyes;

import java.util.Arrays;
import java.util.stream.IntStream;

import lombok.Getter;
import maradamark99.egyszemelyes.maze.CellPosition;

@Getter
public class State {

    private Disc[][] board;

    public State() {
        this.board = new Disc[][] {
                { Disc.BLUE, Disc.RED, Disc.BLUE, Disc.RED },
                { null, null, null, null },
                { null, null, null, null },
                { null, null, null, null },
                { Disc.RED, Disc.BLUE, Disc.RED, Disc.BLUE }
        };
    }

    public State(Disc[][] board) {
        this.board = board;
    }

    public State(State state) {
        this(Arrays.stream(state.board)
                .map(Disc[]::clone)
                .toArray(Disc[][]::new));
    }

    public boolean isTargetState(CellPosition position, Disc disc) {
        var directions = EightDirection.values();
        return checkDirectNeighbors(position, disc) ||
                Arrays
                        .stream(directions)
                        .map((direction) -> checkNeighbors(direction, position, disc))
                        .anyMatch((count) -> count >= 3);
    }

    private boolean checkDirectNeighbors(CellPosition position, Disc disc) {
        return doCheckDirectNeighbors(position, disc, EightDirection.UP, EightDirection.DOWN)
                || doCheckDirectNeighbors(position, disc, EightDirection.LEFT, EightDirection.RIGHT)
                || doCheckDirectNeighbors(position, disc, EightDirection.DOWN_LEFT, EightDirection.UP_RIGHT)
                || doCheckDirectNeighbors(position, disc, EightDirection.DOWN_RIGHT, EightDirection.UP_LEFT);
    }

    private boolean doCheckDirectNeighbors(CellPosition position, Disc disc, EightDirection direction,
            EightDirection otherDirection) {
        var directionPosition = new CellPosition(position.getRow() + direction.getPosition().getRow(),
                position.getColumn() + direction.getPosition().getColumn());
        var otherDirectionPosition = new CellPosition(position.getRow() + otherDirection.getPosition().getRow(),
                position.getColumn() + otherDirection.getPosition().getColumn());
        if (!isValidPosition(directionPosition) || !isValidPosition(otherDirectionPosition)) {
            return false;
        }
        return board[directionPosition.getRow()][directionPosition.getColumn()] == disc
                && board[otherDirectionPosition.getRow()][otherDirectionPosition.getColumn()] == disc;
    }

    private int checkNeighbors(EightDirection direction, CellPosition position, Disc disc) {
        if (!isValidPosition(position) || disc != board[position.getRow()][position.getColumn()]) {
            return 0;
        }

        CellPosition nextPosition = getNextPosition(position, direction);

        return 1 + checkNeighbors(direction, nextPosition, disc);
    }

    private boolean isValidPosition(CellPosition position) {
        int row = position.getRow();
        int col = position.getColumn();
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    private CellPosition getNextPosition(CellPosition currentPosition, EightDirection direction) {
        return new CellPosition(currentPosition.getRow() + direction.getPosition().getRow(),
                currentPosition.getColumn() + direction.getPosition().getColumn());
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("  ");
        IntStream.range(0, board[0].length).forEach((i) -> sb.append(i + " "));
        sb.append("\n");
        for (int i = 0; i < board.length; i++) {
            sb.append(i + " ");
            for (int j = 0; j < board[0].length; j++) {
                sb.append((board[i][j] == null ? "-" : board[i][j] == Disc.BLUE ? "B" : "R") + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}

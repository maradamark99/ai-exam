package maradamark99.egyszemelyes;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import maradamark99.egyszemelyes.maze.CellPosition;
import maradamark99.egyszemelyes.maze.Maze;

@Getter
@AllArgsConstructor
public class State {

    private Maze maze;

    private CellPosition currentPosition;

    public State(Maze maze) {
        this.maze = maze;
        currentPosition = new CellPosition(maze.getEntryPosition());
    }

    public State(State state) {
        this(new Maze(state.maze), state.currentPosition);
    }

    public void setCurrentPosition(CellPosition position) {
        this.currentPosition = position;
        var mazeCell = maze.getMazeArea()[position.getRow()][position.getColumn()];
        mazeCell.setContent(maze.getCurrent());
    }

    public boolean isTargetState() {
        return currentPosition.equals(maze.getExitPosition());
    }

    @Override
    public String toString() {
        return maze.toString();
    }

    @Override
    public boolean equals(Object arg0) {
        if (!(arg0 instanceof State other)) {
            return false;
        }
        boolean equalCellPositions = this.currentPosition.equals(other.getCurrentPosition());
        boolean equalMazes = this.maze.equals(other.getMaze());
        return equalCellPositions && equalMazes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maze, currentPosition);
    }

}

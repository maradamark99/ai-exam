package maradamark99.egyszemelyes.maze;

import java.util.Arrays;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
public class Maze {
    private final char current;
    private final char entry;
    private final char exit;
    private final CellPosition entryPosition;
    private final CellPosition exitPosition;
    private final MazeCell[][] mazeArea;

    public Maze(char entry, char exit, char current, MazeCell[][] mazeArea, CellPosition entryPosition,
            CellPosition exitPosition) {
        this.entry = entry;
        this.exit = exit;
        this.current = current;
        this.entryPosition = entryPosition;
        this.exitPosition = exitPosition;
        this.mazeArea = mazeArea;
        this.mazeArea[entryPosition.getRow()][entryPosition.getColumn()].setContent(entry);
        this.mazeArea[exitPosition.getRow()][exitPosition.getColumn()].setContent(exit);
    }

    public Maze(Maze maze) {
        this(maze.entry, maze.exit, maze.current,
                Arrays.stream(maze.getMazeArea())
                        .map(MazeCell[]::clone)
                        .toArray(MazeCell[][]::new),
                maze.entryPosition,
                maze.exitPosition);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Draw the top wall of the maze
        sb.append("+---".repeat(mazeArea[0].length));
        sb.append("+\n");

        // Draw the maze cells row by row
        for (int row = 0; row < mazeArea.length; row++) {
            // Draw the left wall of the row
            sb.append("|");

            // Draw the content and right wall of each cell
            for (int col = 0; col < mazeArea[row].length; col++) {
                MazeCell cell = mazeArea[row][col];
                sb.append(" ").append(cell.getContent()).append(" ");
                sb.append(cell.isRight() ? "|" : " ");
            }

            sb.append("\n");

            if (row < mazeArea.length - 1) {
                // Draw the horizontal walls between rows
                for (int col = 0; col < mazeArea[row].length; col++) {
                    var cell = mazeArea[row][col];
                    sb.append("+");
                    sb.append(cell.isBottom() ? "---" : "   ");
                }

                sb.append("+\n");
            } else {
                // Draw the bottom wall of the maze
                sb.append("+---".repeat(mazeArea[row].length));
                sb.append("+\n");
            }
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object arg0) {
        if (!(arg0 instanceof Maze other)) {
            return false;
        }
        for (int i = 0; i < this.getMazeArea().length; i++) {
            for (int j = 0; j < this.getMazeArea()[0].length; j++) {
                if (this.getMazeArea()[i][j] != other.getMazeArea()[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + entry;
        result = 31 * result + exit;
        result = 31 * result + current;
        result = 31 * result + entryPosition.hashCode();
        result = 31 * result + exitPosition.hashCode();
        result = 31 * result + Arrays.deepHashCode(mazeArea);

        return result;
    }

}

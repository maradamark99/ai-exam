package maradamark99.egyszemelyes.maze;

import java.util.List;

public class MazeValidator {

    private MazeValidator() {

    }

    public static boolean isValidMaze(List<List<MazeCell>> mazeArea) {
        return areRowsOfEqualLength(mazeArea) && hasPerimeter(mazeArea);
    }

    private static boolean areRowsOfEqualLength(List<List<MazeCell>> mazeArea) {
        var rowLength = mazeArea.size();
        for (var mazeCells : mazeArea) {
            if (mazeCells.size() != rowLength)
                return false;
        }
        return true;
    }

    private static boolean hasPerimeter(List<List<MazeCell>> mazeArea) {
        for (int row = 0; row < mazeArea.size(); row++) {
            if (!mazeArea.get(row).get(0).isLeft() || !mazeArea.get(row).get(mazeArea.size() - 1).isRight())
                return false;
        }
        for (int col = 0; col < mazeArea.size(); col++) {
            if (!mazeArea.get(0).get(col).isTop() || !mazeArea.get(mazeArea.get(0).size() - 1).get(col).isBottom())
                return false;
        }
        return true;
    }
}

package maradamark99.egyszemelyes;

import java.io.IOException;

import maradamark99.egyszemelyes.maze.CellPosition;
import maradamark99.egyszemelyes.maze.Maze;
import maradamark99.egyszemelyes.maze.MazeParser;
import maradamark99.egyszemelyes.solvers.BreadthFirstSearch;
import maradamark99.egyszemelyes.solvers.Solver;

public class Main {
    public static void main(String[] args) throws IOException {
        var parser = new MazeParser("Maze.txt");
        var mazeArea = parser.parse();
        var maze = new Maze('S', 'E', '.', mazeArea, new CellPosition(0, 0),
                new CellPosition(mazeArea.length - 1, mazeArea[0].length - 1));
        var initalState = new State(maze);
        Solver solver = new BreadthFirstSearch(initalState);
        solver.solve();
    }
}

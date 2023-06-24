package maradamark99.egyszemelyes.maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MazeParser {

    private final Map<String, Wall> allowedCharacters;
    private String fileName;

    public MazeParser(String fileName) {
        this.fileName = fileName;
        allowedCharacters = Map.of("t", Wall.TOP, "r", Wall.RIGHT, "b", Wall.BOTTOM, "l", Wall.LEFT, "n", Wall.NONE);
    }

    public MazeCell[][] parse() throws IOException {
        var mazeArea = new ArrayList<List<MazeCell>>();
        var inputStream = MazeParser.class.getClassLoader().getResourceAsStream(fileName);
        try (var br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<MazeCell> cells = new ArrayList<MazeCell>();
                line = line.toLowerCase();
                for (var word : line.split(" ")) {
                    MazeCell cell = new MazeCell();
                    for (var c : word.split("")) {
                        if (!allowedCharacters.containsKey(c)) {
                            throw new IllegalCharacterInFileException();
                        }
                        cell = setWall(cell, allowedCharacters.get(c));
                    }
                    cells.add(cell);
                }
                mazeArea.add(cells);
            }
        }
        if (!MazeValidator.isValidMaze(mazeArea))
            throw new InvalidMazeFormatException();
        return convertToMatrix(mazeArea);
    }

    private MazeCell setWall(MazeCell mazeCell, Wall wall) {
        switch (wall) {
            case TOP: {
                mazeCell.setTop();
                break;
            }
            case RIGHT: {
                mazeCell.setRight();
                break;
            }
            case BOTTOM: {
                mazeCell.setBottom();
                break;
            }
            case LEFT: {
                mazeCell.setLeft();
                break;
            }
            default:
                break;

        }
        return mazeCell;
    }

    private MazeCell[][] convertToMatrix(List<List<MazeCell>> mazeList) {
        int numRows = mazeList.size();
        int numCols = mazeList.get(0).size();
        MazeCell[][] mazeArea = new MazeCell[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            List<MazeCell> rowList = mazeList.get(i);
            for (int j = 0; j < numCols; j++) {
                mazeArea[i][j] = rowList.get(j);
            }
        }
        return mazeArea;
    }

}

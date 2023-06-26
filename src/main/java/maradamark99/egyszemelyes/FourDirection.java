package maradamark99.egyszemelyes;

import lombok.Getter;
import maradamark99.egyszemelyes.maze.CellPosition;

public enum FourDirection {
    UP(new CellPosition(-1, 0)),
    DOWN(new CellPosition(1, 0)),
    LEFT(new CellPosition(0, -1)),
    RIGHT(new CellPosition(0, 1));

    @Getter
    private CellPosition position;

    private FourDirection(CellPosition position) {
        this.position = position;
    }
}

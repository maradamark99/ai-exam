package maradamark99.ketszemelyes;

import lombok.Getter;
import maradamark99.egyszemelyes.maze.CellPosition;

public enum EightDirection {
    UP(new CellPosition(-1, 0)),
    DOWN(new CellPosition(1, 0)),
    LEFT(new CellPosition(0, -1)),
    RIGHT(new CellPosition(0, 1)),
    UP_LEFT(new CellPosition(-1, 1)),
    UP_RIGHT(new CellPosition(1, 1)),
    DOWN_LEFT(new CellPosition(-1, -1)),
    DOWN_RIGHT(new CellPosition(1, -1));

    @Getter
    private CellPosition position;

    private EightDirection(CellPosition position) {
        this.position = position;
    }
}

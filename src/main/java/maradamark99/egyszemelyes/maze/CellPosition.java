package maradamark99.egyszemelyes.maze;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CellPosition {
    private int row;
    private int column;

    public CellPosition(CellPosition cellPosition) {
        this(cellPosition.row, cellPosition.column);
    }
}

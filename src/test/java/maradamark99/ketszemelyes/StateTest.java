package maradamark99.ketszemelyes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import maradamark99.egyszemelyes.maze.CellPosition;

public class StateTest {

    @Test
    void testIsTargetStateIsTrue_whenCurrentIsLastToBeAddedAndHasThreeOfTheSameInDiagonal() {
        var board = new Disc[][] {
                { Disc.BLUE, Disc.RED, Disc.BLUE, Disc.RED },
                { null, Disc.RED, null, null },
                { Disc.BLUE, null, Disc.BLUE, null },
                { Disc.BLUE, Disc.BLUE, null, Disc.BLUE },
                { Disc.RED, Disc.BLUE, Disc.BLUE, Disc.RED }
        };
        var state = new State(board);
        assertTrue(state.isTargetState(new CellPosition(2, 0), Disc.BLUE));
    }

    @Test
    void testIsTargetState_whenCurrentIsAddedInBetweenTwoOfTheSame() {
        var board = new Disc[][] {
                { Disc.BLUE, Disc.RED, Disc.BLUE, Disc.RED },
                { null, Disc.RED, null, null },
                { Disc.BLUE, null, Disc.BLUE, null },
                { Disc.RED, Disc.BLUE, null, Disc.BLUE },
                { Disc.BLUE, Disc.BLUE, Disc.BLUE, Disc.RED }
        };
        var state = new State(board);
        assertTrue(state.isTargetState(new CellPosition(3, 1), Disc.BLUE));
    }
}

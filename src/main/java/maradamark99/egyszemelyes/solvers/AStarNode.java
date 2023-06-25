package maradamark99.egyszemelyes.solvers;

import maradamark99.egyszemelyes.State;
import maradamark99.egyszemelyes.maze.CellPosition;

public class AStarNode extends Node implements Comparable<AStarNode> {

    private CellPosition goal;

    public AStarNode(State state, CellPosition goal) {
        super(state);
        this.goal = goal;
    }

    public AStarNode(State state, Node parent, CellPosition goal) {
        super(state, parent);
        this.goal = goal;
    }

    public int getH() {
        return Math.abs(this.getState().getCurrentPosition().getRow() - goal.getRow())
                + Math.abs(this.getState().getCurrentPosition().getColumn() - goal.getColumn());
    }

    public int getG() {
        return this.getDepth();
    }

    public int getF() {
        return this.getH() + this.getG();
    }

    @Override
    public int compareTo(AStarNode other) {
        return Integer.compare(this.getF(), other.getF());
    }

}

package maradamark99.egyszemelyes.solvers;

import java.util.Objects;

import lombok.Getter;
import maradamark99.egyszemelyes.State;

@Getter
public class Node {
    private State state;
    private Node parent;
    private int depth;

    public Node(State state) {
        this.state = state;
    }

    public Node(State state, Node parent) {
        this.state = state;
        this.parent = parent;
        if (parent == null) {
            depth = 0;
        } else {
            depth = parent.getDepth() + 1;
        }
    }

    public boolean isTargetNode() {
        return state.isTargetState();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        return Objects.equals(state, other.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return state.toString();
    }

}

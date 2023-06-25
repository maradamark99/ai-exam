package maradamark99.egyszemelyes.solvers;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import lombok.Getter;
import maradamark99.egyszemelyes.State;
import maradamark99.egyszemelyes.maze.CellPosition;

@Getter
public class AStar extends Solver {

    Queue<AStarNode> openNodes;
    Set<AStarNode> closedNodes;
    private final CellPosition goal;
    private Node path;

    public AStar(State state) {
        super(state);
        openNodes = new PriorityQueue<>();
        goal = state.getMaze().getExitPosition();
        openNodes.add(new AStarNode(state, goal));
        closedNodes = new HashSet<>();
    }

    @Override
    public void solve() {
        while (openNodes.size() > 0) {
            var currentNode = openNodes.remove();
            closedNodes.add(currentNode);
            for (var operator : getOperators()) {
                if (operator.isExistingState(currentNode.getState())) {
                    var newState = operator.applyState(currentNode.getState());
                    var newNode = new AStarNode(newState, currentNode, goal);
                    if (newNode.isTargetNode()) {
                        path = newNode;
                        System.out.println(path);
                        System.out.println("Maze successfully solved");
                        System.out.println("path length: " + path.getDepth());
                        return;
                    }
                    if (!openNodes.contains(newNode) && !closedNodes.contains(newNode)) {
                        openNodes.add(newNode);
                    }
                }
            }
        }
    }

}

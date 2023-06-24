package maradamark99.egyszemelyes.solvers;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import maradamark99.egyszemelyes.Node;
import maradamark99.egyszemelyes.State;

public class DepthFirstSearch extends Solver {

    private final Stack<Node> openNodes;
    private final Set<Node> closedNodes;
    private Node path;

    public DepthFirstSearch(State state) {
        super(state);
        openNodes = new Stack<>();
        closedNodes = new HashSet<>();
    }

    @Override
    public void solve() {
        var node = new Node(state);
        openNodes.push(node);
        while (!openNodes.isEmpty()) {
            var currentNode = openNodes.pop();
            closedNodes.add(currentNode);
            for (var operator : getOperators()) {
                if (operator.isExistingState(currentNode.getState())) {
                    var newState = operator.applyState(currentNode.getState());
                    var newNode = new Node(newState, currentNode);
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
        System.out.println("Failed to solve the maze.");

    }

}

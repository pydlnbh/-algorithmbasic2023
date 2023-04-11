package src.class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 三种方式实现图的拓扑排序
public class Code04_TopologySort {
	// solution one
	public static List<Node> sortedTopologySort(Code01_Graph graph) {
		Map<Node, Integer> inMap = new HashMap<>();
		Queue<Node> zeroInQueue = new LinkedList<>();

		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}

		List<Node> result = new ArrayList<>();
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			result.add(cur);

			for (Node node : cur.nexts) {
				inMap.put(node, inMap.get(node) - 1);

				if (inMap.get(node) == 0) {
					zeroInQueue.add(node);
				}
			}
		}

		return result;
	}
	
	// solution two bfs-1
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> inMap = new HashMap<>();
        
        for (DirectedGraphNode node : graph) {
        	inMap.put(node, 0);
        }
        
        for (DirectedGraphNode node : graph) {
        	for (DirectedGraphNode next : node.neighbors) {
        		inMap.put(next, inMap.get(next) + 1);
        	}
        }
        
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode key : inMap.keySet()) {
        	if (inMap.get(key) == 0) {
        		queue.add(key);
        	}
        }
        
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        while (!queue.isEmpty()) {
        	DirectedGraphNode cur = queue.poll();
        	result.add(cur);
        	
        	for (DirectedGraphNode next : cur.neighbors) {
        		inMap.put(next, inMap.get(next) - 1);
        		
        		if (inMap.get(next) == 0) {
        			queue.add(next);
        		}
        	}
        }
        
        return result;
    }
}

class DirectedGraphNode {
	int label;
	List<DirectedGraphNode> neighbors;

	DirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<DirectedGraphNode>();
	}
}
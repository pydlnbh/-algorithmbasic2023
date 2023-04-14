package src.class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

// 实现Dijkstra算法，用加强堆做更好的实现 no negative weight
public class Code07_Dijkstra {
	// solution one
	public static HashMap<Node, Integer> dijkstra1(Node from) {
		HashMap<Node, Integer> distanceMap = new HashMap<>();
		distanceMap.put(from, 0);
		
		HashSet<Node> selectedNodes = new HashSet<>();
		Node minNode = getMinDistanceAndUnSelectedNode(distanceMap, selectedNodes);
		
		while (minNode != null) {
			int distance = distanceMap.get(minNode);
			
			for (Edge edge : minNode.edges) {
				Node toNode = edge.to;
				
				if (!distanceMap.containsKey(toNode)) {
					distanceMap.put(toNode, distance + edge.weight);
				} else {
					distanceMap.put(toNode, 
						Math.min(distanceMap.get(minNode), distance + edge.weight));
				}
			}
			
			selectedNodes.add(minNode);
			minNode = getMinDistanceAndUnSelectedNode(distanceMap, selectedNodes);
		}
		
		return distanceMap;
	}
	
	// method one
	public static Node getMinDistanceAndUnSelectedNode(
			HashMap<Node, Integer> distanceMap,
			HashSet<Node> selectedNodes) {
	    Node minNode = null;
		int minDistance = Integer.MAX_VALUE;
		
		for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
			Node node = entry.getKey();
			int distance = entry.getValue();
			
			if (!selectedNodes.contains(node)
				&& distance < minDistance) {
					minNode = node;
					minDistance = distance;
			}
		}
		
		return minNode;
	}
}

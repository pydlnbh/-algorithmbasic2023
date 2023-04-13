package src.class16;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// 用堆实现Prim算法
public class Code06_Prim {
	// solution
	public static Set<Edge> primMST(Code01_Graph graph) {
		PriorityQueue<Edge> heap = new PriorityQueue<>(new EdgeComparator());
		
		HashSet<Node> nodeSet = new HashSet<>();
		
		Set<Edge> result = new HashSet<>();
		
		for (Node node : graph.nodes.values()) {
			if (!nodeSet.contains(node)) {
				nodeSet.add(node);
				
				for (Edge edge : node.edges) {
					heap.add(edge);
				}
				
				while (!heap.isEmpty()) {
					Edge edge = heap.poll();
					Node toNode = edge.to;
					
					if (!nodeSet.contains(toNode)) {
						nodeSet.add(toNode);
						result.add(edge);
						
						for (Edge nextEdge : toNode.edges) {
							heap.add(nextEdge);
						}
					}
				}
			}
			
//			break;
		}
		
		return result;
	}
	
	// auxiliary method
	public static class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}
	}
}

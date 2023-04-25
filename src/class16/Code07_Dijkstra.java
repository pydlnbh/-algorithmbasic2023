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
						Math.min(distanceMap.get(toNode), distance + edge.weight));
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
	
	// auxiliary structure class
	public static class NodeRecord {
		public Node node;
		public int distance;
		
		public NodeRecord(Node n, int d) {
			node = n;
			distance = d;
		}
	}
	
	// auxiliary class
	public static class NodeHeap {
		public Node[] nodes;
		public HashMap<Node, Integer> indexMap;
		public HashMap<Node, Integer> distanceMap;
		public int size;
		
		public NodeHeap(int size) {
			nodes = new Node[size];
			indexMap = new HashMap<>();
			distanceMap = new HashMap<>();
			size = 0;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		private boolean isEntered(Node node) {
			return indexMap.containsKey(node);
		}
		
		private boolean inHeap(Node node) {
			return isEntered(node) && indexMap.get(node) != -1;
		}
		 
		public void addOrUpdateOrIgnore(Node node, int distance) {
			if (inHeap(node)) {
				distanceMap.put(node, Math.min(distanceMap.get(node), distance));
				heapInsert(indexMap.get(node));
			}
			
			if (!isEntered(node)) {
				nodes[size] = node;
				indexMap.put(node, size);
				distanceMap.put(node, distance);
				heapInsert(size++);
			}
		}
		
		public NodeRecord pop() {
			NodeRecord info = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
			swap(0, size - 1);
			indexMap.put(nodes[size - 1], -1);
			distanceMap.remove(nodes[size - 1]);
			nodes[size - 1] = null;
			heapify(0, --size);
			return info;
		}
		
		public void heapInsert(int index) {
			while (distanceMap.get(nodes[index])  < distanceMap.get(nodes[(index - 1) / 2])) {
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}
		
		public void heapify(int index, int size) {
			int left = index * 2 + 1;
			
			while (left < size) {
				int best = left + 1 < size 
				    && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
				    ? left + 1
				    : left;
				best = distanceMap.get(nodes[index]) < distanceMap.get(nodes[best]) ? index : best;
				
				if (index == best) {
					break;
				}
				
				swap(index, best);
				index = best;
				left = index * 2 + 1;
			}
		}
		
		public void swap(int i, int j) {
			indexMap.put(nodes[i], j);
			indexMap.put(nodes[j], i);
			Node tmp = nodes[i];
			nodes[i] = nodes[j];
			nodes[j] = tmp;
		}
 	}
	
	// solution two
	public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
		NodeHeap nodeHeap = new NodeHeap(size);
		nodeHeap.addOrUpdateOrIgnore(head, 0);
		HashMap<Node, Integer> result = new HashMap<>();
		
		while (!nodeHeap.isEmpty()) {
			NodeRecord info = nodeHeap.pop();
			Node cur = info.node;
			int distance = info.distance;
			
			for (Edge edge : cur.edges) {
				nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
			}
			
			result.put(cur, distance);
		}
		
		return result;
	}
}

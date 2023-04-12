package src.class16;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

// 用并查集实现Kruskal算法
public class Code05_Kruskal {
	// solution
	public static Set<Edge> kruskalMST(Code01_Graph graph) {
		Union u = new Union();
		u.makeSets(graph.nodes.values());
		
		PriorityQueue<Edge> heap = new PriorityQueue<>(new MyComparator());
		for (Edge edge : graph.edges) {
			heap.add(edge);
		}
		
		Set<Edge> result = new HashSet<>();
		
		while (!heap.isEmpty()) {
			Edge cur = heap.poll();
			
			if (!u.isSameSet(cur.from, cur.to)) {
				result.add(cur);
				u.union(cur.from, cur.to);
			}
		}
		
		return result;
 	}
	
	// auxiliary comparator
	public static class MyComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}
	}
	
	// auxiliary class
	public static class Union {
		public HashMap<Node, Node> parent;
		public HashMap<Node, Integer> size;
		
		public Union() {
			parent = new HashMap<>();
			size = new HashMap<>();
		}
		
		public void makeSets(Collection<Node> nodes) {
			parent.clear();
			size.clear();
			
			for (Node node : nodes) {
				parent.put(node, node);
				size.put(node, 1);
			}
		}
		
		public Node find(Node head) {
			Stack<Node> stack = new Stack<>();
			
			while (head != parent.get(head)) {
				stack.push(head);
				head = parent.get(head);
			}
			
			while (!stack.isEmpty()) {
				parent.put(stack.pop(), head);
			}
			
			return head;
		}
		
		public boolean isSameSet(Node a, Node b) {
			return find(a) == find(b);
		}
		
		public void union(Node a, Node b) {
			Node p1 = find(a);
			Node p2 = find(b);
			
			if (p1 != p2) {
				int s1 = size.get(p1);
				int s2 = size.get(p2);
				
				Node big = s1 > s2 ? p1 : p2;
				Node small = big == p1 ? p2 : p1;
				
				parent.put(small, big);
				size.put(big, s1 + s2);
				size.remove(small);
			}
		}
		
		public int size() {
			return size.size();
		}
	}
}

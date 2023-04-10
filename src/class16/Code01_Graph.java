package src.class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 图的数据结构抽象
public class Code01_Graph {
	public Map<Integer, Node> nodes;
	public Set<Edge> edges;
	
	public Code01_Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}

class Node {
	public int value;
	public int in;
	public int out;
	public List<Node> nexts;
	public List<Edge> edges;
	
	public Node(int v) {
		value = v;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}

class Edge {
	public int weight;
	public Node from;
	public Node to;
	
	public Edge(int w, Node f, Node t) {
		weight = w;
		from = f;
		to = t;
	}
}

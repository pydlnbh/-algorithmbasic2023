package src.class16;

import java.util.ArrayList;
import java.util.Comparator;
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
	
	// solution two BFS-1
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
    
    // solution three DFS-1
    public static ArrayList<DirectedGraphNode> topSort1(ArrayList<DirectedGraphNode> graph) {
    	HashMap<DirectedGraphNode, Record1> map = new HashMap<>();
    	for (DirectedGraphNode node : graph) {
    		f1(node, map);
    	}
    	
    	ArrayList<Record1> recordArr = new ArrayList<>();
    	for (Record1 r : map.values()) {
    		recordArr.add(r);
    	}
    	
    	recordArr.sort(new MyComparator1());

    	ArrayList<DirectedGraphNode> result = new ArrayList<>(); 
    	for (Record1 r : recordArr) {
    		result.add(r.node);
    	}
    	
    	return result;
    }
    
    // auxiliary class
    public static class Record1 {
    	public DirectedGraphNode node;
    	public long nodes;
    	
    	public Record1(DirectedGraphNode n, long l) {
    		node = n;
    		nodes = l;
    	}
    }
    
    // auxiliary class
    public static class MyComparator1 implements Comparator<Record1> {
    	@Override
    	public int compare(Record1 o1, Record1 o2) {
    		return o1.nodes == o2.nodes ? 0 : (o2.nodes > o1.nodes ? 1 : -1);
    	}
    }
    
    // recursive method
    public static Record1 f1(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record1> map) {
    	if (map.containsKey(cur)) {
    		return map.get(cur);
    	}
    	
    	long nodes = 0;
    	for (DirectedGraphNode next : cur.neighbors) {
    		nodes += f1(next, map).nodes;
    	}
    	
    	Record1 r = new Record1(cur, nodes + 1);
    	map.put(cur, r);
    	return r;
    }
    
    // solution three DFS-2
    public static ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
    	HashMap<DirectedGraphNode, Info2> map = new HashMap<>();
    	for (DirectedGraphNode cur : graph) {
    		f2(cur, map);
    	}
    	
    	ArrayList<Info2> infoArr = new ArrayList<>();
    	for (Info2 info : map.values()) {
    		infoArr.add(info);
    	}
    	
    	infoArr.sort(new MyComparator2());
    	
    	ArrayList<DirectedGraphNode> result = new ArrayList<>();
    	for (Info2 info : infoArr) {
    		result.add(info.node);
    	}
    	
    	return result;
    }

    // auxiliary class
    public static class Info2 {
    	public DirectedGraphNode node;
    	public int deep;
    	
    	public Info2(DirectedGraphNode n, int d) {
    		node = n;
    		deep = d;
    	}
    }
    
    // auxiliary comparator class
    public static class MyComparator2 implements Comparator<Info2> {
    	@Override
    	public int compare(Info2 o1, Info2 o2) {
    		return o2.deep - o1.deep;
    	}
    }
    
    // recursive method
    public static Info2 f2(DirectedGraphNode node, HashMap<DirectedGraphNode, Info2> map) {
    	if (map.containsKey(node)) {
    		return map.get(node);
    	}
    	
    	int deep = 0;
    	for (DirectedGraphNode cur : node.neighbors) {
    		deep = Math.max(deep, f2(cur, map).deep);
    	}
    	
    	Info2 ans = new Info2(node, deep + 1);
    	map.put(node, ans);
    	return ans;
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
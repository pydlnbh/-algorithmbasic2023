package src.class12;

import java.util.*;

import src.Solution;
import src.Solution.TreeNode;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离
 */
public class Code04_MaxDistance {
	// solution one
	public static int maxDistance1(TreeNode head) {
		if (head == null) {
			return 0;
		}
		ArrayList<TreeNode> arr = getPrelist(head);
		HashMap<TreeNode, TreeNode> parentMap = getParentMap(head);
		int max = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i; j < arr.size(); j++) {
				max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
			}
		}
		return max;
	}

	// solution method one
	public static ArrayList<TreeNode> getPrelist(TreeNode head) {
		ArrayList<TreeNode> arr = new ArrayList<>();
		fillPrelist(head, arr);
		return arr;
	}

	// solution method one 
	public static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	// solution method one
	public static HashMap<TreeNode, TreeNode> getParentMap(TreeNode head) {
		HashMap<TreeNode, TreeNode> map = new HashMap<>();
		map.put(head, null);
		fillParentMap(head, map);
		return map;
	}

	// solution method one
	public static void fillParentMap(TreeNode head, HashMap<TreeNode, TreeNode> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	// solution method one
	public static int distance(HashMap<TreeNode, TreeNode> parentMap, TreeNode o1, TreeNode o2) {
		HashSet<TreeNode> o1Set = new HashSet<>();
		TreeNode cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		TreeNode lowestAncestor = cur;
		cur = o1;
		int distance1 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance1++;
		}
		cur = o2;
		int distance2 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance2++;
		}
		return distance1 + distance2 - 1;
	}
	
	// solution two
	public static int maxDistance2(TreeNode head) {
		return process(head).distance;
	}
	
	// auxiliary class
	public static class Info {
		public int height;
		public int distance;
		
		public Info(int _h, int _d) {
			height = _h;
			distance = _d;
		}
	}
	
	// solution method one
	public static Info process(TreeNode head) {
		if (null == head) {
			return new Info(0, 0);
		}
		
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		int d1 = leftInfo.distance;
		int d2 = rightInfo.distance;
		int d3 = leftInfo.height + rightInfo.height + 1;
		int distance = Math.max(Math.max(d1, d2), d3);
		
		return new Info(height, distance);
	}
	
	// for test
	public static TreeNode generateRandomTreeNode(int maxLevel, int maxValue) {
		return generate(1, (int) (Math.random() * maxLevel) + 1, maxValue);
	}
	
	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel ||
			Math.random() < 0.3) {
			return null;
		}
		
		TreeNode head = new TreeNode((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		
		return head;
	}
	
	// for test
	public static void test() {
		int maxLevel = 3;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomTreeNode(maxLevel, maxValue);
			
			int ans1 = maxDistance1(head);
			int ans2 = maxDistance2(head);
			
			if (ans1 != ans2) {
				Solution.printTree(head);
				System.out.println("---");
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2);
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
	
	// main
	public static void main(String[] args) {
		test();
	}
}

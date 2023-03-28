package src.class13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import src.Solution;
import src.Solution.TreeNode;

// 给定一棵二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先
public class Code03_LowestAncestor {
	public static TreeNode lowestAncestor1(TreeNode head, TreeNode o1, TreeNode o2) {
		if (head == null) {
			return null;
		}
		// key的父节点是value
		HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
		parentMap.put(head, null);
		fillParentMap(head, parentMap);
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
		return cur;
	}

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
	
	// solution two
	public static TreeNode lowestAncestor2(TreeNode head, TreeNode a, TreeNode b) {
		return process(head, a, b).ans;
	}
	
	// extra class
	public static class Info {
		public boolean findA;
		public boolean findB;
		public TreeNode ans;
		
		public Info(boolean a, boolean b, TreeNode ansNode) {
			findA = a;
			findB = b;
			ans = ansNode;
		}
	}
	
	// recursive method two
	public static Info process(TreeNode head, TreeNode a, TreeNode b) {
		if (head == null) {
			return new Info(false, false, null);
		}
		
		Info leftInfo = process(head.left, a, b);
		Info rightInfo = process(head.right, a, b);
		
		boolean findA = (head == a) || leftInfo.findA || rightInfo.findA;
		boolean findB = (head == b) || leftInfo.findB || rightInfo.findB;
		
		TreeNode ans = null;
		if (leftInfo.ans != null) {
			ans = leftInfo.ans;
		} else if (rightInfo.ans != null) {
			ans = rightInfo.ans;
		} else {
			if (findA && findB) {
				ans = head;
			}
		}
		
		return new Info(findA, findB, ans);
	}
	
	// for test
	public static TreeNode generateRandomTreeNode(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}
	
	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		
		TreeNode head = new TreeNode((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		
		return head;
	}
	
	// for test
	public static TreeNode pickRandomOne(TreeNode head) {
		if (head == null) {
			return null;
		}
		ArrayList<TreeNode> arr = new ArrayList<>();
		fillPrelist(head, arr);
		int randomIndex = (int) (Math.random() * arr.size());
		return arr.get(randomIndex);
	}

	// for test
	public static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}
	
	// for test
	public static void test() {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomTreeNode(maxLevel, maxValue);
			TreeNode o1 = pickRandomOne(head);
			TreeNode o2 = pickRandomOne(head);
			
			TreeNode ans1 = lowestAncestor1(head, o1, o2);
			TreeNode ans2 = lowestAncestor2(head, o1, o2);
			
			if (ans1 != ans2) {
				Solution.printTree(head);
				System.out.println("---");
				System.out.println("ans1 = " + ans1.value + ", ans2 = " + ans2.value);
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

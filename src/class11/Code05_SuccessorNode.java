package src.class11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 求二叉树某个节点的后继节点 
 * 二叉树结构如下定义： 
 * Class Node { 
 * 	V value; 
 * 	Node left; 
 * 	Node right; 
 * 	Node parent; 
 * } 
 * 给你二叉树中的某个节点，返回该节点的后继节点
 */
public class Code05_SuccessorNode {
	public static class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;
		public TreeNode parent;

		public TreeNode(int _value) {
			value = _value;
		}
	}

	public static TreeNode getSuccessorNode(TreeNode node) {
		if (node == null) {
			return node;
		}

		if (node.right != null) {
			return getNode(node.right);
		} else {
			TreeNode cur = node.parent;
			while (cur != null && cur.right == node) {
				node = cur;
				cur = cur.parent;
			}

			return cur;
		}
	}

	public static TreeNode getNode(TreeNode node) {
		if (node == null) {
			return node;
		}

		while (node.left != null) {
			node = node.left;
		}

		return node;
	}

	// for test
	public static TreeNode generateRandomTreeNode(int maxLevel, int maxValue) {
		TreeNode head = generate(1, (int) (Math.random() * maxLevel) + 1, maxValue);
		parentNode(head);
		return head;
	}

	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.25) {
			return null;
		}

		TreeNode head = new TreeNode((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);

		return head;
	}
	
	// for test
	public static void parentNode(TreeNode head) {
		if (head == null) {
			return;
		}
		
		TreeNode cur = head;
		while (cur.left != null) {
			cur.left.parent = cur;
			cur = cur.left;
		}
		
		cur = head;
		while (cur.right != null) {
			cur.right.parent = cur;
			cur = cur.right;
		}
	}

	// for test
	public static TreeNode getRandomNode(TreeNode head) {
		List<TreeNode> inOrderList = new ArrayList<>();
		inOrder(head, inOrderList);
		
		TreeNode ans = null;
		if (!inOrderList.isEmpty()) {
			ans = inOrderList.get((int) (Math.random() * inOrderList.size()));
		}
		return ans;
	}

	// for test
	public static TreeNode testMethod(TreeNode node) {
		if (node == null) {
			return node;
		}
		
		TreeNode head = node;

		while (head.parent != null) {
			head = head.parent;
		}

		List<TreeNode> inOrderList = new ArrayList<>();
		inOrder(head, inOrderList);

		TreeNode ans = null;
		for (int i = 0; i < inOrderList.size() - 1; i++) {
			if (node == inOrderList.get(i)) {
				ans = inOrderList.get(i + 1);
			}
		}

		return ans;
	}

	// for test
	public static void inOrder(TreeNode head, List<TreeNode> inOrderList) {
		if (head == null) {
			return;
		}

		inOrder(head.left, inOrderList);
		inOrderList.add(head);
		inOrder(head.right, inOrderList);
	}

	// for test
	public static void printTree(TreeNode root) {
		int maxLevel = maxLevel(root);

		printNodeInternal(Collections.singletonList(root), 1, maxLevel);
	}

	// for test
	private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
		if (nodes.isEmpty() || isAllElementsNull(nodes))
			return;

		int floor = maxLevel - level;
		int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int firstSpaces = (int) Math.pow(2, (floor)) - 1;
		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

		printWhitespaces(firstSpaces);

		List<TreeNode> newNodes = new ArrayList<>();
		for (TreeNode node : nodes) {
			if (node != null) {
				System.out.print(node.value);
				newNodes.add(node.left);
				newNodes.add(node.right);
			} else {
				newNodes.add(null);
				newNodes.add(null);
				System.out.print(" ");
			}

			printWhitespaces(betweenSpaces);
		}
		System.out.println("");

		for (int i = 1; i <= endgeLines; i++) {
			for (int j = 0; j < nodes.size(); j++) {
				printWhitespaces(firstSpaces - i);
				if (nodes.get(j) == null) {
					printWhitespaces(endgeLines + endgeLines + i + 1);
					continue;
				}

				if (nodes.get(j).left != null)
					System.out.print("/");
				else
					printWhitespaces(1);

				printWhitespaces(i + i - 1);

				if (nodes.get(j).right != null)
					System.out.print("\\");
				else
					printWhitespaces(1);

				printWhitespaces(endgeLines + endgeLines - i);
			}

			System.out.println("");
		}

		printNodeInternal(newNodes, level + 1, maxLevel);
	}

	// for test
	private static boolean isAllElementsNull(List<TreeNode> list) {
		for (Object object : list) {
			if (object != null)
				return false;
		}

		return true;
	}

	// for test
	private static void printWhitespaces(int count) {
		for (int i = 0; i < count; i++)
			System.out.print(" ");
	}

	// for test
	private static int maxLevel(TreeNode node) {
		if (node == null)
			return 0;

		return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
	}

	// for test
	public static void test() {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000;

		System.out.println("start");

		for (int i = 0; i < testTimes; i++) {
			TreeNode node = generateRandomTreeNode(maxLevel, maxValue);

			TreeNode randomNode = getRandomNode(node);

			TreeNode ans1 = getSuccessorNode(randomNode);
			TreeNode ans2 = testMethod(randomNode);

			if (ans1 != ans2) {
				printTree(node);				
				System.out.println("---");

				System.out.println("randomNode = " + (randomNode == null ? null : randomNode.value));
				
				System.out.println("randomNode.parent = " + (randomNode.parent == null ? null : randomNode.parent.value));
				System.out.println("randomNode.left = " + (randomNode.left == null ? null : randomNode.left.value));
				System.out.println("randomNode.right = " + (randomNode.right == null ? null : randomNode.right.value));
				
				System.out.println("ans1 = " + (ans1 == null ? null : ans1.value));
				System.out.println("ans2 = " + (ans2 == null ? null : ans2.value));
				
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

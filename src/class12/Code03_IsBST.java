package src.class12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 判断二叉树是不是搜索二叉树
 */
public class Code03_IsBST {
	/**
	 * structure
	 */
	public static class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode(int _value) {
			value = _value;
		}
	}
	
	// solution one
	public static boolean isBST1(TreeNode head) {
		if (null == head) {
			return true;
		}
		
		List<Integer> list = new ArrayList<>();
		in(head, list);
		
		boolean ans = true;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i + 1) <= list.get(i)) {
				ans = false;
			}
		}
		
		return ans;
	}
	
	// in-order
	public static void in(TreeNode head, List<Integer> list) {
		if (null == head) {
			return;
		}
		
		in(head.left, list);
		list.add(head.value);
		in(head.right, list);
	}
	
	// solution two
	public static boolean isBST2(TreeNode head) {
		if (head == null) {
			return true;
		}
		
		return process(head).isBool;
	}
	
	// extra class
	public static class Info {
		public int min;
		public int max;
		public boolean isBool;
		
		public Info(int _min, int _max, boolean _isBool) {
			min = _min;
			max = _max;
			isBool = _isBool;
		}
	}
	
	// recursive method one 
	public static Info process(TreeNode head) {
		if (head == null) {
			return null;
		}
		
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		
		int min = head.value;
		int max = head.value;
		boolean isBST = true;
		if (null != leftInfo) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			
			if (!leftInfo.isBool ||
				leftInfo.max >= head.value) {
				isBST = false;
			}
		}
		
		if (null != rightInfo) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			
			if (!rightInfo.isBool ||
				rightInfo.min <= head.value) {
				isBST = false;
			}
		}
		
		return new Info(min, max, isBST);
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
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomTreeNode(maxLevel, maxValue);
			
			boolean ans1 = isBST1(head);
			boolean ans2 = isBST2(head);
			
			if (ans1 != ans2) {
				printTree(head);
				
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

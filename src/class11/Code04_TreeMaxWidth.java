package src.class11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 求二叉树的最大宽度
 */
public class Code04_TreeMaxWidth {
	public static class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode(int _value) {
			value = _value;
		}
	}
	
	public static int maxWidthUseList(TreeNode head) {
		if (head == null) {
			return 0;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(head);
		
		List<List<Integer>> levelList = new ArrayList<>();
		
		while (!queue.isEmpty()) {
			List<Integer> curLevelList = new ArrayList<>();
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				
				if (cur.left != null) {
					queue.add(cur.left);
				}
				
				if (cur.right != null) {
					queue.add(cur.right);
				}
				
				curLevelList.add(cur.value);
			}
			
			levelList.add(curLevelList);
		}
		
		int max = 0;
		for (List<Integer> ans : levelList) {
			max = Math.max(max, ans.size());
		}
		
		return max;
	}
	
	public static int maxWidth(TreeNode head) {
		if (head == null) {
			return 0;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(head);
		TreeNode curEnd = head;
		TreeNode nextEnd = null;
	
		int max = 0;
		int curLevelNodes = 0;
		
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			
			if (cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			
			if (cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			
			curLevelNodes++;
			
			if (cur == curEnd) {
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 0;
				curEnd = nextEnd;
			}
		}
		
		return max;
	}
	
	// for test
	public static TreeNode generateRandomTree(int maxLevel, int maxValue) {
		return generate(1, (int) (Math.random() * maxLevel) + 1, maxValue);
	}
	
	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel ||
			Math.random() < 0.5) {
			return null;
		}
		
		TreeNode head = new TreeNode((int) (Math.random() * maxValue) - ((int) (Math.random() * maxValue)));
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
			TreeNode head = generateRandomTree(maxLevel, maxValue);
			
			int ans1 = maxWidthUseList(head);
			int ans2 = maxWidth(head);

			if (ans1 != ans2) {
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

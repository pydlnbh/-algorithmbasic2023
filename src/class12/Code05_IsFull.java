package src.class12;

import src.Solution;
import src.Solution.TreeNode;

// 判断二叉树是不是满二叉树
public class Code05_IsFull {
	// solution one
	public static boolean isFull1(TreeNode root) {
		if (null == root) {
			return true;
		}
		Info1 info = process1(root);
		return (1 << info.height) - 1 == info.nodes;
	}
	
	// extra structure one
	public static class Info1 {
		public int nodes;
		public int height;
		
		public Info1(int _n, int _h) {
			nodes = _n;
			height = _h;
		}
	}
	
	// recursive method one
	public static Info1 process1(TreeNode root) {
		if (null == root) {
			return new Info1(0, 0);
		}
		
		Info1 leftInfo = process1(root.left);
		Info1 rightInfo = process1(root.right);
		int nodes = leftInfo.nodes + rightInfo.nodes + 1;
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		
		return new Info1(nodes, height);
	}
	
	// solution two
	public static boolean isFull2(TreeNode root) {
		return process2(root).isFull;
	}
	
	// extra structure two
	public static class Info2 {
		public boolean isFull;
		public int height;
		
		public Info2(boolean _f, int _h) {
			isFull = _f;
			height = _h;
		}
	}
	
	// recursive method two
	public static Info2 process2(TreeNode root) {
		if (null == root) {
			return new Info2(true, 0);
		}
		
		Info2 leftInfo = process2(root.left);
		Info2 rightInfo = process2(root.right);
		
		boolean isFull = leftInfo.isFull &&
						 rightInfo.isFull &&
						 leftInfo.height == rightInfo.height;
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		
		return new Info2(isFull, height);
	}
	
	// for test
	public static TreeNode generateRandomTreeNode(int maxLevel, int maxValue) {
		return generate(1, (int) (Math.random() * maxLevel) + 1, maxValue);
	}
	
	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel ||
			Math.random() < 0.5) {
			return null;
		}
		
		TreeNode head = new TreeNode((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		
		return head;
	}
	
	// for test
	public static void test() {
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomTreeNode(maxLevel, maxValue);
			
			boolean ans1 = isFull1(head);
			boolean ans2 = isFull2(head);
			
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

package src.class12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是不是完全二叉树（一般方法解决、递归套路解决）
 */
public class Code01_IsCBT {
	/**
	 * 二叉树结构
	 */
	public static class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode(int _value) {
			value = _value;
		}
	}
	
	/**
	 * no recursive method
	 * 
	 * @param root 根节点
	 * @return 是否是二叉树
	 */
	public static boolean isCBT(TreeNode head) {
		if (head == null) {
			return true;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(head);
		
		boolean leaf = false;
		TreeNode left = null;
		TreeNode right = null;
		
		while (!queue.isEmpty()) {
			head = queue.poll();
			left = head.left;
			right = head.right;
			
			if ((leaf && (left != null || right != null)) || 
				(left == null && right != null)) {
				return false;
			}
			
			if (left != null) {
				queue.add(left);
			}
			
			if (right != null) {
				queue.add(right);
			}
			
			if (left == null || right == null) {
				leaf = true;
			}
		}
		
		return true;
	}
	
	/**
	 * recursive method
	 * 
	 * @param binary tree head node
	 * @return boolean
	 */
	public static boolean isCBT1(TreeNode head) {
		if (head == null) {
			return true;
		}
		
		return process(head).isCBT;
	}
	
	public static class Info {
		public boolean isFull;
		public boolean isCBT;
		public int height;
		
		public Info(boolean full, boolean cbt, int h) {
			isFull = full;
			isCBT = cbt;
			height = h;
		}
	}
	
	public static Info process(TreeNode head) {
		if (head == null) {
			return new Info(true, true, 0);
		}
		
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		
		boolean isFull = leftInfo.isFull &&
				         rightInfo.isFull &&
				         leftInfo.height == rightInfo.height;
		
		boolean isCBT = false;
		if (isFull) {
			isCBT = true;
		} else {
			if (leftInfo.isCBT && rightInfo.isCBT) {
				
				if (leftInfo.isCBT && 
					rightInfo.isFull &&
					leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				
				if (leftInfo.isFull &&
					rightInfo.isFull &&
					leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				
				if (leftInfo.isFull &&
					rightInfo.isCBT &&
					leftInfo.height == rightInfo.height) {
					isCBT = true;
				}
			}
		}
		
		return new Info(isFull, isCBT, height);
	}
	
	// for test
	public static TreeNode generateRandomTreeNode(int maxLevel, int maxValue) {
		return generate(1, (int) (Math.random() * maxLevel) + 1, maxValue);
	}
	
	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel ||
			Math.random() < 0.4) {
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
			
			boolean ans1 = isCBT(head);
			boolean ans2 = isCBT1(head);
			
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

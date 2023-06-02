package src.class30;

import src.Solution.TreeNode;

/**
 * 给定一棵二叉树的头节点head，求以head为头的树中，最小深度是多少?
 * 
 * 本题测试链接 : https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 */
public class Code02_MinDepth {
	// solution one
	public static int minDepth1(TreeNode head) {
		if (head == null) {
			return 0;
		}
		
		return p(head);
	}
	
	// recursive method
	public static int p(TreeNode x) {
		if (x.left == null && x.right == null) {
			return 1;
		}
		
		int leftH = Integer.MAX_VALUE;
		if (x.left != null) {
			leftH = p(x.left);
		}
		
		int rightH = Integer.MAX_VALUE;
		if (x.right != null) {
			rightH = p(x.right);
		}
		
		return Math.min(leftH, rightH) + 1;
	}
	
	// solution two
	public static int minDepth2(TreeNode head) {
		if (head == null) {
			return 0;
		}
		
		TreeNode cur = head;
		TreeNode mostRight = null;
		int curLevel = 0;
		int minHeight = Integer.MAX_VALUE;
		
		while (cur != null) {
			mostRight = cur.left;
			
			if (mostRight != null) {
				int rightBoardSize = 1;
				
				while (mostRight.right != null && mostRight.right != cur) {
					rightBoardSize++;
					mostRight = mostRight.right;
				}
				
				if (mostRight.right == null) {
					curLevel++;
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					if (mostRight.left == null) {
						minHeight = Math.min(minHeight, curLevel);
					}
					curLevel -= rightBoardSize;
					mostRight.right = null;
				}
			} else {
				curLevel++;
			}
			
			cur = cur.right;
		}
		
		int finalRight = 1;
		cur = head;
		
		while (cur.right != null) {
			finalRight++;
			cur = cur.right;
		}
		
		if (cur.left == null && cur.right == null) {
			minHeight = Math.min(minHeight, finalRight);
		}
		
		return minHeight;
	}
}

package src.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树转化为唯一的二叉树
 */
public class Code03_EncodeNaryTreeToBinaryTree {
	/**
	 * 多叉树结构
	 */
	public static class Node {
		public int val;
		public List<Node> children;
		
		public Node() {
			
		}
		
		public Node(int _val) {
			val = _val;
		}
		
		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}
	
	/**
	 * 二叉树结构
	 */
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode(int _val) {
			val = _val;
		}
	}
	
	/**
	 * Encodes an n-ary tree to a binary tree 
	 * 多叉树转为二叉树
	 * 
	 * @param root 根节点
	 * @return 二叉树结点
	 */
	public static TreeNode encode(Node root) {
		if (root == null) {
			return null;
		}
		
		TreeNode head = new TreeNode(root.val);
		head.left = en(root.children);
		return head;
	}
	
	/**
	 * 递归方法
	 * 
	 * @param children 孩子结点
	 * @return 二叉树结点
	 */
	public static TreeNode en(List<Node> children) {
		TreeNode head = null;
		TreeNode cur = null;
		
		for (Node child : children) {
			TreeNode tNode = new TreeNode(child.val);
			if (head == null) {
				head = tNode;
			} else {
				cur.right = tNode;
			}
			
			cur = tNode;
			cur.left = en(child.children);
		}
		
		return head;
	}
	
	/**
	 * Decodes a binary tree to an n-ary tree
	 * 将二叉树转化为多叉树
	 * 
	 * @param root 二叉树头结点
	 * @return 多叉树结点
	 */
	public static Node decode(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		return new Node(root.val, de(root.left));
	}
	
	/**
	 * 递归方法
	 * 
	 * @param root 二叉树结点
	 * @return 孩子结点
	 */
	public static List<Node> de(TreeNode root) {
		List<Node> children = new ArrayList<>();
		
		while (root != null) {
			Node cur = new Node(root.val, de(root.left));
			children.add(cur);
			root = root.right;
		}
		
		return children;
	}
}

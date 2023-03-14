package src.class10;

/**
 * 二叉树先序、中序、后序的递归遍历和递归序
 */
public class Class01_RecursiveTraversalBT {
	
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int v) {
			value = v;
		}
	}
	
	/**
	 * 二叉树的遍历
	 * 
	 * @param head 头结点
	 */
	public static void f(Node head) {
		if (head == null) {
			return;
		}
		
		// print pre-order
		f(head.left);
		// print in-order
		f(head.right);
		// print post-order
	}
	
	/**
	 * 先序遍历, 头左右
	 * 
	 * @param head 头结点
	 */
	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		
		System.out.println(head.value);
		pre(head.left);
		pre(head.left);
	}
	
	/**
	 * 中序遍历, 左头右	
	 * 
	 * @param head 头结点
	 */
	public static void in(Node head) {
		if (head == null) {
			return;
		}
		
		in(head.left);
		System.out.println(head.value);
		in(head.right);
	}
	
	/**
	 * 后序遍历, 左右头
	 * 
	 * @param head 头结点
	 */
	public static void pos(Node head) {
		if (head == null) {
			return;
		}
		
		pos(head.left);
		pos(head.right);
		System.out.println(head.value);
	}
	
	/**
	 * main方法
	 * 
	 * @param args 标准入参
	 */
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos(head);
		System.out.println("========");
	}
}

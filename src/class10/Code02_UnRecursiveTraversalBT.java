package src.class10;

import java.util.Stack;

/**
 * 二叉树先序、中序、后序的非递归遍历
 */
public class Code02_UnRecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	/**
	 * 先序遍历
	 * 
	 * @param head 头结点
	 */
	public static void pre(Node head) {
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				System.out.print(head.value + " ");
				
				if (head.right != null) {
					stack.push(head.right);	
				}
				
				if (head.left != null) {
					stack.push(head.left);	
				}
			}

		}
	}
	
	/**
	 * 中序遍历
	 * 
	 * @param head 头结点
	 */
	public static void in(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			while (!stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					System.out.print(head.value + " ");
					head = head.right;
				}
			}
		}
	}
	
	/**
	 * 使用两个栈空间
	 * 
	 * @param head 头结点
	 */
	public static void pos1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			Stack<Node> popStack = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				popStack.push(head);
				if (head.left != null) {
					stack.push(head.left);
				}
				
				if (head.right != null) {
					stack.push(head.right);
				}
			}
			
			while (!popStack.isEmpty()) {
				System.out.print(popStack.pop().value + " ");
			}
		}
	}

	/**
	 * 使用一个栈空间
	 * 
	 * @param h 头结点
	 */
	public static void pos2(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek();
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
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
		pos1(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
	}
}

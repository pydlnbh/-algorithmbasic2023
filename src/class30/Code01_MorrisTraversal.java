package src.class30;

/**
 * Morris遍历的实现
 */
public class Code01_MorrisTraversal {
	// auxiliary class
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int v) {
			value = v;
		}
	}
	
	public static void process(Node root) {
		if (root == null) {
			return;
		}
		
		// 1
//		System.out.print(root.value + " ");
		process(root.left);
		// 2
//		System.out.print(root.value + " ");
		process(root.right);
		// 3
		System.out.print(root.value + " ");
	}
	
	// solution
	public static void morris(Node head) {
		if (head == null) {
			return;
		}
		
		Node cur = head;
		Node mostRight = null;
		
		while (cur != null) {
			mostRight = cur.left;
			
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			cur = cur.right;
		}
	}
	
	// solution one
	public static void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		
		Node cur = head;
		Node mostRight = null;
		
		while (cur != null) {
			mostRight = cur.left;
			
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				
				if (mostRight.right == null) {
					System.out.print(cur.value + " ");
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			} else {
				System.out.print(cur.value + " ");
			}
			
			cur = cur.right;
		}
	}
	
	public static void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		
		Node cur = head;
		Node mostRight = null;
		
		while (cur != null) {
			mostRight = cur.left;
			
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				}
			}
			
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
	}
	
	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		
		Node cur = head;
		Node mostRight = null;
		
		while (cur != null) {
			mostRight = cur.left;
			
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					printEdge(cur.left);
				}
			}

			cur = cur.right;
		}
		
		printEdge(head);
	}
	
	public static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		
		reverseEdge(tail);
	}
	
	public static Node reverseEdge(Node head) {
		Node pre = null;
		Node next = null;
		
		while (head != null) {
			next = head.right;
			head.right = pre;
			pre = head;
			head = next;
		}
		
		return pre;
	}
	
	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		
		Node cur = head;
		Integer pre = null;
		Node mostRight = null;
		boolean ans = true;
		
		while (cur != null) {
			mostRight = cur.left;
			
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			
			if (pre != null && pre > cur.value) {
				ans = false;
			}
			
			pre = cur.value;
			cur = cur.right;
		}
		
		return ans;
	}

	public static void main(String[] args) {
		Node node = new Node(4);
		node.left = new Node(2);
		node.right = new Node(6);
		node.left.left = new Node(0);
		node.left.right = new Node(3);
		node.right.left = new Node(5);
		node.right.right = new Node(7);
		
		process(node);
		System.out.println();
		morrisPos(node);
		System.out.println();
		morrisIn(node);
		System.out.println();
		System.out.println(isBST(node));
	}

}

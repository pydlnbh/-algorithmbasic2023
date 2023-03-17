package src.class11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
			children = new ArrayList<Node>();
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
		
		if (children != null) {
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
	
	/**
	 * generate random N-Ary Tree
	 * 
	 * @param depth
	 * @param numChildren 
	 * @return Node
	 */
    public static Node generateRandomNAryTree(int depth, int numChildren, int maxValue) {
        Random rand = new Random();
        int val = rand.nextInt(maxValue) + 1; // generate a random value for the root node
        Node root = new Node(val);
        if (depth > 0) {
            int num = rand.nextInt(numChildren) + 1; // generate a random number of children for the root node
            for (int i = 0; i < num; i++) {
                root.children.add(generateRandomNAryTree(depth-1, numChildren, maxValue));
            }
        }
        return root;
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
				System.out.print(node.val);
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
    public static String convertNAryTreeToString(Node root) {
    	StringBuffer sb = new StringBuffer();
        if (root == null) {
            return null;
        }
//        System.out.print(root.val);
        sb.append(root.val);
        if (!root.children.isEmpty()) {
//            System.out.print("(");
        	sb.append("(");
            for (int i = 0; i < root.children.size(); i++) {
//                printNAryTree(root.children.get(i));
            	sb.append(root.children.get(i));
                if (i < root.children.size() - 1) {
//                    System.out.print(",");
                	sb.append(",");
                }
            }
//            System.out.print(")");
            sb.append(")");
        }
        
        return sb.toString();
    }

	/**
	 * 对数器
	 */
	public static void test() {
		int maxLevel = 3;
		int maxChild = 3;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			Node nAryTree = generateRandomNAryTree(maxLevel, maxChild, maxValue);
			
			TreeNode binaryTree = encode(nAryTree);
		
			Node _nAryTree = decode(binaryTree);
			
			String ans1 = convertNAryTreeToString(nAryTree);
			String ans2 = convertNAryTreeToString(_nAryTree);
			
			boolean flag = (ans1 == null && ans2 == null) || (ans1 != null && ans1.equals(ans2));
			
			if (flag) {
				System.out.println(ans1);
				
				System.out.println("\n===");
				
				printTree(binaryTree);
				
				System.out.println("\n===");
				
				System.out.println(ans2);
				
				System.out.println("\nOops");
				break;
			}
		}
		
		System.out.println("end");
	}
    
	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		test();
	}
}

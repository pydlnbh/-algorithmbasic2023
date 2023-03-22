package src.class12;

/**
 * 判断二叉树是不是平衡二叉树
 */
public class Code02_IsBalanced {
	// auxiliary class
	public static class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode(int _value) {
			value = _value;
		}
	}
	
	// solution one
	public static boolean isBalanced1(TreeNode head) {
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head, ans);
		return ans[0];
	}
	
	// recursive method one
	public static int process1(TreeNode head, boolean[] ans) {
		if (!ans[0] ||
			head == null) {
			return -1;
		}
		
		int leftHeight = process1(head.left, ans);
		int rightHeight = process1(head.right, ans);
		
		if (Math.abs(leftHeight - rightHeight) > 1) {
			ans[0] = false;
		}
		
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	// solution two
	public static boolean isBalanced2(TreeNode head) {
		return process2(head).isBanlance;
	}
	
	// auxiliary class
	public static class Info {
		public boolean isBanlance;
		public int height;
		
		public Info(boolean bool, int i) {
			isBanlance = bool;
			height = i;
		}
	}
	
	// recursive method
	public static Info process2(TreeNode head) {
		if (head == null) {
			return new Info(true, 0);
		}
		
		Info leftInfo = process2(head.left);
		Info rightInfo = process2(head.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isBalanced = true;
		if (!leftInfo.isBanlance) {
			isBalanced = false;
		}
		
		if (!rightInfo.isBanlance) {
			isBalanced = false;
		}
		
		if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
			isBalanced = false;
		}
		
		return new Info(isBalanced, height);
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
			
			if (isBalanced1(head) != isBalanced2(head)) {
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

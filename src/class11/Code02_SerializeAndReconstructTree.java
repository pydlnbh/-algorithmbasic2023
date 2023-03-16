package src.class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化和反序列化
 */
public class Code02_SerializeAndReconstructTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int v) {
			value = v;
		}
	}
	
	/**
	 * 前序遍历序列化
	 * 
	 * @param head 头结点
	 * @return java.util.Queue<java.lang.String> 序列化对象
	 */
	public static Queue<String> preSerial(Node head) {
		if (head == null) {
			return null;
		}
		
		Queue<String> queue = new LinkedList<>();
		preProcess(head, queue);
		return queue;
	}
	
	/**
	 * 递归方法
	 * 
	 * @param head 头结点
	 * @param queue 队列对象
	 */
	public static void preProcess(Node head, Queue<String> queue) {
		if (head == null) {
			queue.offer(null);
		} else {
			queue.offer(String.valueOf(head.value));
			preProcess(head.left, queue);
			preProcess(head.right, queue);
		}
	}
	
    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *       
     * */
	public static void inSerial(Node head) {
		
	}

	/**
	 * 后序遍历序列化
	 * 
	 * @param head 头结点
	 * @return java.util.Queue<java.lang.String> 序列化对象
	 */
	public static Queue<String> posSerial(Node head) {
		if (head == null) {
			return null;
		}
		
		Queue<String> ans = new LinkedList<>();
		posProcess(head, ans);
		
		return ans;
	}
	
	/**
	 * 递归方法
	 * 
	 * @param head 头结点
	 * @param ans 序列化对象
	 */
	public static void posProcess(Node head, Queue<String> ans) {
		if (head == null) {
			ans.offer(null);
		} else {
			posProcess(head.left, ans);
			posProcess(head.right, ans);
			ans.offer(String.valueOf(head.value));
		}
	}
	
	/**
	 * 前序遍历反序列化
	 * 
	 * @param queue 序列化对象
	 * @return Node 二叉树头结点
	 */
	public static Node buildByPreQueue(Queue<String> queue) {
		if (queue == null ||
			queue.size() < 1) {
			return null;
		}
		
		return preBuild(queue);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param queue 序列化对象
	 * @return 二叉头树结点
	 */
	public static Node preBuild(Queue<String> queue) {
		String value = queue.poll();
		if (value == null) {
			return null;
		}
		
		Node head = new Node(Integer.valueOf(value));
		head.left = preBuild(queue);
		head.right = preBuild(queue);
		
		return head;
	}
	
	/**
	 * 后序遍历反序列化
	 * 
	 * @param queue 队列
	 * @return Node 二叉树头结点
	 */
	public static Node buildByPosQueue(Queue<String> queue) {
		if (queue == null ||
			queue.size() < 1) {
			return null;
		}
		
		Stack<String> popStack = new Stack<>();
		while (!queue.isEmpty()) {
			popStack.push(queue.poll());
		}
 		
		return posBuild(popStack);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param popStack 弹出栈
	 * @return Node 二叉树头结点
	 */
	public static Node posBuild(Stack<String> popStack) {
		String value = popStack.pop();
		if (value == null) {
			return null;
		}
		
		Node head = new Node(Integer.valueOf(value));
		head.right = posBuild(popStack);
		head.left = posBuild(popStack);
		
		return head;
	}
	
	/**
	 * 层序遍历序列化
	 * 
	 * @param head 二叉树头结点
	 * @return java.util.Queue<java.lang.String> 序列化对象
	 */
	public static Queue<String> levelSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		if (head == null) {
			ans.add(null);
		} else {
			ans.offer(String.valueOf(head.value));
			Queue<Node> queue = new LinkedList<>();
			queue.offer(head);
			
			while (!queue.isEmpty()) {
				Node cur = queue.poll();
				
				if (cur.left != null) {
					ans.add(String.valueOf(cur.left.value));
					queue.add(cur.left);
				} else {
					ans.add(null);
				}
				
				if (cur.right != null) {
					ans.add(String.valueOf(cur.right.value));
					queue.add(cur.right);
				} else {
					ans.add(null);
				}
			}
		}
		
		return ans;
	}
	
	/**
	 * 层序排序反序列化
	 * 
	 * @param queue 序列化对象
	 * @return 二叉树头结点
	 */
	public static Node buildByLevelQueue(Queue<String> queue) {
		if (queue == null ||
			queue.size() < 1) {
			return null;
		}
		
		Node head = generateNode(queue.poll());
		
		Queue<Node> aux = new LinkedList<>();
		if (head != null) {
			aux.offer(head);
		}
		
		while (!aux.isEmpty()) {
			Node cur = aux.poll();
			cur.left = generateNode(queue.poll());
			cur.right = generateNode(queue.poll());
			
			if (cur.left != null) {
				aux.offer(cur.left);
			}
			
			if (cur.right != null) {
				aux.offer(cur.right);
			}
		}
		
		return head;
	}
	
	/**
	 * 根据字符串生成二叉树结点
	 * 
	 * @param val 字符串
	 * @return Node 结点
	 */
	public static Node generateNode(String val) {
		if (val == null) {
			return null;
		}
		
		return new Node(Integer.valueOf(val));
	}
	
	/**
	 * 生成随机树
	 * 
	 * @param maxLevel 最大等级
	 * @param maxValue 最大值
	 * @return Node 二叉树结点
	 */
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param level 等级
	 * @param maxLevel 最大等级
	 * @param maxValue 最大值
	 * @return Node 二叉树结点
	 */
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel ||
			Math.random() < 0.5) {
			return null;
		}
		
		Node head = new Node((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		
		return head;
	}
	
	/**
	 * 两个二叉树比较是否相同
	 * 
	 * @param head1 二叉树头结点
	 * @param head2 二叉树头结点
	 * @return boolean
	 */
	public static boolean isSameValueStructure(Node head1, Node head2) {
		if (head1 == null && head2 != null) {
			return false;
		}
		
		if (head1 != null && head2 == null) {
			return false;
		}
		
		if (head1 == null && head2 == null) {
			return true;
		}
		
		if (head1.value != head2.value) {
			return false;
		}
		
		return isSameValueStructure(head1.left, head2.left) &&
			   isSameValueStructure(head1.right, head2.right);
	}
	
	/**
	 * 打印二叉树
	 * 
	 * @param head 头结点
	 */
	public static void printTree(Node head) {
		System.out.println("Binary Tree: ");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}
	
	/**
	 * 递归打印
	 * 
	 * @param head 二叉树结点
	 * @param height 深度
	 * @param to 字符
	 * @param len 长度
	 */
	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		
		printInOrder(head.right, height + 1, "v", len);
		
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}
	
	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}
 	
	/**
	 * 测试方法
	 */
	public static void test() {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			
			Queue<String> preQueue = preSerial(head);
			Queue<String> posQueue = posSerial(head);
			Queue<String> levelQueue = levelSerial(head);
			
			Node preHead = buildByPreQueue(preQueue);
			Node posHead = buildByPosQueue(posQueue);
			Node levelHead = buildByLevelQueue(levelQueue);
			
			if (!isSameValueStructure(preHead, posHead) ||
				!isSameValueStructure(preHead, levelHead)) {
				printTree(head);
				System.out.println("==================");
				printTree(preHead);
				System.out.println("==================");
				printTree(posHead);
				
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
	
	/**
	 * main方法
	 * 
	 * @param args 标准入参
	 */
	public static void main(String[] args) {
		test();
	}
}

package src.class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import src.Solution;
import src.Solution.TreeNode;

/**
 * 二叉树的序列化和反序列化
 */
public class Code02_SerializeAndReconstructTree {
	/**
	 * 前序遍历序列化
	 * 
	 * @param head 头结点
	 * @return java.util.Queue<java.lang.String> 序列化对象
	 */
	public static Queue<String> preSerial(TreeNode head) {
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
	 * @param head  头结点
	 * @param queue 队列对象
	 */
	public static void preProcess(TreeNode head, Queue<String> queue) {
		if (head == null) {
			queue.offer(null);
		} else {
			queue.offer(String.valueOf(head.value));
			preProcess(head.left, queue);
			preProcess(head.right, queue);
		}
	}

	/*
	 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化， 以下代码全部实现了。 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
	 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。 比如如下两棵树 __2 / 1 和 1__ \ 2 补足空位置的中序遍历结果都是{
	 * null, 1, null, 2, null}
	 * 
	 */
	public static void inSerial(TreeNode head) {

	}

	/**
	 * 后序遍历序列化
	 * 
	 * @param head 头结点
	 * @return java.util.Queue<java.lang.String> 序列化对象
	 */
	public static Queue<String> posSerial(TreeNode head) {
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
	 * @param ans  序列化对象
	 */
	public static void posProcess(TreeNode head, Queue<String> ans) {
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
	public static TreeNode buildByPreQueue(Queue<String> queue) {
		if (queue == null || queue.size() < 1) {
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
	public static TreeNode preBuild(Queue<String> queue) {
		String value = queue.poll();
		if (value == null) {
			return null;
		}

		TreeNode head = new TreeNode(Integer.valueOf(value));
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
	public static TreeNode buildByPosQueue(Queue<String> queue) {
		if (queue == null || queue.size() < 1) {
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
	public static TreeNode posBuild(Stack<String> popStack) {
		String value = popStack.pop();
		if (value == null) {
			return null;
		}

		TreeNode head = new TreeNode(Integer.valueOf(value));
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
	public static Queue<String> levelSerial(TreeNode head) {
		Queue<String> ans = new LinkedList<>();
		if (head == null) {
			ans.add(null);
		} else {
			ans.offer(String.valueOf(head.value));
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(head);

			while (!queue.isEmpty()) {
				TreeNode cur = queue.poll();

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
	public static TreeNode buildByLevelQueue(Queue<String> queue) {
		if (queue == null || queue.size() < 1) {
			return null;
		}

		TreeNode head = generateNode(queue.poll());

		Queue<TreeNode> aux = new LinkedList<>();
		if (head != null) {
			aux.offer(head);
		}

		while (!aux.isEmpty()) {
			TreeNode cur = aux.poll();
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
	public static TreeNode generateNode(String val) {
		if (val == null) {
			return null;
		}

		return new TreeNode(Integer.valueOf(val));
	}

	/**
	 * 生成随机树
	 * 
	 * @param maxLevel 最大等级
	 * @param maxValue 最大值
	 * @return Node 二叉树结点
	 */
	public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	/**
	 * 递归方法
	 * 
	 * @param level    等级
	 * @param maxLevel 最大等级
	 * @param maxValue 最大值
	 * @return Node 二叉树结点
	 */
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}

		TreeNode head = new TreeNode((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue));
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
	public static boolean isSameValueStructure(TreeNode head1, TreeNode head2) {
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

		return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
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
			TreeNode head = generateRandomBST(maxLevel, maxValue);

			Queue<String> preQueue = preSerial(head);
			Queue<String> posQueue = posSerial(head);
			Queue<String> levelQueue = levelSerial(head);

			TreeNode preHead = buildByPreQueue(preQueue);
			TreeNode posHead = buildByPosQueue(posQueue);
			TreeNode levelHead = buildByLevelQueue(levelQueue);

			if (!isSameValueStructure(preHead, posHead) || !isSameValueStructure(preHead, levelHead)) {
				Solution.printTree(head);
				System.out.println("==================");
				Solution.printTree(head);
				System.out.println("==================");
				Solution.printTree(head);

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

package src.class03;

import java.util.ArrayList;
import java.util.List;

/**
 * 在链表中删除指定值的所有节点
 * 
 * @author peiyiding
 *
 */
public class Code02_DeleteGivenValue {

	/**
	 * 单链表静态类
	 */
	public static class Node {
		public int val;
		public Node next;

		public Node(int val) {
			this.val = val;
		}
	}

	/**
	 * 双向链表静态类
	 */
	public static class DoubleNode {
		public int val;
		public DoubleNode last;
		public DoubleNode next;

		public DoubleNode(int val) {
			this.val = val;
		}
	}

	/**
	 * 在单链表中删除指定值的所有节点
	 * 
	 * @param head  头结点
	 * @param value 给定的值
	 * @return Node 处理后的链表
	 */
	public static Node deleteGivenValue(Node head, int value) {
		// head来到第一个不需要删除的位置
		while (null != head) {
			if (value != head.val) {
				break;
			}
			head = head.next;
		}

		Node pre = head;
		Node cur = head;

		while (null != cur) {
			if (value == cur.val) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}

		return head;
	}

	/**
	 * 在双向链表中删除指定值的所有节点
	 * 
	 * @param head  双向链表头节点
	 * @param value 给定的值
	 * @return DoubleNode 双向链表头节点
	 */
	public static DoubleNode deleteGivenValueDoubleNode(DoubleNode head, int value) {
		while (null != head) {
			if (value != head.val) {
				break;
			}
			head = head.next;
		}

		DoubleNode pre = head;
		DoubleNode cur = head;

		while (null != cur) {
			if (value == cur.val) {
				pre.next = cur.next;
				if (null != cur.next) {
					cur.next.last = pre;
				}
			} else {
				pre = cur;
			}
			cur = cur.next;
		}

		return head;
	}

	/**
	 * 生成随机双向链表
	 * 
	 * @param len 最大长度
	 * @param val 最大值
	 * @return DoubleNode 双向链表头节点
	 */
	public static DoubleNode generateRandomDoubleNode(int len, int val) {
		int size = (int) (Math.random() * len) + 1;

		DoubleNode head = new DoubleNode(generateRandomNum(val));
		DoubleNode pre = head;

		size--;

		while (size > 0) {
			DoubleNode cur = new DoubleNode(generateRandomNum(val));
			pre.next = cur;
			cur.last = pre;
			pre = cur;
			size--;
		}

		return head;
	}

	/**
	 * 把双向链表放入容器中
	 * 
	 * @param head 头结点
	 * @return List<Integer> 容器集合
	 */
	public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
		List<Integer> ans = new ArrayList<Integer>();

		while (null != head) {
			ans.add(head.val);
			head = head.next;
		}

		return ans;
	}

	/**
	 * 检查双向链表和容器集合是否一致
	 * 
	 * @param head 双向链表头结点
	 * @param list 容器集合
	 * @return boolean 是否一致
	 */
	public static boolean checkDoubleListIsEqual(DoubleNode head, List<Integer> list) {
		DoubleNode end = null;

		if (null == head && 0 == list.size()) {
			return true;
		}

		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals(head.val)) {
				return false;
			}
			end = head;
			head = head.next;
		}

		for (int i = list.size() - 1; i >= 0; i--) {
			if (!list.get(i).equals(end.val)) {
				return false;
			}
			end = end.last;
		}

		return true;
	}

	/**
	 * 生成随机链表
	 * 
	 * @param len 链表最大长度
	 * @param val 链表最大值
	 * @return Node 随机链表头结点
	 */
	public static Node generateRandomNode(int len, int val) {
		int size = (int) (Math.random() * len) + 1;

		Node head = new Node(generateRandomNum(val));
		Node pre = head;

		size--;

		while (size != 0) {
			Node cur = new Node(generateRandomNum(val));
			pre.next = cur;
			pre = cur;
			size--;
		}

		return head;
	}

	/**
	 * 生成随机数值
	 * 
	 * @param range -range ~ range
	 * @return int 随机数值
	 */
	public static int generateRandomNum(int range) {
		return (int) (Math.random() * range) - (int) (Math.random() * range);
	}

	/**
	 * 把链表元素放入容器中
	 * 
	 * @param head 链表头结点
	 * @return List<Integer> list容器
	 */
	public static List<Integer> getLinkedListOriginOrder(Node head) {
		if (null == head) {
			return null;
		}

		List<Integer> origin = new ArrayList<>();

		while (null != head) {
			origin.add(head.val);
			head = head.next;
		}

		return origin;
	}

	/**
	 * 在容器中删除给定的值
	 * 
	 * @param origin 容器
	 * @return List<Integer> 返回删除后的集合
	 */
	public static List<Integer> deleteGivenValueList(List<Integer> origin, int value) {
		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < origin.size(); i++) {
			if (value == origin.get(i)) {
				continue;
			}
			ans.add(origin.get(i));
		}

		return ans;
	}

	/**
	 * 对比删除后的链表和集合是否相等
	 * 
	 * @param head 链表头结点
	 * @param list 集合
	 * @return boolean 对比结果
	 */
	public static boolean checkLinkedListIsEqual(Node head, List<Integer> list) {
		if (null == head && 0 == list.size()) {
			return true;
		}

		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals(head.val)) {
				return false;
			}
			head = head.next;
		}

		return true;
	}

	/**
	 * 对数器
	 */
	public static void test() {
		int len = 500;
		int val = 1000;
		int testTimes = 1000;

		System.out.println("start");

		for (int i = 0; i < testTimes; i++) {
			// 生成随机数组
			Node node1 = generateRandomNode(len, val);
			// 把结点元素放入容器中
			List<Integer> origin1 = getLinkedListOriginOrder(node1);
			// 从集合中随机取一个元素或者随机生成一个元素
			int num1 = Math.random() < 0.5 ? 
					generateRandomNum(val) :
					origin1.get((int) (Math.random() * origin1.size()));
			// 在链表中删除指定值的所有节点
			node1 = deleteGivenValue(node1, num1);
			// 返回一个没有num1的集合
			origin1 = deleteGivenValueList(origin1, num1);
			// 判断集合和单链表元素是否一致
			if (!checkLinkedListIsEqual(node1, origin1)) {
				System.out.println("Oops1");
				break;
			}

			// 生成随机双向链表
			DoubleNode node2 = generateRandomDoubleNode(len, val);
			// 把双向链表各结点元素放入容器中
			List<Integer> origin2 = getDoubleListOriginOrder(node2);
			// 从集合中随机取一个元素或者随机生成一个元素
			int num2 = Math.random() < 0.5 ?
					generateRandomNum(val) :
					origin2.get((int) (Math.random() * origin2.size()));

			// 在链表中删除指定值的所有节点
			node2 = deleteGivenValueDoubleNode(node2, num2);
			// 返回一个没有num2的集合
			origin2 = deleteGivenValueList(origin2, num2);
			// 判断集合和单链表元素是否一致
			if (!checkDoubleListIsEqual(node2, origin2)) {
				System.out.println("Oops2");
				break;
			}
		}

		System.out.println("end");
	}

	/**
	 * main
	 * 
	 * @param args 标准入参
	 */
	public static void main(String[] args) {
		test();
	}
}

package src.class09;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点 
 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个 
 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 */
public class Code01_LinkedListMid {
	/**
	 * 自定义链表结点类
	 */
	private static class Node {
		private int value;
		private Node next;
		
		public Node(int v) {
			value = v;
		}
	}
	
	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
	 * 解决方法: 快慢指针
	 * 
	 * @param head 头结点
	 * @return Node 中点结点或上中点
	 */
	public static Node midOrUpMidNode(Node head) {
		if (head == null ||
			head.next == null ||
			head.next.next == null) {
			return head;
		}
		
		Node slow = head.next;
		Node fast = head.next.next;
		
		while (fast.next != null &&
			   fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
	}
	
	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
	 * 解决方法: 快慢指针
	 * 
	 * @param head 头结点
	 * @return Node 中点结点或下中点
	 */
	public static Node midOrDownMidNode(Node head) {
		if (head == null ||
			head.next == null ||
			head.next.next == null) {
			return head;
		}
		
		Node slow = head.next;
		Node fast = head.next;
		
		while (fast.next != null &&
			   fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
	}
	
	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
	 * 解决方法: 快慢指针
	 * 
	 * @param head 头结点
	 * @return Node 中点前一个或上中点前一个
	 */
	public static Node midOrUpMidPreNode(Node head) {
		if (head == null ||
			head.next == null ||
			head.next.next == null) {
			return head;
		}
		
		Node slow = head;
		Node fast = head.next.next;
		
		while (fast.next != null &&
			   fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
	}
	
	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
	 * 解决方法: 快慢指针
	 * 
	 * @param head 头结点
	 * @return Node 中点前一个或下中点前一个
	 */
	public static Node midOrDownMidPreNode(Node head) {
		if (head == null ||
			head.next == null ||
			head.next.next == null) {
			return head;
		}
		
		Node slow = head;
		Node fast = head.next;
		
		while (fast.next != null &&
			   fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
	}
	
	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
	 * 解题方法: 放入一个集合中
	 * 
	 * @param head 头结点
	 * @return Node 中点结点或上中点
	 */
	public static Node right1(Node head) {
		if (head == null) {
			return head;
		}
		
		List<Node> list = new ArrayList<>();
		
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		
		Node ans = list.get((list.size() - 1) / 2);
		
		return ans;
	}
	
	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
	 * 解题方法: 放入一个集合中
	 * 
	 * @param head 头结点
	 * @return Node 中点结点或下中点
	 */
	public static Node right2(Node head) {
		if (head == null) {
			return head;
		}
		
		List<Node> list = new ArrayList<>();
		
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		
		Node ans = list.get(list.size() / 2);
		
		return ans;
	}
	
	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
	 * 解决方法: 放入一个集合中
	 * 
	 * @param head 头结点
	 * @return Node 中点前一个上中点前一个
	 */
	public static Node right3(Node head) {
		if (head == null) {
			return head;
		}
		
		List<Node> list = new ArrayList<>();
		
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		
		Node ans = list.get((list.size() - 3) / 2);
		
		return ans;
	}
	
	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
	 * 解决方法: 快慢指针
	 * 
	 * @param head 头结点
	 * @return Node 中点前一个或下中点前一个
	 */
	public static Node right4(Node head) {
		if (head == null) {
			return head;
		}
		
		List<Node> list = new ArrayList<>();
		
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		
		Node ans = list.get((list.size() - 2) / 2);
		
		return ans;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		Node test = null;
		test = new Node(0);
		test.next = new Node(1);
		test.next.next = new Node(2);
		test.next.next.next = new Node(3);
		test.next.next.next.next = new Node(4);
		test.next.next.next.next.next = new Node(5);
		test.next.next.next.next.next.next = new Node(6);
		test.next.next.next.next.next.next.next = new Node(7);
		test.next.next.next.next.next.next.next.next = new Node(8);
//		test.next.next.next.next.next.next.next.next.next = new Node(9);

		Node ans1 = null;
		Node ans2 = null;

		ans1 = midOrUpMidNode(test);
		ans2 = right1(test);
		System.out.println(ans1 != null ? ans1.value : "无");
		System.out.println(ans2 != null ? ans2.value : "无");
		
		System.out.println();

		ans1 = solution(test);
		ans2 = right2(test);
		System.out.println(ans1 != null ? ans1.value : "无");
		System.out.println(ans2 != null ? ans2.value : "无");
		
		System.out.println();

		ans1 = solution(test);
		ans2 = right3(test);
		System.out.println(ans1 != null ? ans1.value : "无");
		System.out.println(ans2 != null ? ans2.value : "无");
		
		System.out.println();

		ans1 = solution(test);
		ans2 = right4(test);
		System.out.println(ans1 != null ? ans1.value : "无");
		System.out.println(ans2 != null ? ans2.value : "无");
	}
	public static Node solution(Node head) {
		if (head == null ||
			head.next == null ||
			head.next.next == null) {
			return head;
		}
		
//		Node slow = head.next;
//		Node fast = head.next.next;
		
//		Node slow = head.next;
//		Node fast = head.next;
//		
//		Node slow = head;
//		Node fast = head.next.next;
//		
		Node slow = head;
		Node fast = head.next;
		
		while (fast.next != null && 
			   fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
	}
	public static void main(String[] args) {
		test();
	}
}

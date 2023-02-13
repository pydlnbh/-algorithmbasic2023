package src.class03;

import java.util.ArrayList;
import java.util.List;

/**
 * 反转单链表、反转双链表
 * 
 * @author peiyiding
 *
 */
public class Code01_ReverseList {
	
	/**
	 * 单链表静态类
	 */
	public static class Node {
		int val;
		Node next;
		
		public Node(int val) {
			this.val = val;
		}
	}

	/**
	 * 双向链表静态类
	 */
	public static class DoubleNode {
		int val;
		DoubleNode pre;
		DoubleNode next;
		
		public DoubleNode(int val) {
			this.val = val;
		}
	}
	
	/**
	 * 反转单链表
	 * 
	 * @param head 头结点
	 * @return Node 单链表
	 */
	public static Node reverseLinkedList(Node head) {
		Node pre = null;
		Node next;
		
		while (null != head) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		
		return pre;
	}
		
	/**
	 * 反转双向链表
	 * 
	 * @param head 头结点
	 * @return DoubleNode 双向链表
	 */
	public static DoubleNode reverseDoubleList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next;
		
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.pre = next;
			pre = head;
			head= next;
		}
		
		return pre;
	}
	
	/**
	 * 生成随机链表
	 * 
	 * @param len 链表长度
	 * @param val 数值
	 * @return Node 链表
	 */
	public static Node generateRandomLinkedList(int len, int val) {
		int size = (int) (Math.random() * len) + 1;
		
		if (0 == size) {
			return null;
		}
		
		size--;
					
		Node head = new Node((int) (Math.random() * val) + 1);
		Node pre = head;
		
		while (size != 0) {
			Node cur = new Node((int) (Math.random() * val) + 1);
			pre.next = cur;
			pre = cur;
			size--;
		}
		
		return head;
	}
	
	/**
	 * 把链表转为list
	 * 
	 * @param head 头结点
	 * @return List<Integer> list
	 */
	public static List<Integer> getLinkedListOriginOrder(Node head) {
		List<Integer> ans = new ArrayList<>();
		
		while (null != head) {
			ans.add(head.val);
			head = head.next;
		}
		
		return ans;
	}
	
	/**
	 * 反转单链表校验方法
	 * 
	 * @param list 对比list
	 * @param head 头结点
	 * @return boolean 返回对错
	 */
	public static boolean checkLinkedListReverse(List<Integer> list, Node head) {
		for (int i = list.size() - 1; i >= 0; i--) {
			if (!list.get(i).equals(head.val)) {
				return false;
			}
			head = head.next;
		}
		
		return true;
	}
	
	/**
	 * 反转单链表测试方法
	 * 
	 * @param head 头结点
	 * @return Node 返回头节点
	 */
	public static Node testReverseLinkedList(Node head) {
		if (null == head) {
			return null;
		}
		
		List<Node> list = new ArrayList<>();
		
		while (null != head) {
			list.add(head);
			head = head.next;
		}
		
		list.get(0).next = null;
		
		for (int i = 1; i < list.size(); i++) {
			list.get(i).next = list.get(i - 1);
		}
		
		return list.get(list.size() - 1);
	}
	
	/**
	 * 生成随机双向链表
	 * 
	 * @param len 链表长度
	 * @param val 链表数值
	 * @return DoubleNode 双向链表头结点
	 */
	public static DoubleNode generateRandomDoubleList(int len, int val) {
		int size = (int) (Math.random() * len) + 1;
		
		if (0 == size) {
			return null;
		}
		
		DoubleNode head = new DoubleNode((int) (Math.random() * val));
		DoubleNode pre = head;
				
		size--;
		
		while (size != 0) {
			DoubleNode cur = new DoubleNode((int) (Math.random() * val));
			pre.next = cur;
			cur.pre = pre;
			pre = cur;
			size--;
		}
		
		return head;
	}
	
	/**
	 * 把双向链表转为list
	 * 
	 * @param head 双向链表头结点
	 * @return List<Integer> list
	 */
	public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
		List<Integer> ans = new ArrayList<>();
		
		while (null != null) {
			ans.add(head.val);
			head = head.next;
		}
		
		return ans;
	}
	
	/**
	 * 检查反转双向链表
	 * 
	 * @param list list
	 * @param head 双向链表头结点
	 * @return boolean 检查结果
	 */
	public static boolean checkDoubleListReverse(List<Integer> list, DoubleNode head) {
		DoubleNode end = null;
		
		for (int i = list.size() - 1; i >= 0; i--) {
			if (!list.get(i).equals(head.val)) {
				return false;
			}
			end = head;
			head = head.next;
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals(head.val)) {
				return false;
			}
			end = head.pre;
		}
		
		return true;
	}
	
	/**
	 * 测试反转双向链表
	 * 
	 * @param head 双向链表头结点
	 * @return DoubleNode 反转后的头结点
	 */
	public static DoubleNode testReverseDoubleList(DoubleNode head) {
		if (null == head) {
			return null;
		}
		
		List<DoubleNode> list = new ArrayList<>();
		
		while (null != head) {
			list.add(head);
			head = head.next;
		}
		
		list.get(0).next = null;
		
		DoubleNode pre = list.get(0);
		
		for (int i = 1; i < list.size(); i++) {
			DoubleNode cur = list.get(i);
			cur.pre = null;
			cur.next = pre;
			pre.pre = cur;
			pre = cur;
		}
		
		return list.get(list.size() - 1);
	}
	
	/** 
	 * 对数器
	 */
	public static void test() {
		int len = 50;
		int val = 100;
		int testTimes = 100;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			// 生成随机单链表
			Node node1 = generateRandomLinkedList(len, val);
			// 把随机链表转为list
			List<Integer> list1 = getLinkedListOriginOrder(node1);
			// 反转单链表
			node1 = reverseLinkedList(node1);
			// 检查是否链表反转
			if (!checkLinkedListReverse(list1, node1)) {
				System.out.println("Oops");
			}
			
			// 生成随机单链表
			Node node2 = generateRandomLinkedList(len, val);
			// 把随机链表转为list
			List<Integer> list2 = getLinkedListOriginOrder(node2);
			// 反转单链表对比方法
			node2 = testReverseLinkedList(node2);
			// 检查是否链表反转			
			if (!checkLinkedListReverse(list2, node2)) {
				System.out.println("Oops");
			}
			
			// 生成随机双向链表
			DoubleNode node3 = generateRandomDoubleList(len, val);
			// 把随机链表转为list
			List<Integer> list3 = getDoubleListOriginOrder(node3);
			// 反转双向链表方法
			node3 = reverseDoubleList(node3);
			// 检查是否链表反转
			if (!checkDoubleListReverse(list3, node3)) {
				System.out.println("Oops");
			}
			
			// 生成随机双向链表
			DoubleNode node4 = generateRandomDoubleList(len, val);
			// 把随机链表转为list
			List<Integer> list4 = getDoubleListOriginOrder(node4);
			// 反转双向链表方法
			node4 = testReverseDoubleList(node4);
			// 检查是否链表反转
			if (!checkDoubleListReverse(list4, node4)) {
				System.out.println("Oops");
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

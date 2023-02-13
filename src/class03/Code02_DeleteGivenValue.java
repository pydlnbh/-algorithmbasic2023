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
	 * 链表静态类
	 */
	public static class Node {
		int val;
		Node next;
		
		public Node(int val) {
			this.val = val;
		}
	}
	
	/**
	 * 在链表中删除指定值的所有节点
	 * 
	 * @param head 头结点
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
		for (int i = 0; i < origin.size(); i++) {
			if (value == origin.get(i)) {
				origin.remove(i);
			}
		}
		
		return origin;
	}
	
	/**
	 * 对比删除后的链表和集合是否相等
	 * 
	 * @param head 链表头结点
	 * @param list 集合
	 * @return boolean 对比结果
	 */
	public static boolean isEqual(Node head, List<Integer> list) {
		if (null == head && 0 == list.size()) {
			return true;
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (head.val == list.get(i)) {
				return true;
			}
			head = head.next;
		}
		
		return false;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int len = 10;
		int val = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			// 生成随机数组
			Node node1 = generateRandomNode(len, val);
			// 把结点元素放入容器中
			List<Integer> origin1 = getLinkedListOriginOrder(node1);
			
			int num = origin1.get( (int) (Math.random() * origin1.size()) );
			
			node1 = deleteGivenValue(node1, num);
			origin1 = deleteGivenValueList(origin1, num);
			
			if (!isEqual(node1, origin1)) {
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

package src.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用双链表实现栈和队列
 * 
 * @author peiyiding
 *
 */
public class Code03_DoubleEndsQueueToStackAndQueue {

	/**
	 * 单链表静态类
	 */
	public static class Node<T> {
		public T value;
		public Node<T> last;
		public Node<T> next;
		
		public Node(T data) {
			value = data;
		}
	}
	
	/**
	 * 双向链表静态类
	 */
	public static class DoubleEndsQueue<T> {
		public Node<T> head;
		public Node<T> tail;
		
		public void addFromHead(T val) {
			Node<T> cur = new Node<>(val);
			
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				cur.next = head;
				head.last = cur;
				head = cur;	
			}			
		}
		
		public void addFromTail(T val) {
			Node<T> cur = new Node<>(val);
			
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				cur.last = tail;
				tail = cur;
			}
		}
		
		public T popFromHead() {
			if (head == null) {
				return null;
			}
			
			Node<T> cur = head;
			
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
				cur.next = null;
				head.last = null;
			}
			
			return cur.value;
		}
		
		public T popFromTail() {
			if (head == null) {
				return null;
			}
			
			Node<T> cur = tail;
			
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				tail = tail.last;
				tail.next = null;
				cur.last = null;
			}
			
			return cur.value;
		}
		
		public boolean isEmpty() {
			return head == null;
		}
	}
	
	/**
	 * 双向链表实现栈 (先进后出) 
	 */
	public static class MyStack<T> {
		private DoubleEndsQueue<T> queue;
		
		public MyStack() {
			queue = new DoubleEndsQueue<T>();
		}
		
		public void push(T val) {
			queue.addFromHead(val);
		}
		
		public T pop() {
			return queue.popFromHead();
		}
		
		public boolean isEmpty() {
			return queue.isEmpty();
		}
	}
	
	/**
	 * 双向链表实现队列 (先进先出) 
	 */
	public static class MyQueue<T> {
		private DoubleEndsQueue<T> queue;
		
		public MyQueue() {
			queue = new DoubleEndsQueue<>();
		}
		
		public void push(T val) {
			queue.addFromHead(val);
		}
		
		public T pop() {
			return queue.popFromTail();
		}
		
		public boolean isEmpty() {
			return queue.isEmpty();
		}
	}
	
	/**
	 * 判断包装类是否相等
	 * 
	 * @param num1 对象1
	 * @param num2 对象2
	 * @return boolean 是否相等
	 */
	public static boolean isEqual(Integer num1, Integer num2) {
		return (num1 == num2) || (num1 != null && num1.equals(num2));
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int oneTestDataNum = 100;
		int maxValue = 100;
		int testTimes = 100;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			MyStack<Integer> myStack = new MyStack<>();
			MyQueue<Integer> myQueue = new MyQueue<>();
			
			Stack<Integer> testStack = new Stack<>();
			Queue<Integer> testQueue = new LinkedList<>();
			
			for (int j = 0; j < oneTestDataNum; j++) {
				
				int num0 = (int) (Math.random() * maxValue);
				
				if(myStack.isEmpty()) {
					myStack.push(num0);
					testStack.push(num0);
				} else {
					if (Math.random() < 0.5) {
						if (!isEqual(myStack.pop(),testStack.pop())) {
							System.out.println("Oops1");
							break;
						}
					} else {
						myStack.push(num0);
						testStack.push(num0);
					}
				}
				
				int num1 = (int) (Math.random() * maxValue);
				
				if (myQueue.isEmpty()) {
					myQueue.push(num1);
					testQueue.offer(num1);
				} else {
					if (Math.random() < 0.5) {
						if (!isEqual(myQueue.pop(), testQueue.poll())) {
							System.out.println("Oops2");
							break;
						}
					} else {
						myQueue.push(num1);
						testQueue.offer(num1);
					}
				}
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

package src.class03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用环形数组实现栈和队列
 * 
 * @author peiyiding
 *
 */
public class Code04_RingArray {
	
	/**
	 * 用环形数组实现栈和队列
	 */
	public static class MyQueue {
		private Integer[] arr;
		private int pushI;
		private int popI;
		private int size;
		private final int limit;
		
		public MyQueue(int limit) {
			arr = new Integer[limit];
			pushI = 0;
			popI = 0;
			size = 0;
			this.limit = limit;
		}
		
		public void push(Integer num) {
			if (size > limit) {
				throw new RuntimeException("队列满了");
			}
			size++;
			arr[pushI] = num;
			pushI = nextIndex(pushI);
		}
		
		public Integer poll() {
			if (size == 0) {
				throw new RuntimeException("队列空了");
			}
			size--;
			Integer ans = arr[popI];
			popI = nextIndex(popI);
			return ans;
		}
		
		public int nextIndex(int index) {
			return index < limit - 1 ? ++index : 0;
		}
		
		
		public boolean isEmpty() {
			return size == 0;
		}
	}
	
	/**
	 * 判断包装类是否相等
	 * 
	 * @param a 对象1
	 * @param b 对象2
	 * @return boolean 是否相等
	 */
	public static boolean isEqual(Integer a, Integer b) {
		return (a == b) || (a != null && a.equals(b));
	}
		
	/**
	 * 对数器
	 */
	public static void test() {
		int oneTestDataNum = 10;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			MyQueue myQueue = new MyQueue(oneTestDataNum);
			Queue<Integer> testQueue = new LinkedList<>(); 
			
			for (int j = 0; j < oneTestDataNum; j++) {
				int randomNum = (int) (Math.random() * maxValue);
				
				if (myQueue.isEmpty()) {
					myQueue.push(randomNum);
					testQueue.offer(randomNum);
				} else {
					if (Math.random() < 0.5) {
						myQueue.push(randomNum);
						testQueue.offer(randomNum);
					} else {
						Integer poll1 = myQueue.poll();
						Integer poll2 = testQueue.poll();
						
						if (!isEqual(poll1, poll2)) {
							System.out.println("Oops");
							break;
						}
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

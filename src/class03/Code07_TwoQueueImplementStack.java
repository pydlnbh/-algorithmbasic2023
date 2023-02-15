package src.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 两个队列实现栈 (使用队列实现图的深度优先遍历)
 * 
 * @author peiyiding
 *
 */
public class Code07_TwoQueueImplementStack {
	
	/**
	 * 两个队列实现栈 (使用队列实现图的深度优先遍历)
	 */
	public static class TwoQueueStack<T> {
		private Queue<T> queue;
		private Queue<T> help;
		
		public TwoQueueStack() {
			queue = new LinkedList<>();
			help = new LinkedList<>();
		}
		
		public void push(T num) {
			queue.offer(num);
		}
		
		public T pop() {
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			
			T ans = queue.poll();
			
			Queue<T> t = queue;
			queue = help;
			help = t;
			
			return ans;
		}
		
		public T peek() {
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			
			T ans = queue.poll();
			help.offer(ans);
			
			Queue<T> t = queue;
			queue = help;
			help = t;
			
			return ans;
		}
		
		public boolean isEmpty() {
			return queue.isEmpty();
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
		int maxValue = 100;
		int testTimes = 100;
		int oneTestDataNum = 10;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			TwoQueueStack<Integer> stack01 = new TwoQueueStack<>();
			Stack<Integer> stack02 = new Stack<>();
			
			for (int j = 0; j < oneTestDataNum; j++) {
				int num = (int) (Math.random() * maxValue);
				
				if (stack01.isEmpty()) {
					stack01.push(num);
					stack02.push(num);
				} else {
					double random = Math.random();
					
					if (random < 0.25) {
						stack01.push(num);
						stack02.push(num);
					} else if (random < 0.5) {
						Integer ans1 = stack01.pop();
						Integer ans2 = stack02.pop();
						
						if (!isEqual(ans1, ans2)) {
							System.out.println("Oops1");
							break;
						}
					} else if (random < 0.75) {
						Integer ans3 = stack01.peek();
						Integer ans4 = stack02.peek();
						
						if (!isEqual(ans3, ans4)) {
							System.out.println("Oops2");
							break;
						}
					} else {
						boolean ans5 = stack01.isEmpty();
						boolean ans6 = stack02.isEmpty();
						
						if (ans5 != ans6) {
							System.out.println("Oops3");
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

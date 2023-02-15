package src.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 两个栈实现队列 (使用栈实现图的宽度优先遍历)
 * 
 * @author peiyiding
 *
 */
public class Code06_TwoStackImplementQueue {
	
	/**
	 * 两个栈实现队列 (使用栈实现图的宽度优先遍历)
	 */
	public static class TwoStacksQueue<T> {
		private Stack<T> pushStack;
		private Stack<T> popStack;
		
		public TwoStacksQueue() {
			pushStack = new Stack<>();
			popStack = new Stack<>();
		}
		
		public void pushToPop() {
			if (popStack.isEmpty()) {
				while (!pushStack.isEmpty()) {
					popStack.push(pushStack.pop());
				}
			}
		}
		
		public void add(T num) {
			pushStack.push(num);
			pushToPop();
		}
		
		public T pop() {
			if (pushStack.isEmpty() && popStack.isEmpty()) {
				throw new RuntimeException("栈空了");
			}
			
			pushToPop();
			return popStack.pop();
		}
		
		public T peek() {
			if (pushStack.isEmpty() && popStack.isEmpty()) {
				throw new RuntimeException("栈空了");
			}
			
			pushToPop();
			return popStack.peek();
		}
		
		public boolean isEmpty() {
			return pushStack.isEmpty() && popStack.isEmpty();
		}
	}
	
	/**
	 * 判断包装类型是否相等
	 * 
	 * @param a 对象a
	 * @param b 对象b
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
			TwoStacksQueue<Integer> queue01 = new TwoStacksQueue<>();
			Queue<Integer> queue02 = new LinkedList<>();
			
			for (int j = 0; j < oneTestDataNum; j++) {
				int randomNum = (int) (Math.random() * maxValue);
				
				if (queue01.isEmpty()) {
					queue01.add(randomNum);
					queue02.offer(randomNum);
				} else {
					double random = Math.random();
					if (random < 0.25) {
						queue01.add(randomNum);
						queue02.offer(randomNum);
					} else if (random < 0.5) {
						Integer ans1 = queue01.pop();
						Integer ans2 = queue02.poll();
						if (!isEqual(ans1, ans2)) {
							System.out.println("Oops1");
							break;
						}
					} else if (random < 0.75) {
						Integer ans3 = queue01.peek();
						Integer ans4 = queue02.peek();
						if (!isEqual(ans3, ans4)) {
							System.out.println("Oops2");
							break;
						}
					} else {
						boolean ans5 = queue01.isEmpty();
						boolean ans6 = queue02.isEmpty();
						
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

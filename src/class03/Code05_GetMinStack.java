package src.class03;

import java.util.Stack;

/**
 * 实现有getMin功能的栈
 * 
 * @author peiyiding
 *
 */
public class Code05_GetMinStack {

	/**
	 * 最小栈第一种写法, 最小栈不压重复最小数，最小栈和数据栈size可能不一样
	 */
	public static class MyStack01 {
		private Stack<Integer> stack;
		private Stack<Integer> minStack;
		
		public MyStack01() {
			stack = new Stack<>();
			minStack = new Stack<>();
		}
		
		public void push(Integer val) {
			if (minStack.isEmpty()) {				
				minStack.add(val);
			} else {
				if (val <= getMin()) {
					minStack.push(val);
				}
			}
			stack.push(val);
		}
		
		public int pop() {
			if (stack.isEmpty()) {
				throw new RuntimeException("栈空了");
			}
			
			int val = stack.pop();
			
			if (val == getMin()) {
				minStack.pop();
			}
			
			return val;
		}
		
		public Integer getMin() {
			if (minStack.isEmpty()) {
				throw new RuntimeException("栈空了");
			}
			
			return minStack.peek();
		}
		
		public boolean isEmpty() {
			return stack.isEmpty();
		}
	}
	
	/**
	 * 最小栈第一种写法, 如果不是最小数, 重复把最小栈的栈顶压栈一次, 最小栈和数据栈size一定相等
	 */
	public static class MyStack02 {
		private Stack<Integer> stack;
		private Stack<Integer> minStack;
		
		public MyStack02() {
			stack = new Stack<>();
			minStack = new Stack<>();
		}
		
		public void push(Integer num) {
			if (minStack.isEmpty()) {
				minStack.push(num);
			} else if(num < getMin()) {
				minStack.push(num);
			} else {
				minStack.push(minStack.peek());
			}
			stack.push(num);
		}
		
		public Integer pop() {
			if (stack.isEmpty()) {
				throw new RuntimeException("栈空了");
			}
			
			minStack.pop();
			return stack.pop();
		}
		
		public int getMin() {
			if (minStack.isEmpty()) {
				throw new RuntimeException("栈空了");
			}
			
			return minStack.peek();
		}
		
		public boolean isEmpty() {
			return stack.isEmpty();
		}
	}
	
	public static boolean isEmpty(Integer a, Integer b) {
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
			MyStack01 stack01 = new MyStack01();
			MyStack02 stack02 = new MyStack02();
			
			for (int j = 0; j < oneTestDataNum; j++) {
				int num = (int) (Math.random() * maxValue);
				
				if (stack01.isEmpty() && stack02.isEmpty()) {
					stack01.push(num);
					stack02.push(num);
				} else {
					double random = Math.random();
					if (random < 0.3) {
						stack01.push(num);
						stack02.push(num);
					} else if (random < 0.6) {
						Integer ans1 = stack01.pop();
						Integer ans2 = stack02.pop();
						
						if (!isEmpty(ans1, ans2)) {
							System.out.println("Oops1");
							break;
						}
					} else {
						Integer ans3 = stack01.getMin();
						Integer ans4 = stack02.getMin();
						
						if (!isEmpty(ans3, ans4)) {
							System.out.println("Oops2");
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

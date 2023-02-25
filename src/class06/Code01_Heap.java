package src.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆结构的实现
 * 
 * @author peiyiding
 *
 */
public class Code01_Heap {
	/**
	 * 大根堆实现
	 */	
	public static class Heap {
		private int[] heap;
		private int heapSize;
		private final int limit;
		
		public Heap(int limit) {
			heapSize = 0;
			this.limit = limit;
			heap = new int[limit];
		}
		
		private void heapInsert(int[] arr, int index) {
			while (arr[index] > arr[(index - 1) / 2]) {
				swap(arr, index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}
		
		private void heapify(int[] arr, int index, int heapSize) {
			int left = index * 2 + 1;
			
			while (left < heapSize) {
				int max = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
				max = arr[index] > arr[max] ? index : max;
				
				if (max == index) {
					break;
				}
				
				swap(arr, index, max);
				index = max;
				left = index * 2 + 1;
			}
		}
		
		public void push(int val) {
			if (heapSize == limit) {
				return;
			}
			
			heap[heapSize] = val;
			heapInsert(heap, heapSize++);
		}
		
		public int pop() {
			int ans = heap[0];
			swap(heap, 0, --heapSize);
			heapify(heap, 0, heapSize);
			return ans;
		}
		
		public int peek() {
			return heap[0];
		}
		
		public boolean isEmpty() {
			return heapSize == 0;
		}
		
		private void swap(int[] arr, int i, int j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	/**
	 * 比较器
	 */
	public static class MyCompatator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
		
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int maxSize = 100;
		int maxValue = 1000;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int limit = (int) (Math.random() * maxSize) + 1;
			Heap heap = new Heap(limit);
			PriorityQueue<Integer> testHeap = new PriorityQueue<>(new MyCompatator());
			
			int curOpTimes = (int) (Math.random() * limit);
			
			for (int j = 0; j < curOpTimes; j++) {
				int num = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
				
				if (heap.isEmpty()) {
					heap.push(num);
					testHeap.add(num);
				} else {
					double random = Math.random();
					if (random < 0.25) {
						heap.push(num);
						testHeap.add(num);
					} else if (random < 0.5) {
						int ans1 = heap.pop();
						int ans2 = testHeap.poll();
						
						if (ans1 != ans2) {
							System.out.println("Oops1");
							return;
						}
					} else if (random < 0.75) {
						int ans1 = heap.peek();
						int ans2 = testHeap.peek();
						
						if (ans1 != ans2) {
							System.out.println("Oops2");
							return;
						}
					} else {
						boolean ans1 = heap.isEmpty();
						boolean ans2 = testHeap.isEmpty();
						
						if (ans1 != ans2) {
							System.out.println("Oops3");
							return;
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

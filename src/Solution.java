package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import src.class07.Code02_HeapPlus;

/**
 * 随机题目
 */
public class Solution {
	/**
	 * main
	 * 
	 * @param args 标准入参
	 */
	public static void main(String[] args) {
		randomPractice();
	}
	
	/**
	 * 随机练习题目
	 */
	public static void randomPractice() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("一共学习了几节课: ");
			int iCalss = sc.nextInt();
			
			System.out.println("你今天要练习的课是: " + ((int) (Math.random() * iCalss) + 1));
			
			System.out.print("一节课有几道题: ");
			int iCode = sc.nextInt();
			System.out.println("你今天要做的题目是: " + ((int) (Math.random() * iCode) + 1));
		}
	}
	
	/**
	 * 手写加强堆
	 *
	 * @param <T>
	 */
	public static class HeapPro<T> {
		private ArrayList<T> heap;
		private HashMap<T, Integer> indexMap;
		private int heapSize;
		private Comparator<? super T> comp; 
		
		public HeapPro(Comparator<? super T> comparator) {
			heap = new ArrayList<>();
			indexMap = new HashMap<>();
			heapSize = 0;
			comp = comparator;
		}
		
		public boolean isEmpty() {
			return heapSize == 0;
		}
		
		public int size() {
			return heapSize;
		}
		
		public boolean contains(T obj) {
			return indexMap.containsKey(obj);
		}
		
		public List<T> getAllElement() {
			List<T> ans = new ArrayList<>();
			
			for (int i = 0; i < heapSize; i++) {
				ans.add(heap.get(i));
			}
			
			return ans;
		}
		
		public T peek() {
			return heap.get(0);
		}
		
		public void push(T obj) {
			heap.add(obj);
			indexMap.put(obj, heapSize);
			heapInsert(heapSize++);
		}
		
		public T pop() {
			T ans = heap.get(0);
			swap(0, heapSize - 1);
			indexMap.remove(ans);
			heap.remove(--heapSize);
			heapify(0);
			return ans;
		}
		
		public void remove(T obj) {
			T replace = heap.get(heapSize - 1);
			int index = indexMap.get(obj);
			heap.remove(--heapSize);
			indexMap.remove(obj);
			
			if (replace != obj) {
				heap.set(index, replace);
				indexMap.put(replace, index);
				resign(replace);
			}
		}
		
		public void resign(T obj) {
			heapInsert(indexMap.get(obj));
			heapify(indexMap.get(obj));
		}
		
		private void swap(int i, int j) {
			T o1 = heap.get(i);
			T o2 = heap.get(j);
			
			heap.set(i, o2);
			heap.set(j, o1);
			
			indexMap.put(o1, j);
			indexMap.put(o2, i);
		}
		
		private void heapInsert(int index) {
			while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}
		
		private void heapify(int index) {
			int left = index * 2 + 1;
			while (left < heapSize) {
				int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ?
						   left + 1 :
						   left;
				best = comp.compare(heap.get(index), heap.get(best)) < 0 ? index : best;
				
				if (best == index) {
					break;
				}
				
				swap(index, best);
				
				index = best;
				left = index * 2 + 1;
			}
		}
	}
	
	public static class _Customer {
		private int val;
		private int buy;
		private int time;
		
		public _Customer(int v, int b, int t) {
			val = v;
			buy = b;
			time = 0;
		}
	}
	
	public static class CandidateComp implements Comparator<_Customer> {

		@Override
		public int compare(_Customer o1, _Customer o2) {
			return o1.buy != o2.buy ? o2.buy - o1.buy : o1.time - o2.time;
		}

	}
	
	public static class PrizeComp implements Comparator<_Customer> {
		
		@Override
		public int compare(_Customer o1, _Customer o2) {
			return o1.buy != o2.buy ? o1.buy - o2.buy : o1.time - o2.time;
		}
		
	}
	
	public static List<List<Integer>> solution(int[] arr, boolean[] op, int k) {
		List<List<Integer>> ans = new ArrayList<>();
		
		Process heap = new Process(k);
		
		for (int i = 0; i < arr.length; i++) {
			heap.operate(i, arr[i], op[i]);
			ans.add(heap.getPrizeArea());
		}
		
		return ans;
	}
	
	public static class Process {
		private HashMap<Integer, _Customer> _CustomerMap;
		private Code02_HeapPlus<_Customer> candidateArea;
		private Code02_HeapPlus<_Customer> prizeArea;
		private final int prizeLimit;
		
		public Process(int limit) {
			_CustomerMap = new HashMap<>();
			candidateArea = new Code02_HeapPlus<>(new CandidateComp());
			prizeArea = new Code02_HeapPlus<>(new PrizeComp());
			prizeLimit = limit;
		}
		
		public void operate(int time, int cus, boolean buyOrRefund) {
			if (!buyOrRefund && _CustomerMap.containsKey(cus)) {
				return;
			}
			
			if (!_CustomerMap.containsKey(cus)) {
				_CustomerMap.put(cus, new _Customer(cus, 0, 0));
			}
			
			_Customer cur = _CustomerMap.get(cus);
			
			if (buyOrRefund) {
				cur.buy++;
			} else {
				cur.buy--;
			}
			
			if (cur.buy == 0) {
				_CustomerMap.remove(cus);
			}
			
			if (!candidateArea.contains(cur) && !prizeArea.contains(cur)) {
				if (prizeArea.size() < prizeLimit) {
					cur.time = time;
					prizeArea.push(cur);
				} else {
					cur.time = time;
					candidateArea.push(cur);
				}
			} else if (candidateArea.contains(cur)) {
				if (cur.buy == 0) {
					candidateArea.remove(cur);
				} else {
					candidateArea.resign(cur);
				}
			} else {
				if (cur.buy == 0) {
					prizeArea.remove(cur);
				} else {
					prizeArea.resign(cur);
				}
			}
			
			prizeMove(time);
		}
		
		public List<Integer> getPrizeArea() {
			List<Integer> ans = new ArrayList<>();
			List<_Customer> prizes = prizeArea.getAllElements();
			
			for (_Customer cus : prizes) {
				ans.add(cus.val);
			}
			
			return ans;
		}
		
		public void prizeMove(int time) {
			if (candidateArea.isEmpty()) {
				return;
			}
			
			if (prizeArea.size() < prizeLimit) {
				_Customer cus = candidateArea.pop();
				cus.time = time;
				prizeArea.push(cus);
			} else {
				if (candidateArea.peek().buy > prizeArea.peek().buy) {
					_Customer cus1 = candidateArea.pop();
					_Customer cus2 = prizeArea.pop();
					
					cus1.time = time;
					cus2.time = time;
					
					prizeArea.push(cus1);
					candidateArea.push(cus2);
				}
			}
		}
	
	}
	
}

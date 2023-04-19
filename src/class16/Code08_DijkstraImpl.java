package src.class16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode 743题，可以用这道题来练习Dijkstra算法
//测试链接 : https://leetcode.com/problems/network-delay-time
public class Code08_DijkstraImpl {
	// solution one use priorityQueue
	public static int networkDelayTime1(int[][] times, int n, int k) {
		ArrayList<ArrayList<int[]>> nexts = new ArrayList<>();
		
		for (int i = 0; i <= n; i++) {
			nexts.add(new ArrayList<>());
		}
		
		for (int[] delay : times) {
			nexts.get(delay[0]).add(new int[] {delay[1], delay[2]});
		}
		
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		heap.add(new int[] {k, 0});
		boolean[] used = new boolean[n + 1];
		int num = 0;
		int max = 0;
		
		while (!heap.isEmpty() && num < n) {
			int[] record = heap.poll();
			
			int cur = record[0];
			int delay = record[1];
			
			if (used[cur]) {
				continue;
			}
			
			used[cur] = true;
			num++;
			max = Math.max(max, delay);
			
			for (int[] next : nexts.get(cur)) {
				if (!used[next[0]]) {
					heap.add(new int[] {next[0], delay + next[1]});
				}
			}
		}
		
		return num < n ? -1 : max; 
	}
	
	// solution two use heap plus
	public static int networkDelayTime2(int[][] times, int n, int k) {
		ArrayList<ArrayList<int[]>> nexts = new ArrayList<>();
		
		for (int i = 0; i <= n; i++) {
			nexts.add(new ArrayList<>());
		}
		
		for (int[] delay : times) {
			nexts.get(delay[0]).add(new int[] {delay[1], delay[2]});
		}
		
		HeapPro heap = new HeapPro(n);
		heap.add(k, 0);
		int num = 0;
		int max = 0;
		
		while (!heap.isEmpty()) {
			int[] record = heap.poll();
			
			int cur = record[0];
			int delay = record[1];
			
			num++;
			max = Math.max(max, delay);
			
			for (int[] next : nexts.get(cur)) {
				heap.add(next[0], delay + next[1]);
			}
		}
		
		return num < n ? -1 : max;
	}
	
	public static class HeapPro {
		public int size;
		public int[] index;
		public int[][] nodes;
		public boolean[] used;
		
		public HeapPro(int n) {
			size = 0;
		    index = new int[n + 1];
		    Arrays.fill(index, -1);
		    nodes = new int[n + 1][2];
		    used = new boolean[n + 1];
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public void swap(int i, int j) {
			int[] o1 = nodes[i];
			int[] o2 = nodes[j];
			
			int index1 = index[o1[0]];
			int index2 = index[o2[0]];
			
			index[o1[0]] = index2;
			index[o2[0]] = index1;
			
			nodes[i] = o2;
			nodes[j] = o1;
		}
		
		public void heapInsert(int index) {
			int parent = (index - 1) / 2;
			while (nodes[index][1] < nodes[parent][1]) {
				swap(index, parent);
				index = parent;
				parent = (index - 1) / 2;
			}
		}
		
		public void heapify(int index, int size) {
			int left = index * 2 + 1;
			
			while (left < size) {
				int best = left + 1 < size
				    && nodes[left + 1][1] < nodes[left][1]
				    ? left + 1
				    : left;
				best = nodes[index][1] < nodes[best][1] ? index : best;
				
				if (index == best) {
					break;
				}
				
				swap(index, best);
				index = best;
				left = index * 2 + 1;
			}
		}
		
		public void add(int cur, int delay) {
			if (used[cur]) {
				return;
			}
			
			if (index[cur] == -1) {
				nodes[size][0] = cur;
				nodes[size][1] = delay;
				index[cur] = size;
				heapInsert(size++);
			} else {
				int i = index[cur];
				
				if (delay < nodes[i][1]) {
					nodes[i][1] = delay;
					heapInsert(i);
				}
			}
		}
		
		public int[] poll() {
			int[] ans = nodes[0];
			swap(0, size - 1);
			heapify(0, --size);
			used[ans[0]] = true;
			index[ans[0]] = -1;
			return ans;
		}
	}
}

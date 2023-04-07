package src.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// 岛问题（递归解法 + 并查集解法 + 并行解法）
// 给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，返回matrix中岛的数量
// 测试链接：https://leetcode.com/problems/number-of-islands/
public class Code02_NumberOfIslands {
	// solution one
	public static int numIslands1(char[][] board) {
		int island = 0;
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '1') {
					island++;
					infect(board, i, j);
				}
			}
		}
		
		return island;
	}
	
	// recursive method one 
	public static void infect(char[][] board, int i, int j) {
		if (i < 0 || i == board.length
		 || j < 0 || j == board[0].length
		 || board[i][j] != '1') {
			return;
		}
		
		board[i][j] = 0;
		infect(board, i - 1, j);
		infect(board, i + 1, j);
		infect(board, i, j - 1);
		infect(board, i, j + 1);
	}
	
	// solution two
	public static int numIslands2(char[][] board) {
		int row = board.length;
		int col = board[0].length;
		
		Dot[][] dot = new Dot[row][col];
		List<Dot> dotList = new ArrayList<>();
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == '1') {
					dot[i][j] = new Dot();
					dotList.add(dot[i][j]);
				}
			}
		}
		
		Union<Dot> u = new Union<>(dotList);

		for (int j = 1; j < col; j++) {
			if (board[0][j - 1] == '1' && board[0][j] == '1') {
				u.union(dot[0][j - 1], dot[0][j]);
			}
		}
		
		for (int i = 1; i < row; i++) {
			if (board[i - 1][0] == '1' && board[i][0] == '1') {
				u.union(dot[i - 1][0], dot[i][0]);
			}
		}

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (board[i][j] == '1') {
					if (board[i - 1][j] == '1') {
						u.union(dot[i - 1][j], dot[i][j]);
					}
					
					if (board[i][j - 1] == '1') {
						u.union(dot[i][j - 1], dot[i][j]);
					}
				}
			}
		}
		
		return u.size();
	}
	
	// auxiliary class
	public static class Dot {
		
	}
	
	// auxiliary class
	public static class Node<T> {
		public T val;
		
		public Node(T v) {
			val = v;
		}
	}
	
	// auxiliary class
	public static class Union<T> {
		public Map<T, Node<T>> nodes;
		public Map<Node<T>, Node<T>> parentMap;
		public Map<Node<T>, Integer> sizeMap;
		
		public Union(List<T> values) {
			nodes = new HashMap<>();
			parentMap = new HashMap<>();
			sizeMap = new HashMap<>();
			
			for (T cur : values) {
				Node<T> node = new Node<>(cur);
				nodes.put(cur, node);
				parentMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}
		
		public Node<T> find(Node<T> node) {
			Stack<Node<T>> stack = new Stack<>();
			
			while (node != parentMap.get(node)) {
				stack.push(node);
				node = parentMap.get(node);
			}
			
			while (!stack.isEmpty()) {
				parentMap.put(stack.pop(), node);
			}
			
			return node;
		}
		
		public void union(T a, T b) {
			Node<T> p1 = find(nodes.get(a));
			Node<T> p2 = find(nodes.get(b));
			
			if (p1 != p2) {
				int s1 = sizeMap.get(p1);
				int s2 = sizeMap.get(p2);
				
				Node<T> big = s1 > s2 ? p1 : p2;
				Node<T> small = big == p1 ? p2 : p1;
				
				parentMap.put(small, big);
				sizeMap.put(big, s1 + s2);
				sizeMap.remove(small);
			}
		}
		
		public int size() {
			return sizeMap.size();
		}
	}
	
	// solution three
	public static int numIslands3(char[][] board) {
		int row = board.length;
		int col = board[0].length;
		
		UnionFind u = new UnionFind(board);
		
		for (int j = 1; j < col; j++) {
			if (board[0][j] == '1' && board[0][j - 1] == '1') {
				u.union(0, j, 0, j - 1);
			}
		}
		
		for (int i = 1; i < row; i++) {
			if (board[i][0] == '1' && board[i - 1][0] == '1') {
				u.union(i, 0, i - 1, 0);
			}
		}
		
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (board[i][j] == '1') {
					if (board[i][j - 1] == '1') {
						u.union(i, j - 1, i, j);
					}
					
					if (board[i - 1][j] == '1') {
						u.union(i - 1, j, i, j);
					}
				}
			}
		}
		
		return u.size();
	}
	
	// auxiliary class
	public static class UnionFind {
		public int[] parent;
		public int[] size;
		public int[] help;
		public int sets;
		public int col;
		
		public UnionFind(char[][] board) {
			col = board[0].length;
			sets = 0;
			int row = board.length;
			parent = new int[col * row];
			size = new int[col * row];
			help = new int[col * row];
			
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (board[i][j] == '1') {
						int index = getIndex(i, j);
						parent[index] = index;
						size[index] = 1;
						sets++;
					}
				}
			}
		}
		
		public int getIndex(int i, int j) {
			return i * col + j;
		}
		
		public int find(int i) {
			int index = 0;
			
			while (i != parent[i]) {
				help[index++] = i;
				i = parent[i];
			}
			
			for (index--; index >= 0; index--) {
				parent[help[index]] = i;
			}
			
			return i;
		}
		
		public void union(int r1, int c1, int r2, int c2) {
			int index1 = getIndex(r1, c1);
			int index2 = getIndex(r2, c2);
			int p1 = find(index1);
			int p2 = find(index2);
			
			if (p1 != p2) {
				int s1 = size[p1];
				int s2 = size[p2];
				
				int b = s1 > s2 ? p1 : p2;
				int s = p1 == b ? p2 : p1;
				
				parent[s] = b;
				size[b] = s1 + s2;
				sets--;
			}
		}
		
		public int size() {
			return sets;
		}
	}
	
	// for test
	public static char[][] generateRandomMatrix(int row, int col) {
		char[][] board = new char[row][col];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = Math.random() < 0.5 ? '1' : '0';
			}
		}
		
		return board;
	}
	
	// for test
	public static char[][] copy(char[][] board) {
		int row = board.length;
		int col = board[0].length;
		char[][] ans = new char[row][col];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				ans[i][j] = board[i][j];
			}
		}
		
		return ans;
	}
	
	// for test
	public static void test() {
		int row = 0;
		int col = 0;
		char[][] board1 = null;
		char[][] board2 = null;
		char[][] board3 = null;
		long start = 0;
		long end = 0;
		
		row = 1000;
		col = 1000;
		
		board1 = generateRandomMatrix(row, col);
		board2 = copy(board1);
		board3 = copy(board1);
		
		System.out.println("time test begin");
		System.out.println("generate matrix row: " + row + ", col: " + col + "\n");
		
		start = System.currentTimeMillis();
		System.out.println("recursive method answer: " + numIslands1(board1));
		end = System.currentTimeMillis();
		System.out.println("recursive method cost time: " + (end - start) + "ms\n");
		
		start = System.currentTimeMillis();
		System.out.println("union method one(map) answer: " + numIslands2(board2));
		end = System.currentTimeMillis();
		System.out.println("union method one(map) cost time: " + (end - start) + "ms\n");
		
		start = System.currentTimeMillis();
		System.out.println("union method two(array) answer: " + numIslands3(board3));
		end = System.currentTimeMillis();
		System.out.println("union method two(array) cost time: " + (end - start) + "ms\n");
		
		row = 10000;
		col = 10000;
		board1 = generateRandomMatrix(row, col);
		board2 = copy(board1);
		board3 = copy(board1);
		
		System.out.println("generate matrix row: " + row + ", col: " + col + "\n");
		
		start = System.currentTimeMillis();
		System.out.println("recursive method answer: " + numIslands1(board1));
		end = System.currentTimeMillis();
		System.out.println("recursive method cost time: " + (end - start) + "ms\n");
		
		// too slow
		/*
		 * start = System.currentTimeMillis();
		 * System.out.println("union method one(map) answer: " + numIslands2(board2));
		 * end = System.currentTimeMillis();
		 * System.out.println("union method one(map) cost time: " + (end - start) +
		 * "ms\n");
		 */
		
		start = System.currentTimeMillis();
		System.out.println("union method two(array) answer: " + numIslands3(board3));
		end = System.currentTimeMillis();
		System.out.println("union method two(array) cost time: " + (end - start) + "ms\n");
		
		System.out.println("time test end");
	}
	
	// main
	public static void main(String[] args) {
		test();
	}
}

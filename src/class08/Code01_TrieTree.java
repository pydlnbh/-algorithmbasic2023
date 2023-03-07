package src.class08;

import java.util.HashMap;

/**
 * 前缀树实现
 */
public class Code01_TrieTree {
	
	public static class Node {
		int pass;
		int end;
		public Node[] nexts;
		
		public Node() {
			pass = 0;
			end = 0;
			nexts = new Node[26];
		}
	}
	
	public static class Trie {
		private Node root;
		
		public Trie() {
			root = new Node();
		}
		
		public void insert(String word) {
			if (word == null) {
				return;
			}
			
			char[] str = word.toCharArray();
			Node node = root;
			node.pass++;
			int path = 0;
			
			for (int i = 0; i < str.length; i++) {
				path = str[i] - 'a';
				if (node.nexts[path] == null) {
					node.nexts[path] = new Node();
				}
				node = node.nexts[path];
				node.pass++;
			}
			
			node.end++;
		}
		
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			
			char[] chars = word.toCharArray();
			Node node = root;
			int index = 0;
			
			for (int i = 0; i < chars.length; i++) {
				index = chars[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			
			return node.end;
		}
		
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			
			char[] chars = pre.toCharArray();
			Node node = root;
			int index = 0;
			
			for (int i = 0; i < pre.length(); i++) {
				index = chars[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			
			return node.pass;
		}
		
		public void delete(String word) {
			if (search(word) != 0) {
				char[] chars = word.toCharArray();
				Node node = root;
				node.pass--;
				int path = 0;
				
				for (int i = 0; i < chars.length; i++) {
					path = chars[i] - 'a';
					if (--node.nexts[path].pass == 0) {
						node.nexts[path] = null;
						return;
					}
					node = node.nexts[path];
				}
				node.end--;
			}
		}
	}
	
	public static class Right {
		private HashMap<String, Integer> box;
		
		public Right() {
			box = new HashMap<>();
		}
		
		public void insert(String word) {
			if (!box.containsKey(word)) {
				box.put(word, 1);
			} else {
				box.put(word, box.get(word) + 1);
			}
		}
		
		public void delete(String word) {
			if (box.containsKey(word)) {
				if (box.get(word) == 1) {
					box.remove(word);
				} else {
					box.put(word, box.get(word) - 1); 
				}
			}
		}
		
		public int search(String word) {
			if (!box.containsKey(word)) {
				return 0;
			} else {
				return box.get(word);
			}
		}
		
		public int prefixNumber(String pre) {
			int ans = 0;
			
			for (String cur : box.keySet()) {
				if (cur.startsWith(pre)) {
					ans += box.get(cur);
				}
			}
			
			return ans;
		}
	}
	
	/**
	 * 生成随机字符串数组
	 * 
	 * @param arrLen 字符串长度
	 * @param strLen 字符串大小
	 * @return String[] 字符串数组
	 */
	public static String[] generateRandomStringArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		
		return ans;
	}
	
	/**
	 * 生成随机字符串
	 * 
	 * @param strLen 字符串长度
	 * @return String 字符串
	 */
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 26);
			ans[i] = (char) (97 + value);
		}
		return String.valueOf(ans);
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int arrLen = 100;
		int strLen = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			String[] arr = generateRandomStringArray(arrLen, strLen);
			Trie trie = new Trie();
			Right right = new Right();
			
			for (int j = 0; j < arr.length; j++) {
				double decide = Math.random();
				
				if (decide < 0.25) {
					trie.insert(arr[j]);
					right.insert(arr[j]);
				} else if (decide < 0.5) {
					trie.delete(arr[j]);
					right.delete(arr[j]);
				} else if (decide < 0.75) {
					int ans1 = trie.search(arr[j]);
					int ans2 = trie.search(arr[j]);
					
					if (ans1 != ans2) {
						System.out.println("Oops1");
					}
				} else {
					int ans1 = trie.prefixNumber(arr[j]);
					int ans2 = right.prefixNumber(arr[j]);
					
					if (ans1 != ans2) {
						System.out.println("Oops2");
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

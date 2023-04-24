package src.class19;

import java.util.HashMap;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来 返回需要至少多少张贴纸可以完成这个任务 例子：str =
 * "babac"，arr = {"ba","c","abcd"} ba + ba + c 3 abcd + abcd 2 abcd+ba 2 所以返回2
 */
public class Code03_StickersToSpellWord {
	// solution one
	public static int minStickers1(String[] stickers, String target) {
		int ans = process1(stickers, target);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// recursive method one
	public static int process1(String[] stickers, String target) {
		if (target.length() == 0) {
			return 0;
		}

		int min = Integer.MAX_VALUE;

		for (String first : stickers) {
			String rest = minus(target, first);

			if (rest.length() != target.length()) {
				min = Math.min(min, process1(stickers, rest));
			}
		}

		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

	// String minus method
	public static String minus(String target, String first) {
		int[] count = new int[26];

		for (int i = 0; i < target.length(); i++) {
			count[target.charAt(i) - 'a']++;
		}

		for (int i = 0; i < first.length(); i++) {
			count[target.charAt(i) - 'a']--;
		}

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < 26; i++) {
			if (count[i] > 0) {
				for (int j = 0; j < count[i]; j++) {
					builder.append((char) (i + 'a'));
				}
			}
		}

		return builder.toString();
	}

	// solution two
	public static int minStickers2(String[] stickers, String target) {
		int n = stickers.length;
		int[][] dp = new int[n][26];

		for (int i = 0; i < n; i++) {
			String str = stickers[i];
			for (int j = 0; j < str.length(); j++) {
				dp[i][str.charAt(j) - 'a']++;
			}
		}

		int ans = process2(dp, target);
		return ans == Integer.MAX_VALUE ? -1 : 0;
	}

	// recursive method
	public static int process2(int[][] dp, String target) {
		if (target.length() == 0) {
			return 0;
		}

		int[] count = new int[26];
		for (int i = 0; i < target.length(); i++) {
			count[target.charAt(i) - 'a']++;
		}

		int n = dp.length;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			int[] sticker = dp[i];

			if (sticker[target.charAt(0) - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();

				for (int j = 0; j < 26; j++) {
					if (count[j] > 0) {
						int nums = count[j] - sticker[j];

						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}

				String rest = builder.toString();
				min = Math.min(min, process2(dp, rest));
			}
		}

		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

	// solution three
	public static int minStickers3(String[] stickers, String target) {
		int n = stickers.length;
		int[][] dp = new int[n][26];

		for (int i = 0; i < n; i++) {
			String sticker = stickers[i];

			for (int j = 0; j < sticker.length(); j++) {
				dp[i][sticker.charAt(j) - 'a']++;
			}
		}

		HashMap<String, Integer> map = new HashMap<>();
		map.put("", 0);
		int ans = process3(dp, target, map);

		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// recursive method
	public static int process3(int[][] dp, String target, HashMap<String, Integer> map) {
		if (map.containsKey(target)) {
			return map.get(target);
		}
		
		int[] count = new int[26];
		for (int i = 0; i < target.length(); i++) {
			count[target.charAt(i) - 'a']++;
		}
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < dp.length; i++) {
			int[] sticker = dp[i];
			
			if (sticker[target.charAt(0) - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				
				for (int j = 0; j < 26; j++) {
					if (count[j] > 0) {
						int num = count[j] - sticker[j];
						
						for (int k = 0; k < num; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				
				String rest = builder.toString();
				min = Math.min(min, process3(dp, rest, map));
			}
		}
		
		int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
		map.put(target, ans);
		return ans;
	}

	// main
	public static void main(String[] args) {
		
	}
}

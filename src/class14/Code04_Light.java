package src.class14;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由'X'和'.'两种字符构成 'X'表示墙，不能放灯，也不需要点亮；'.'表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code04_Light {
	// solution one
	public static int light1(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		return process(str.toCharArray(), 0, new HashSet<>());
	}

	// recursive method one
	public static int process(char[] chars, int index, HashSet<Integer> lights) {
		// base case
		if (index == chars.length) {
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] != 'x') {
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}

			return lights.size();
		} else {
			int no = process(chars, index + 1, lights);
			int yes = Integer.MAX_VALUE;

			if (chars[index] == '.') {
				lights.add(index);
				yes = process(chars, index + 1, lights);
				lights.remove(index);
			}

			return Math.min(no, yes);
		}
	}

	// solution two
	public static int light2(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		char[] chars = str.toCharArray();

		int index = 0;
		int light = 0;

		while (index < chars.length) {
			if (chars[index] == 'x') {
				index++;
			} else {
				light++;

				if (index == chars.length - 1) {
					break;
				} else {
					if (chars[index + 1] == 'x') {
						index = index + 2;
					} else {
						index = index + 3;
					}
				}
			}
		}

		return light;
	}

	// solution three
	public static int light3(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		char[] chars = str.toCharArray();

		int cur = 0;
		int light = 0;

		for (char c : chars) {
			if (c == 'x') {
				light += (cur + 2) / 3;
				cur = 0;
			} else {
				cur++;
			}
		}

		light += (cur + 2) / 3;
		return light;
	}

	// for test
	public static String generateString(int maxLength) {
		int length = (int) (Math.random() * maxLength);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			String cur = Math.random() < 0.5 ? "x" : ".";
			sb.append(cur);
		}

		return sb.toString();
	}

	// for test
	public static void test() {
		int maxLength = 10;
		int testTimes = 100;

		System.out.println("start");

		for (int i = 0; i < testTimes; i++) {
			String str = generateString(maxLength);

			int ans1 = light1(str);
			int ans2 = light2(str);
			int ans3 = light3(str);

			if (ans1 != ans2
				|| ans2 != ans3) {
				System.out.println("str = " + str);
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2 + "ans3 = " + ans3);
				System.out.println("Oops");
				break;
			}
		}

		System.out.println("end");
	}

	// main
	public static void main(String[] args) {
		test();
	}
}

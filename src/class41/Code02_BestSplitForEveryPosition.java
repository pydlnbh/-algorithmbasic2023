package src.class41;

/**
 * 把题目一中提到的，min{左部分累加和，右部分累加和}，定义为S(N-1)，也就是说：
 * S(N-1)：在arr[0…N-1]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值 
 * 现在要求返回一个长度为N的s数组， s[i] = 在arr[0…i]范围上，
 * 做最优划分所得到的min{左部分累加和，右部分累加和}的最大值 得到整个s数组的过程，做到时间复杂度O(N)
 */
public class Code02_BestSplitForEveryPosition {
	// solution one
	public static int[] bestSplit1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		
		int N = arr.length;
		int[] ans = new int[N];
		ans[0] = 0;
		
		for (int range = 1; range < N; range++) {
			for (int s = 0; s < range; s++) {
				int sumL = 0;
				
				for (int L = 0; L <= s; L++) {
					sumL += arr[L];
				}
				
				int sumR = 0;
				
				for (int R = s + 1; R <= range; R++) {
					sumR += arr[R];
				}
				
				ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
			}
		}
		
		return ans;
	}
	
	// solution two
	public static int[] bestSplit2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		
		int N = arr.length;
		int[] ans = new int[N];
		ans[0] = 0;
		int[] sum = new int[N + 1];
		
		for (int i = 0; i < N; i++) {
			sum[i + 1] = sum[i] + arr[i];
		}
		
		for (int range = 1; range < N; range++) {
			for (int s = 0; s < range; s++) {
				int sumL = sum(sum, 0, s);
				int sumR = sum(sum, s + 1, range);
				ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
			}
		}
		
		return ans;
	}
	
	public static int sum(int[] sum, int L, int R) {
		return sum[R + 1] - sum[L];
	}
	
	// solution three
	public static int[] bestSplit3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		
		int N = arr.length;
		int[] ans = new int[N];
		ans[0] = 0;
		
		int[] sum = new int[N + 1];
		for (int i = 0; i < N; i++) {
			sum[i + 1] = sum[i] + arr[i];
		}
		
		int best = 0;
		
		for (int range = 1; range < N; range++) {
			while (best + 1 < range) {
				int before = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
				int after = Math.min(sum(sum, 0, best + 1), sum(sum, best + 2, range));
				
				if (after >= before) {
					best++;
				} else {
					break;
				}
			}
			ans[range] = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
		}
		
		return ans;
	}
	
	public static int[] randomArray(int len, int max) {
		int[] ans = new int[len];
		for (int i = 0; i < len; i++) {
			ans[i] = (int) (Math.random() * max);
		}
		return ans;
	}

	public static boolean isSameArray(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		int N = arr1.length;
		for (int i = 0; i < N; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int N = 20;
		int max = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N);
			int[] arr = randomArray(len, max);
			int[] ans1 = bestSplit1(arr);
			int[] ans2 = bestSplit2(arr);
			int[] ans3 = bestSplit3(arr);
			if (!isSameArray(ans1, ans2) || !isSameArray(ans1, ans3)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束");
	}
}

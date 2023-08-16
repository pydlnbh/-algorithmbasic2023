package src.class45;

import src.class44.DC3;

//测试链接: https://leetcode.com/problems/create-maximum-number/
public class Code02_CreateMaximumNumber {
	// solution one
	public static int[] maxNumber1(int[] nums1, int[] nums2, int k) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		
		if (k < 0 || k > len1 + len2) {
			return null;
		}
		
		int[] res = new int[k];
		int[][] dp1 = getdp(nums1);
		int[][] dp2 = getdp(nums2);
		
		for (int get1 = Math.max(0, k - len2); get1 <= Math.min(k, len1); get1++) {
			int[] pick1 = maxPick(nums1, dp1, get1);
			int[] pick2 = maxPick(nums2, dp2, k - get1);
			int[] merge = merge(pick1, pick2);
			
			res = preMoreThanLast(res, 0, merge, 0) ? res : merge;
		}
		
		return res;
	}
	
	public static int[] merge(int[] nums1, int[] nums2) {
		int k = nums1.length + nums2.length;
		int[] ans = new int[k];
		
		for (int i = 0, j = 0, r = 0; r < k; ++r) {
			ans[r] = preMoreThanLast(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
		}
		
		return ans;
	}
	
	public static boolean preMoreThanLast(int[] nums1, int i, int[] nums2, int j) {
		while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
			i++;
			j++;
		}
		
		return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
	}
	
	public static int[] maxNumber2(int[] nums1, int[] nums2, int k) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		
		if (k < 0 || k > len1 + len2) {
			return null;
		}
		
		int[] res = new int[k];
		int[][] dp1 = getdp(nums1);
		int[][] dp2 = getdp(nums2);
		
		for (int get1 = Math.max(0, k - len2); get1 <= Math.min(k, len1); get1++) {
			int[] pick1 = maxPick(nums1, dp1, get1);
			int[] pick2 = maxPick(nums2, dp2, k - get1);
			int[] merge = mergeBySuffixArray(pick1, pick2);
			res = moreThan(res, merge) ? res : merge;
		}
		
		return res;
	}
	
	public static boolean moreThan(int[] pre, int[] last) {
		int i = 0;
		int j = 0;
		
		while (i < pre.length && j < last.length && pre[i] == last[j]) {
			i++;
			j++;
		}
		
		return j == last.length || (i < pre.length && pre[i] > last[j]);
	}
	
	public static int[] mergeBySuffixArray(int[] nums1, int[] nums2) {
		int size1 = nums1.length;
		int size2 = nums2.length;
		
		int[] nums = new int[size1 + 1 + size2];
		for (int i = 0; i < size1; i++) {
			nums[i] = nums1[i] + 2;
		}
		
		nums[size1] = 1;
		
		for (int j = 0; j < size2; j++) {
			nums[j + size1 + 1] = nums2[j] + 2;
		}
		
		DC3 dc3 = new DC3(nums, 11);
		int[] rank = dc3.rank;
		int[] ans = new int[size1 + size2];
		
		int i = 0;
		int j = 0;
		int r = 0;
		
		while (i < size1 && j < size2) {
			ans[r++] = rank[i] > rank[j + size1 + 1] ? nums1[i++] : nums2[j++];
		}
		
		while (i < size1) {
			ans[r++] = nums1[i++];
		}
		
		while (j < size2) {
			ans[r++] = nums2[j++];
		}
		
		return ans;
	}
	
	public static int[][] getdp(int[] arr) {
		int size = arr.length;
		int pick = arr.length + 1;
		int[][] dp = new int[size][pick];
		
		for (int get = 1; get < pick; get++) {
			int maxIndex = size - get;
			
			for (int i = size - get; i >= 0; i--) {
				if (arr[i] >= arr[maxIndex]) {
					maxIndex = i;
				}
				
				dp[i][get] = maxIndex;
			}
		}
		
		return dp;
	}
	
	public static int[] maxPick(int[] arr, int[][] dp, int pick) {
		int[] res = new int[pick];
		
		for (int resIndex = 0, dpRow = 0; pick > 0; pick--, resIndex++) {
			res[resIndex] = arr[dp[dpRow][pick]];
			dpRow = dp[dpRow][pick] + 1;
		}
		
		return res;
	}
}

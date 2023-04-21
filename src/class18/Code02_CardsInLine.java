package src.class18;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线 
 * 玩家A和玩家B依次拿走每张纸牌 
 * 规定玩家A先拿，玩家B后拿 
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明 请返回最后获胜者的分数
 */
public class Code02_CardsInLine {
	// solution one
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		int first = f1(arr, 0, arr.length - 1);
		int second = g1(arr, 0, arr.length - 1);
		
		return Math.max(first, second);
	}
	
	// recursive method one
	public static int f1(int[] arr, int left, int right) {
		if (left == right) {
			return arr[left];
		}
		
		int p1 = arr[left] + g1(arr, left + 1, right);
		int p2 = arr[right] + g1(arr, left, right - 1);
		
		return Math.max(p1, p2);
	}
	
	// recursive method two
	public static int g1(int[] arr, int left, int right) {
		if (left == right) {
			return 0;
		}
		
		int p1 = f1(arr, left + 1, right);
		int p2 = f1(arr, left, right - 1);
		
		return Math.min(p1, p2);
	}
	
	// solution two
	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		int n = arr.length;
		int[][] fdp = new int[n][n];
		int[][] gdp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				fdp[i][j] = -1;
				gdp[i][j] = -1;
			}
		}
		
		int first = f2(arr, 0, arr.length - 1, fdp, gdp);
		int second = g2(arr, 0, arr.length - 1, fdp, gdp);
		
		return Math.max(first, second);
	}
	
	// recursive method two
	public static int f2(int[] arr, int left, int right, int[][] fdp, int[][] gdp) {
		if (fdp[left][right] != -1) {
			return fdp[left][right];
		}
	
		int ans = 0;
		if (left == right) {
			ans = arr[left];
		} else {
			int p1 = arr[left] + g2(arr, left + 1, right, fdp, gdp);
			int p2 = arr[right] + g2(arr, left, right - 1, fdp, gdp);
			ans = Math.max(p1, p2);
		}
		
		fdp[left][right] = ans;
		return ans;
	}
	
	// recursive method two
	public static int g2(int[] arr, int left, int right, int[][] fdp, int[][] gdp) {
		if (gdp[left][right] != -1) {
			return gdp[left][right];
		}
		
		int ans = 0;
		if (left != right) {
			int p1 = f2(arr, left + 1, right, fdp, gdp);
			int p2 = f2(arr, left, right - 1, fdp, gdp);
			ans = Math.min(p1, p2);
		}
		
		gdp[left][right] = ans;
		return ans;
	}
	
	// recursive method three
	public static int win3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		int n = arr.length;
		int[][] fdp = new int[n][n];
		int[][] gdp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			fdp[i][i] = arr[i];
		}
		
		for (int startCol = 1; startCol < n; startCol++) {
			int left = 0;
			int right = startCol;
			
			while (right < n) {
				fdp[left][right] = Math.max(arr[left] + gdp[left + 1][right], arr[right] + gdp[left][right - 1]);
				gdp[left][right] = Math.min(fdp[left + 1][right], fdp[left][right - 1]);
				left++;
				right++;
			}
		}
		
		return Math.max(fdp[0][n - 1], gdp[0][n - 1]);
	}
	
	// main
	public static void main(String[] args) {
		int[] arr1 = {1, 100, 1};
		int ans1 = win1(arr1);
		System.out.println(ans1);
		
		System.out.println("===");
		
		int[] arr2 = {1, 100, 1};
		int ans2 = win2(arr2);
		System.out.println(ans2);
		
		System.out.println("===");
		
		int[] arr3 = {1, 100, 1};
		int ans3 = win3(arr3);
		System.out.println(ans3);
	}
}

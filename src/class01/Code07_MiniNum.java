package src.class01;

/**
 * 局部最小值问题
 * 定义何为局部最小值：
 * arr[0] < arr[1]，0位置是局部最小；
 * arr[N-1] < arr[N-2]，N-1位置是局部最小；
 * arr[i-1] > arr[i] < arr[i+1]，i位置是局部最小；
 * 给定一个数组arr，已知任何两个相邻的数都不相等，找到随便一个局部最小位置返回
 * 
 * @author peiyiding
 *
 */
public class Code07_MiniNum {
	
	/**
	 * 局部最小值问题
	 * 
	 * @param arr 数组
	 */
	public static int miniNumIndex(int[] arr) {
		if (null == arr ||
			0 == arr.length) {
			return -1;
		}
		
		if (1 == arr.length || arr[0] < arr[1]) {
			return 0;
		}
		
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}
		
		int left = 1;
		int right = arr.length - 2;
		int mid;
		int index = -1;
		
		while (left <= right) {
			mid = left + ((right - left) >> 1);
			
			if (arr[mid - 1] > arr[mid] && arr[mid + 1] > arr[mid]) {
				index = mid;
				break;
			} else {
				if (arr[mid - 1] > arr[mid]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int maxSize = 100;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			
			int iAns = miniNumIndex(arr);
//			boolean bAns = testMiniNumIndex(arr, iAns);
			boolean bAns = check(arr, iAns);
			
			if (!bAns) {
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
	
	/**
	 * 生成随机数组, 要求相邻不连续
	 * 
	 * @param maxSize 数组最大长度
	 * @param maxValue 数组元素最大值
	 * @return int[] 随机数组
	 */
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxSize) + 1];
		
		int num = generateRandomNum(maxValue);
		
		arr[0] = num;
		
		for (int i = 1; i < arr.length; i++) {
			do {
				arr[i] = generateRandomNum(maxValue);;
			} while (arr[i - 1] == arr[i]);
		}
		
		return arr;
	}
	
	/**
	 * 生成随机数值
	 * 
	 * @param maxValue 最大值
	 * @return int 随机数值
	 */
	public static int generateRandomNum(int maxValue) {
		return ((int) (Math.random() * maxValue)) - ((int) (Math.random() * maxValue));
	}
	
	/**
	 * 测试index位置下标最小值方法
	 * 
	 * @param arr 数组
	 * @param index 最小值下标
	 * @return boolean 是否该下标的值是局部最小值
	 */
	public static boolean testMiniNumIndex(int[] arr, int index) {
		if (1 == arr.length && 0 == index) {
			return true;
		}
		
		if (arr[0] < arr[1] && 0 == index) {
			return true;
		}
		
		if (arr[arr.length - 1] < arr[arr.length - 2] && index == arr.length - 1) {
			return true;
		}
		
		if (arr[index] < arr[index - 1] && arr[index] < arr[index + 1]) {
			return true;
		}
			
		return false;
	}
	
	/**
	 * 测试方法另一种写法
	 * 
	 * @param arr 数组
	 * @param index 最小值下标
	 * @return boolean 测试结果
	 */
	public static boolean check(int[] arr, int index) {
		if (0 == arr.length) {
			return index == -1;
		}
		
		int left = index - 1;
		int right = index + 1;
		
		boolean leftBigger = left >= 0 ? arr[left] > arr[index] : true;
		boolean rightBigger = right <= arr.length - 1 ? arr[right] > arr[index] : true;
		
		return leftBigger && rightBigger;
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

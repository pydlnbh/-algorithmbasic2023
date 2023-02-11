package src.class02;

/**
 * 不用额外变量交换两个数的值
 * 不用额外变量交换数组中两个数的值
 * 
 * @author peiyiding
 *
 */
public class Code01_Swap {
	
	/**
	 * 不用额外变量交换两个数的值
	 * 
	 * @param i 第一个数
	 * @param j 第二个数
	 */
	public static void swapAndPrint(int i, int j) {
		i = i ^ j;
		j = i ^ j;
		i = i ^ j;
		
		System.out.println("i = " + i + ", j = " + j);
	}
	
	/**
	 * 不用额外变量交换数组中两个数的值
	 * 
	 * @param i 下标1
	 * @param j 下标2
	 */
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
	
	/**
	 * 测试方法
	 */
	public static void test() {
		int i = 66;
		int j = 88;
		
		swapAndPrint(i, j);
		
		i = 77;
		j = 77;
		
		swapAndPrint(i, j);
		
		int[] arr = {66, 88};
		swap(arr, 0, 1);
		System.out.println("arr[0] = " + arr[0] + ", arr[1] = " + arr[1]);
		
		swap(arr, 0, 0);
		System.out.println("arr[0] = " + arr[0]);
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

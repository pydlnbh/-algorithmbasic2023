package src;

import java.util.*;

public class Solution {
	// print array
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			String comma = i == arr.length - 1 ? "\n" : ", ";
			System.out.print(arr[i] + comma);
		}
	}

	// print random number
	public static void printRandomNumber(int maxValue) {
		System.out.println((int) (Math.random() * maxValue) + 1);
	}

	// main
	public static void main(String[] args) {
		if (args.length < 1 ||
			Objects.isNull(args)) {
			System.out.println("please input args (type: int)");
			return;
		}
		printRandomNumber(Integer.valueOf(args[0]));
	}

	// Talk is cheap.Show me your code
	// TODO
	
}

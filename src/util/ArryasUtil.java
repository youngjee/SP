package util;

public class ArryasUtil {
	public static void main(String[] args) {
		int[] nums = { 4, 2, 3, 6, 8, 7, 5 };
		System.out.println(getMaxNumInArray(nums)[0]);
		
		int[] result = rotateArray(nums, 3);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+" ");
		}
	}

	// 배열 중 가장 큰 수의 값, indx 가져옴
	public static int[] getMaxNumInArray(int[] nums) {

		int maxInx = 0;
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > max) {
				max = nums[i];
				maxInx = i;
			}
		}

		int[] result = { max, maxInx };
		return result;
	}

	public static int[] rotateArray(int[] nums, int rotate) {
		int[] newArr = new int[nums.length];

		for (int x = 0; x <= nums.length - 1; x++) {
			newArr[(x + rotate) % nums.length] = nums[x];
		}
		
		return newArr;
	}

}

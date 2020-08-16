package data;

public class BinarySearch {
	public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
 
        binarySearch(2, arr);
    }
 
    public static void binarySearch(int iKey, int arr[]) {
    	int left = 0;
    	int right = arr[arr.length-1];

    	while(left<=right) {
    		int mid = (left+right)/2;
    		if(arr[mid] == iKey) {
    			System.out.println(mid);
    			break;
    		}else {
    			if(arr[mid]<iKey) {
    				left = mid+1;
    			}else {
    				right = mid-1;
    			}
    		}
    	}
    }
    
}

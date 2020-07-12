package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class ListUtil {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for (char c = 'A'; c <='Z'; c++) {
			list.add(String.valueOf(c));
		}
		printList(rotateList(list, 3), false);
	}
	
	public static List<String> rotateList(List<String> list, int rotateNum) {
		Collections.rotate(list, rotateNum);
		return list;
	}

	public static void printList(List<String> list, boolean enter) {
		
		if(enter) {
			for(String str:list){
				System.out.println(str);
			}
		}else{
			StringBuffer sb = new StringBuffer();
			for(String str:list){
				sb.append(str+" ");
			}
			sb.append("\n");
			System.out.println(sb.toString());
		}
		
	}
	
}

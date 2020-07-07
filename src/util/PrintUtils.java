package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//print util: array, list, map, object를 print해서 보여줌.
//main함수 아래쪽에 있음.
public class PrintUtils {

	public static void print(String[] info) {
		System.out.println(Arrays.toString(info));
	}

	public static void print(int[] info) {
		System.out.println(Arrays.toString(info));
	}

	public static void print(String[][] info) {
		System.out.println(Arrays.deepToString(info));
	}

	public static void println(String[][] info) {
		for (int i = 0; i < info.length; i++) {
			System.out.println(Arrays.toString(info[i]));
		}
	}

	public static void println(int[][] info) {
		for (int i = 0; i < info.length; i++) {
			System.out.println(Arrays.toString(info[i]));
		}
	}

	/**
	 * @param info
	 */
	public static void print(Object info) {
		// String newLine = System.getProperty("line.separator");

		if (info instanceof List) {
			List<Object> list = (List) info;
			if (list.get(0) instanceof String || list.get(0) instanceof Integer || list.get(0) instanceof Boolean
					|| list.get(0) instanceof Character) {
				System.out.println(info);
			} else {
				System.out.println("{");
				for (Object obj : list) {
					System.out.println("	" + toString(obj));
				}
				System.out.println("}");
			}
		} else if (info instanceof Map) {
			Map map = (Map) info;
			System.out.println("{");
			for (Object key : map.keySet()) {
				System.out.print(" " + key + " :");
				if (map.get(key) instanceof String || map.get(key) instanceof Integer || map.get(key) instanceof Boolean
						|| map.get(key) instanceof Character) {
					System.out.println(map.get(key));
				} else {
					print(map.get(key));
				}

			}
			System.out.println("}");
		} else {
			// 보통의 object의 경우,
			System.out.println(toString(info));
		}

	}

	public static String toString(Object obj) {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		// result.append( obj.getClass().getName() );
		result.append("{ ");
		// result.append(newLine);

		// determine fields declared in this class only (no fields of superclass)
		Field[] fields = obj.getClass().getDeclaredFields();

		// print field names paired with their values
//		  for ( Field field : fields  ) {
		for (int i = 0; i < fields.length; i++) {
			// result.append(" ");
			try {
//				result.append(fields[i].getName());
//				result.append(": ");
				// requires access to private field:
				result.append(fields[i].get(obj));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			// result.append(newLine);
			if (i != fields.length - 1)
				result.append(", ");
		}
		result.append(" }");

		return result.toString();
	}
	

	public static void main(String[] args) {
		String[] aaa = { "aaa", "Bbb" };
		PrintUtils.print(aaa);
		List<String> info = new ArrayList<String>();
		info.add("aaa");
		info.add("bbb");
		PrintUtils.print(info);

		System.out.println();
		System.out.println("object출력");
		PrintUtils.print(new PrintUtils("Abc", "def"));

		System.out.println();
		System.out.println("list object 출력(getter가 존재할때)");
		List<PrintUtils> list = new ArrayList<PrintUtils>();
		list.add(new PrintUtils("abcd", "ddde"));
		list.add(new PrintUtils("aa", "eeeee"));
		PrintUtils.print(list);

		System.out.println();
		System.out.println("map<string, object> 출력");
		Map<String, PrintUtils> map = new HashMap<String, PrintUtils>();
		map.put("info1", new PrintUtils("ab111d", "ddde11"));
		map.put("info2", new PrintUtils("ab22d", "ddde222"));
		PrintUtils.print(map);

		System.out.println();
		System.out.println("map<string, list> 출력");
		Map<String, List<PrintUtils>> map2 = new HashMap<String, List<PrintUtils>>();
		List<PrintUtils> list1 = new ArrayList<PrintUtils>();
		list.add(new PrintUtils("ab111d", "ddde11"));
		list.add(new PrintUtils("a11a", "eee11ee"));
		map2.put("info111", list);

		List<PrintUtils> list2 = new ArrayList<PrintUtils>();
		list2.add(new PrintUtils("ab2222cd", "dd22de"));
		list2.add(new PrintUtils("a22a", "eeee22e"));
		map2.put("info222", list2);

		PrintUtils.print(map2);
	}


	public PrintUtils(String printId, String printName) {
		super();
		this.printId = printId;
		this.printName = printName;
	}

	private String printId;
	private String printName;

	public String getPrintId() {
		return printId;
	}

	public void setPrintId(String printId) {
		this.printId = printId;
	}

	public String getPrintName() {
		return printName;
	}

	public void setPrintName(String printName) {
		this.printName = printName;
	}

}

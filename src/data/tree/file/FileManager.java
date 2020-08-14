package data.tree.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileManager {
	public static void main(String[] args) {
		FileManager fm = new FileManager();
		fm.readFile();
		fm.printInfo(fm.oneInput());
		fm.printChildEffectMap();
	}

	// 1개 입력받기
	public String oneInput() {
		Scanner scanner = new Scanner(System.in);
		// 값 입력 전에 Console에 표시할 내용. println()이 아니고 print()임.
		System.out.print("VALUE : ");
		// 값 받기
		String value = scanner.nextLine();
		scanner.close();
		// 값 처리
		
		return value;
	}

	public void readFile() {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader("./INPUT/trees.txt");
			bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				// [[line 값 처리]]
				processLine(line);
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (Exception ex) {
					/* Do Nothing */ }
			if (fileReader != null)
				try {
					fileReader.close();
				} catch (Exception ex) {
					/* Do Nothing */ }
		}

	}

	private Map<String, Node> trees = new HashMap<String, Node>();

	public void processLine(String line) {
		String[] arr = line.split(" ");
		String parent = arr[0];
		String child = arr[1];

		Node node = trees.getOrDefault(parent, new Node(parent));
		Node childNode = trees.getOrDefault(child, new Node(child));

		childNode.setNodeId(child);
		childNode.setParentId(parent);

		node.getChildNodes().add(childNode);
		
		trees.put(parent, node);
		trees.put(child, childNode);
	}
	
	public void printInfo(String input) {
		if(!trees.containsKey(input)) {
			System.out.println("There is no node.");
			return;
		}
		
		Node node = trees.get(input);
		System.out.println("child 노드 개수 : " + node.getChildNodes().size());
		System.out.println("Child nodes: ");
		for (int i = 0; i < node.getChildNodes().size(); i++) {
			System.out.println(node.getChildNodes().get(i));
		}
		
		System.out.println("하위 총 노드 개수 : "+getTotalChilds(input, input));
		
	}
	
	Map<String, Integer> childEffectMap = new HashMap<String, Integer>();
	
	public int getTotalChilds(String key, String firstKey) {
		if(trees.get(key).getChildNodes().size()==0) {
			childEffectMap.put(key, 0);
			return 0;
		}else {
			Node node = trees.get(key);
			int cnt = node.getChildNodes().size();
			for (int i = 0; i < node.getChildNodes().size(); i++) {
				cnt += getTotalChilds(node.getChildNodes().get(i).getNodeId(), firstKey);
			}
			childEffectMap.put(key, cnt);
			return cnt;
		}
	}
	
	public void printChildEffectMap() {
		String[] keys = new String[childEffectMap.size()];
		childEffectMap.keySet().toArray(keys);
		Arrays.sort(keys);
		for (int i = 0; i < keys.length; i++) {
			System.out.println(keys[i]+": "+childEffectMap.get(keys[i]));
		}
	}
}

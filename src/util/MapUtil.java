package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUtil {
	//Map의 key값을 list로 정렬하여 출력하는 함수
	public static void sortByKey(Map map) {
		
		/*Map<String,Integer> tm = new TreeMap<String,Integer>(map);
		Iterator<String> iteratorKey = tm.keySet( ).iterator( );   //키값 오름차순 정렬(기본)
		*/
		 //내림차순
		 TreeMap<String, Integer> tm = new TreeMap<String, Integer>(Collections.reverseOrder());
		 tm.putAll(map);
		 
		 Iterator<String> iteratorKey = tm.keySet( ).iterator( );
		while(iteratorKey.hasNext()) {
			String key = iteratorKey.next();
		  System.out.println(key+","+tm.get(key));
		 }
    }
	
	public static List sortByValue(final Map map) {

        List<String> list = new ArrayList();
        list.addAll(map.keySet());
        
        Collections.sort(list,new Comparator() {
            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                return ((Comparable) v2).compareTo(v1);
            }

        });
        Collections.reverse(list); // 주석시 오름차순
        return list;
    }
	
	public static void main(String[] args) {
		Map<String,Integer> hashMap = new HashMap<String,Integer>();

		hashMap.put("abc",99);
		hashMap.put("bag",70);
		hashMap.put("cat",89);
		hashMap.put("hotdog",79);
		hashMap.put("dog",99);
		
		/*Map<Double,Integer> hashMap = new HashMap<Double,Integer>();

		hashMap.put(1.1,99);
		hashMap.put(2.2,70);
		hashMap.put(13.3,89);
		hashMap.put(7.7,79);
		hashMap.put(10.1,99);*/
		sortByKey(hashMap);
		
		System.out.println("------------sort 전 -------------");

        Iterator iterator = hashMap.keySet().iterator();
        while(iterator.hasNext()) {
            String temp = (String) iterator.next();
            System.out.println(temp + " = " + hashMap.get(temp));

        }

        Iterator it = sortByValue(hashMap).iterator();
        System.out.println("------------sort 후 -------------");

        while(it.hasNext()) {
            String temp = (String) it.next();
            System.out.println(temp + " = " + hashMap.get(temp));
        }
	}
	


}

package data;

import java.util.HashMap;
import java.util.Map;

public class MapSample {
	public static void main(String[] args) {
		Map<String, Integer> map=new HashMap<String, Integer>();
	    //임의로 여러개의 KEY값을 MAP 객체에 PUT
	    for(int i=0;i<3;i++){
	        map.put("key"+i, i+24);
	    }
	    
	    //MAP의 KEY값을 이용하여 VALUE값 가져오기
	    for (String mapkey : map.keySet()){
	        System.out.println("key:"+mapkey+",value:"+map.get(mapkey));
	    }

	}
}

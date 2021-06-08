package reflection.gettersetter;

import java.lang.reflect.Method;

public class ReflectionGetSet {
	public static void main(String[] args) {
		Test test = new Test();

		Class<?> testClass = (Class<?>) test.getClass();

		Method[] methods = testClass.getDeclaredMethods();

		try {
			for (Method method : methods) {
				String meName = method.getName();

				if (meName.startsWith("set")) {
					Class[] paramObj = method.getParameterTypes();
					Object callParmaeter = null;
					if (paramObj.length == 1) {
						if (paramObj[0] == String.class) {
							callParmaeter = new String("test01");
						} else if (paramObj[0] == int.class) {
							callParmaeter = Integer.valueOf(1);
						} else {
							throw new RuntimeException("not invalid param");
						}	

						method.invoke(test, new Object[] { callParmaeter });
					}

				}
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
		System.out.println(test);
	}
}

package reflection;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;

public class InvokeUtil {
	public static void main(String[] args) {
		InvokeUtil.invokeClass("com.lgcns.test.RunTest", args);
	}
	public static void invokeClass(String name, String[] args){
		File file = new File("./classes");
		try {
			// Convert File to a URL
			URL url = file.toURL(); // file:/D:/_Develop/jmxSamples/customMBean/log4j-1.2.8.jar
			URL[] urls = new URL[] { url };
			System.out.println(urls);

			// Create a new class loader with the directory
			ClassLoader cl = new URLClassLoader(urls);
			System.out.println(cl);

			Class c = cl.loadClass(name);
			Method m = c.getMethod("main", new Class[] { args.getClass() });
			m.setAccessible(true);
			int mods = m.getModifiers();
			if (m.getReturnType() != void.class || !Modifier.isStatic(mods) || !Modifier.isPublic(mods)) {
				throw new NoSuchMethodException("main");
			}

			m.invoke(null, new Object[] { args });
		} catch (Exception e) {
			// This should not happen, as we have disabled access checks
			e.printStackTrace();
		} finally {

		}
	}

}

package reflection.dynamicLoading;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicLoading {

	public void run() throws MalformedURLException{

		File dir = new File("./classes");

		URLClassLoader loader = createURLClassLoader(dir);

		File[] files = new File(dir, "sp/dloading/libsrc").listFiles(new FileFilter() {

			@Override
			public boolean accept(File file){
				return file.getName().endsWith(".class");
			}
		});

		for (File file : files) {
			Object o = loadClass(loader, file);
			Method m = getMethod(o);
			if (m != null) {
				String r = invokeMethod(o, m, "1", "100");
				System.out.println(r);
			}
		}

	}

	public URLClassLoader createURLClassLoader(File dir) throws MalformedURLException{
		return new URLClassLoader(new URL[] { dir.toURI().toURL() });
	}

	public Method getMethod(Object o){
		for (Method m : o.getClass().getMethods()) {
			if ("getAddedString".equals(m.getName())) {
				return m;
			}
		}
		return null;
	}

	public String invokeMethod(Object o, Method method, String a, String b){
		try {
			String r = (String)method.invoke(o, a, b);
			return r;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Object loadClass(URLClassLoader loader, File file){
		try {
			Class<?> c = loader.loadClass("sp.dloading.libsrc." + file.getName().substring(0, file.getName().lastIndexOf('.')));
			Object o = c.getDeclaredConstructor().newInstance();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object();

	}
}

package file.parse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxTest {
	
	public static void main(String[] args) {
		InputStream in = null;

		try {
			//File file = new File("./INPUT/student.xml");
			in = SaxTest.class.getResourceAsStream("student.xml");
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			SaxHandler handler = new SaxHandler();
			saxParser.parse(in, handler);
			Map<Integer, Student> model = handler.getModel();
			System.out.println(model.entrySet());
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {

				}
			}
		}
	}

	public static void parseZip() {
		InputStream zis = null;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			SaxHandler handler = new SaxHandler();
			Map<Integer, Student> model = handler.getModel();
			URL url = SaxTest.class.getResource("student.zip");

			File file = new File(url.getFile());

			ZipFile zipfile = new ZipFile(file);

			ZipEntry entry = zipfile.getEntry("student.xml");

			if (entry != null) {

				zis = zipfile.getInputStream(entry);

				saxParser.parse(zis, handler);

			}

			zis = zipfile.getInputStream(zipfile.getEntry("student2.xml"));

			saxParser.parse(zis, handler);

			System.out.println(model.entrySet());

		} catch (SAXException e) {

			e.printStackTrace();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (zis != null) {
				try {
					zis.close();
				} catch (IOException e) {

				}
			}
		}
	}




}

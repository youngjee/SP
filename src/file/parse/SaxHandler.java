package file.parse;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {

	private Map<Integer, Student> model = new HashMap<Integer, Student>();

	/**
	 * 
	 * xml의 element의 path를 저장하는 변수
	 * 
	 */
	private StringBuffer path = new StringBuffer();

	/**
	 * 
	 * 현재 parsing을 하고 있는 xml element node의 name
	 * 
	 */
	private String nodeName;

	/**
	 * 
	 * 현재 parsing을 하고 있는 xml element node의 value
	 * 
	 */
	private StringBuffer nodeValue;

	/**
	 * 
	 * 현재 parsing을 하고 있는 위치를 저장할 location 객체
	 * 
	 */
	private Locator loc;

	/**
	 * 
	 * 현재 parsing을 하고 있는 Student 객체
	 * 
	 */
	private Student student;

	@Override

	public void setDocumentLocator(Locator locator) {

		this.loc = locator;

	}

	public Locator getDocumentLocator() {

		return this.loc;

	}
	
	
	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes attrs)
			throws SAXException {

		path.append("/").append(qName);

		if ("/class".equals(path.toString())) {

		} else if ("/class/student".equals(path.toString())) {

			student = new Student();

			student.setRollno(Integer.parseInt(attrs.getValue("rollno")));

		}

		nodeName = qName;

	}

	/**
	 * 
	 * xml의 element가 닫힐때 자동으로 호출
	 * 
	 * 
	 * 
	 * @param namespaceURI - namespace URI
	 * @param localName    - The local name (without prefix), xml Namespace
	 *                     processing을 사용하지 않으므로 사용되지 않는다.
	 * @param qname        - The qualified name (with prefix)
	 * @throws SAXException
	 * 
	 */

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

		// System.out.println("endElement : " + path);

		if (nodeName.equals(qName)) { // leap node

			String value = nodeValue.toString().trim();

			if ("/class/student/firstname".equals(path.toString())) {

				student.setFirstName(value);

			} else if ("/class/student/lastname".equals(path.toString())) {

				student.setLastName(value);

			} else if ("/class/student/nickname".equals(path.toString())) {

				student.setNickName(value);

			} else if ("/class/student/marks".equals(path.toString())) {

				student.setMarks(Integer.parseInt(value));

			}

		} else {

			if ("/class/student".equals(path.toString())) {

				model.put(student.getRollno(), student);

				student = null;

			}

		}

		nodeValue = null;

		path.delete(path.lastIndexOf("/"), path.length());

	}

	/**
	 * 
	 * xml의 element node에 character data가 존재하는 경우 자동으로 호출되며 해당 data를 nodeValue 전역변수에
	 * 할당한다.
	 * 
	 * @param ch     - The characters
	 * @param start  - The start position in the character array
	 * @param length - The number of characters to use from the character array
	 * @throws SAXException
	 * 
	 */

	public void characters(char[] ch, int start, int length) throws SAXException {

		if (length == 0)
			return;

		String text = new String(ch, start, length);

		if (nodeValue == null)
			nodeValue = new StringBuffer(text);

		else
			nodeValue.append(text);

	}

	/**
	 * 
	 * xml을 통해서 만들어진 Map을 return한다.
	 * @return Map
	 * 
	 */

	public Map<Integer, Student> getModel() {

		return model;

	}

	@Override

	public void endDocument() throws SAXException {
	}

}

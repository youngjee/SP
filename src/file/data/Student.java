package file.data;

import java.io.Serializable;

public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int age;
	private double height;
	private boolean man;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public boolean isMan() {
		return man;
	}
	public void setMan(boolean man) {
		this.man = man;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Student() {
		
	}
	
	public Student(String name, int age, int height, boolean man) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.man = man;
	}
	@Override
	public String toString() {
		return name+","+age+","+height;
	}
}

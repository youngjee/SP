package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortData {

	public static void main(String[] args) {
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee("1111", "leeyoungjee", "korea", "001"));
		empList.add(new Employee("3333", "yeoruem", "korea", "003"));
		empList.add(new Employee("2222", "kimtaebong", "korea", "002"));
		Collections.sort(empList, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				// TODO Auto-generated method stub
				return o1.getNum().compareTo(o2.getNum());
			}
		});
				
		for(Employee emp:empList){
			System.out.println(emp.toString());
		}
		System.out.println("\n");
		Collections.rotate(empList, -1);
		
		for(Employee emp:empList){
			System.out.println(emp.toString());
		}
	}
}

class Employee{
	private String num;
	private String name;
	private String address;
	private String phone;
	
	public Employee(String num, String name, String address, String phone){
		this.num = num;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String toString() {
		return String.format("%s %s %s %s", this.num, this.name, this.address, this.phone);
	}
}

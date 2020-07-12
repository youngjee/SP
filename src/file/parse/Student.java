package file.parse;

public class Student {

	private String firstName;

	private String lastName;

	private String nickName;

	private int marks;

	private int rollno;

	public Student() {

	}

	public int getRollno() {

		return rollno;

	}

	public void setRollno(int rollno) {

		this.rollno = rollno;

	}

	public String getFirstName() {

		return firstName;

	}

	public void setFirstName(String firstName) {

		this.firstName = firstName;

	}

	public String getLastName() {

		return lastName;

	}

	public void setLastName(String lastName) {

		this.lastName = lastName;

	}

	public String getNickName() {

		return nickName;

	}

	public void setNickName(String nickName) {

		this.nickName = nickName;

	}

	public int getMarks() {

		return marks;

	}

	public void setMarks(int marks) {

		this.marks = marks;

	}

	@Override

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(getFirstName()).append(" ");

		sb.append(getLastName());

		sb.append("(").append(getNickName()).append(":");

		sb.append(getMarks()).append(")");

		return sb.toString();

	}

}

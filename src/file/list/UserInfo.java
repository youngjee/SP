package file.list;

public class UserInfo {
	private String name;
	private int kGrade;
	private int eGrade;
	private int mGrade;

	public UserInfo(String name, int kGrade, int eGrade, int mGrade) {
		this.name = name;
		this.kGrade = kGrade;
		this.eGrade = eGrade;
		this.mGrade = mGrade;
	}

	public String getName() {
		return name;
	}

	public int getkGrade() {
		return kGrade;
	}

	public int geteGrade() {
		return eGrade;
	}

	public int getmGrade() {
		return mGrade;
	}

}
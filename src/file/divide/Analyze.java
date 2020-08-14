package file.divide;

public class Analyze {

	private String id;
	private int total = 0;
	
	public Analyze(String id, int total) {
		super();
		this.id = id;
		this.total = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}

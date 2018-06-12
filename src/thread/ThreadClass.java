package thread;

public class ThreadClass implements Runnable{
	
	public static void main(String[] args) throws InterruptedException {
		ThreadClass tc1 = new ThreadClass("tc1");
		ThreadClass tc2 = new ThreadClass("tc2");
		Thread t1 = new Thread(tc1);
		Thread t2 = new Thread(tc2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("Thread finish");
	}
	
	private String threadName;
	
	public ThreadClass(String name) {
		this.threadName = name;
	}

	@Override
	public void run() {
		try {
			show();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void show() throws InterruptedException{
		for (int i = 0; i < 100; i++) {
			System.out.println("["+threadName+"]"+"count: "+i);
			Thread.sleep(100);
		}
	}

}

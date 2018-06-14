package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
	public static void main(String[] args) {
		int threadNum = 5; //쓰레드 풀 안의 쓰레드 갯수
		ExecutorService es = Executors.newFixedThreadPool(threadNum);
		
		//총 실행할 쓰레드갯수 10
		for (int i = 0; i < 10; i++) {
			es.submit(new ThreadClass("thread"+(i+1)));
		}
	}
}

class ThreadClass implements Runnable{

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
		}
	}

}
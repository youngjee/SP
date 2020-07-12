package thread.mutex;

import java.util.concurrent.locks.ReentrantLock;

//Thread의 Sync방식
public class MutexSimpleSample {
	public static void main(String[] args) throws InterruptedException {
		ThreadClass tc1 = new ThreadClass("[Thread1] ");
		ThreadClass tc2 = new ThreadClass("[Thread2] ");
		tc1.start();
		tc2.start();

		tc1.join();
		tc2.join();
	}
}

class ThreadClass extends Thread { // 'Thread' Class¸¦

	String thread_name;

	public ThreadClass(String name) {
		this.thread_name = name;
	}

	public synchronized void run() {
		PrintNums(thread_name);
	}

	public synchronized void PrintNums(String str) {
		int i;

		System.out.println(str);

		for (i = 1; i <= 30; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}

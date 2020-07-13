package thread.callable;

import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableExample {
	 static class MyCallable implements Callable<String> {
	        @Override
	        public String call() throws Exception {
	            String result = "Called at " + LocalTime.now();
	            return result;
	        }
	    }

	    public static void main(String[] args) throws ExecutionException, InterruptedException {
	        MyCallable callable = new MyCallable();
	        FutureTask<String> futureTask = new FutureTask<String>(callable);
	        Thread thread = new Thread(futureTask);
	        thread.start();

	        // 결과가 리턴되기를 기다립니다.
	        System.out.println("result : " + futureTask.get());
	    }
}

package thread.mutex.wait;

public class CakePlate {

	private int breadCount=0;

    public CakePlate() {
    }

    public synchronized void makeBread(){
        if(breadCount>=10){
            try{
                System.out.println("빵이 남는다.");
                wait();
            }catch(InterruptedException ire){}
        }
        breadCount++;//빵이 10개가 안되면 더 만들자.
        System.out.println("빵을 1개 더 만듬  총 : "+breadCount+"개");
        this.notifyAll();
    }

    public synchronized void eatBread(){
        if(breadCount<1){
            try{
                System.out.println("빵이 모자라 기다림");
                wait();
            }catch(InterruptedException ire){}
        }

        breadCount--;//빵이 있으니 먹자.
        System.out.println("빵을 1개 먹음  총 : "+breadCount+"개");
        this.notifyAll();
    }

}

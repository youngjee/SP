package thread.mutex.wait;

public class CakeEater extends Thread {
	private CakePlate cake;

    public CakeEater(CakePlate cake){
        setCakePlate(cake);
    }

    public void setCakePlate(CakePlate cake){
        this.cake=cake;
    }

    public CakePlate getCakePlate(){
        return cake;
    }

    public void run(){
    	
        for(int i=0;i<30;i++){
        	try {
    			Thread.sleep(2000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            cake.eatBread();
        }
    }
}

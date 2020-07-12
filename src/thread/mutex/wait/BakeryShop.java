package thread.mutex.wait;

public class BakeryShop {

	public static void main(String[] args) {

        CakePlate cake=new CakePlate();//Cake 접시 준비
        CakeEater eater=new CakeEater(cake);//cake 접시 공유
        CakeMaker baker=new CakeMaker(cake);//cake 접시 공유

        //baker.setPriority(6);//우선순위--먼저 채워 넣고 시작하자.       
        baker.start();//먼저 채워 넣고 시작하자.
        eater.start();

    }
}

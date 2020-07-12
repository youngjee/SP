package file.object;


import java.io.*;

import java.util.Vector;

public class ObjectStudentWrite {
	
	//byte형식으로 write되기 때문에 file을 열면 이상하게 들어가지만 readObject하면 정상적으로 읽어짐
	public static void main(String[] args) {
		ObjectStudentWrite osw=new ObjectStudentWrite();

        Vector v=new Vector(5, 5);
        //Vector<Student> v=new Vector<Student>(5, 5);//JAVA5
        v.add(new Student("gildong",17,"newyork"));
        v.add(new Student("gilsun",15,"seoul"));
        v.add(new Student("teddy",20,"gangseo"));
        v.add(new Student("chang",18,"magok"));
        try {
            osw.write("stu.txt",v);
            osw.read("stu.txt");
        } catch (IOException e) {

            e.printStackTrace();

        }
	}

    public int write(String fname, Vector v) throws IOException{

    //public int write(String fname, Vector<Student> v) throws IOException{//JAVA5
        int objectNumber=0;

        try {
            FileOutputStream fos=new FileOutputStream(fname);
            ObjectOutputStream oos=new ObjectOutputStream(fos);//throws
            objectNumber=v.size();

            oos.writeInt(objectNumber);

            System.out.println(objectNumber+"개의 Student가 입력됨");

            for(int i=0;i<objectNumber;i++){
                oos.writeInt(i);
                oos.writeObject((Student)v.get(i));

                //oos.writeObject(v.get(i));//JAVA5
                oos.flush();
                System.out.println(i+"번째의  Student가 입력됨");

            }
            oos.close();   fos.close();

        } catch (FileNotFoundException e) {
            System.out.println("잘못된 파일이름을 입력했습니다.");
        } catch(Exception ee){
            throw new IOException("타입이 이상합니다."+ee);
        }

        return objectNumber;
    }

    public void read(String fname) throws IOException{

        try {
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream ois=new ObjectInputStream(fis);//throws
            int objectNumber=ois.readInt();
            
            System.out.println(objectNumber+"개의 Student를 읽음");
            for(int i=0;i<objectNumber;i++){

                try {
                    System.out.print(ois.readInt()+"번째 :");
                    System.out.println((Student)ois.readObject());
                } catch (ClassNotFoundException e1) {
                    System.out.println("잘못된 타입입니다..");
                }
            }

            ois.close();  fis.close();
        } catch (FileNotFoundException e) {

            System.out.println("잘못된 파일이름을 입력했습니다.");

        }

    }

}

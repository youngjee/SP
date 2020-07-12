package file.data;

import java.io.*;

public class WritingDatasUsing {

	public static void main(String[] args) {

        WritingDatasUsing wdu=new WritingDatasUsing();

        Student s1=new Student("홍길순", 20, 165, false);
        
        Student s2 = null;

        try {
            wdu.writingData("abc.txt",true, s1);
            s2=wdu.readingData("abc.txt");
            System.out.println(s2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void writingData(String fname, boolean append, Student wd) throws IOException {

		FileOutputStream fos = null;
		DataOutputStream dos = null;

		try {
			fos = new FileOutputStream(new File(fname), append);
			dos = new DataOutputStream(fos);
			
			dos.writeUTF(wd.getName());
			dos.writeInt(wd.getAge());
			dos.writeDouble(wd.getHeight());
			dos.writeBoolean(wd.isMan());
		} catch (FileNotFoundException e) {
			System.out.println("잘못된 파일이름을 입력했습니다.");
		}
	}

	public Student readingData(String fname) throws IOException {

		FileInputStream fis = null;
		DataInputStream dis = null;
		Student wd = new Student();

		try {
			fis = new FileInputStream(new File(fname));
			dis = new DataInputStream(fis);
			wd.setName(dis.readUTF());
			wd.setAge(dis.readInt());
			wd.setHeight(dis.readDouble());
			wd.setMan(dis.readBoolean());
		} catch (FileNotFoundException e) {
			System.out.println("잘못된 파일이름을 입력했습니다.");
		}
		return wd;
	}
}

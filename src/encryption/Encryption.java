package encryption;

import java.security.MessageDigest;

//SHA256, MD5로 암호화하기
public class Encryption {

	public static void main(String[] args) throws Exception {
		//ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f
		System.out.println(getEncSHA256("password123"));
		System.out.println(getEncMD5("password123"));
	}

	public static String getEncSHA256(String txt) throws Exception {
		StringBuffer sbuf = new StringBuffer();

		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		mDigest.update(txt.getBytes());

		byte[] msgStr = mDigest.digest();

		for (int i = 0; i < msgStr.length; i++) {
			byte tmpStrByte = msgStr[i];
			String tmpEncTxt = Integer
					.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);

			sbuf.append(tmpEncTxt);
		}

		return sbuf.toString();
	}
	
	public static String getEncMD5(String txt) throws Exception {
	     
	    StringBuffer sbuf = new StringBuffer();
	     
	     
	    MessageDigest mDigest = MessageDigest.getInstance("MD5");
	    mDigest.update(txt.getBytes());
	     
	    byte[] msgStr = mDigest.digest() ;
	     
	    for(int i=0; i < msgStr.length; i++){
	        String tmpEncTxt = Integer.toHexString((int)msgStr[i] & 0x00ff) ;          
	        sbuf.append(tmpEncTxt) ;
	    }
	     
	     
	     
	    return sbuf.toString() ;
	}


}

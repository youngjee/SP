package encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

//SHA256, MD5로 암호화하기
public class Encryption {

	public static void main(String[] args) throws Exception {
		//ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f
		System.out.println(getEncSHA256("password123"));
		System.out.println(getEncMD5("password123"));
		
		//base64 인코딩, 디코딩
		String encodedTxt = getEncBase64("password123");
		System.out.println(encodedTxt);
		System.out.println(getDecBase64(encodedTxt));
	}

	//SHA256 인코딩
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
	
	//BASE64 인코딩
	public static String getEncBase64(String txt) throws UnsupportedEncodingException {
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(txt.getBytes("UTF-8"));
	}
	
	//BASE64 디코딩
	public static String getDecBase64(String encodedTxt) throws UnsupportedEncodingException{
		Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(encodedTxt);
		
		return new String(decodedBytes, "UTF-8");
	}
	
	//MD5 인코딩
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

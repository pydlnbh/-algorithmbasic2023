package src.class33;

//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.security.Security;

// https://www.mashibing.com/study?courseNo=339&sectionNo=286&courseVersionId=1255
// 需要自己找一下javax.xml.bind的jar，然后导入到项目
//import javax.xml.bind.DatatypeConverter;

public class Hash {

//	private MessageDigest hash;

//	public Hash(String algorithm) {
//		try {
//			hash = MessageDigest.getInstance(algorithm);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//	}

//	public String hashCode(String input) {
//		return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
//	}

	public static void main(String[] args) {
		System.out.println("支持的算法 : ");
		for (String str : Security.getAlgorithms("MessageDigest")) {
			System.out.println(str);
		}
		System.out.println("=======");

//		String algorithm = "MD5";
//		Hash hash = new Hash(algorithm);

//		String input1 = "peiyiding1";
//		String input2 = "peiyiding12";
//		String input3 = "peiyiding13";
//		String input4 = "peiyiding14";
//		String input5 = "peiyiding15";
//		System.out.println(hash.hashCode(input1));
//		System.out.println(hash.hashCode(input2));
//		System.out.println(hash.hashCode(input3));
//		System.out.println(hash.hashCode(input4));
//		System.out.println(hash.hashCode(input5));

	}

}

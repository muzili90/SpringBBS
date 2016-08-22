package cn.tiger.utils.md5;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Md5PasswordEncoderUtil {

	// 邮箱登录采用种子加密
	//public static String SEED = "seed";
	
	public static Md5PasswordEncoder md5=new Md5PasswordEncoder();
	
	public static String zencodePassword(String password,String salt){
		return md5.encodePassword(password, salt);
		
	}
	
	//ead4d1f2d6032bb4564f85400ab98604
}

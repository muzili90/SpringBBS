package cn.tiger.utils.md5;

import org.apache.commons.codec.digest.DigestUtils;

//useless
public class EncryptUtil {
	/*public static String encryptUsernameAndPassword(String username,String password){
		return DigestUtils.md5Hex(username+password);
	}*/
	
	//邮箱登录采用种子加密
	public static String SEED="seed";
	
	public static String encryptUsernameAndPassword(String password){
		return DigestUtils.md5Hex(SEED+password);
	}
}

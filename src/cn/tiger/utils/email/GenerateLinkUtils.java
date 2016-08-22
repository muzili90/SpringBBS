package cn.tiger.utils.email;  
  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
  
import javax.servlet.ServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import cn.tiger.entity.account.AcctUser;  
  
/** 
 * 生成帐户激活、重新设置密码的链接 
 */  
public class GenerateLinkUtils {  
      
    private static final String CHECK_CODE = "checkCode";  
      
    /** 
     * 生成重设密码的链接 
     */  
    public static String generateResetPwdLink(AcctUser user,long date,String digitalSignature) {  
        /*return "http://localhost:8080/MySpringBBSSample/mypace/account/forget!resetPasswordForm.action?userName="   
                + user.getLoginName() + "&" + CHECK_CODE + "=" + generateCheckcode(user); */ 
    	
    	return null;
    }  
      
    private static String md5(String string) {  
        MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("md5");  
            md.update(string.getBytes());  
            byte[] md5Bytes = md.digest();  
            return bytes2Hex(md5Bytes);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
          
        return null;  
    }  
      
    private static String bytes2Hex(byte[] byteArray)  
    {  
        StringBuffer strBuf = new StringBuffer();  
        for (int i = 0; i < byteArray.length; i++)  
        {  
            if(byteArray[i] >= 0 && byteArray[i] < 16)  
            {  
                strBuf.append("0");  
            }  
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));  
        }  
        return strBuf.toString();  
    }  
}  
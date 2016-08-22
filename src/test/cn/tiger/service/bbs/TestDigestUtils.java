package test.cn.tiger.service.bbs;

import org.apache.commons.codec.digest.DigestUtils;

public class TestDigestUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s=DigestUtils.md5Hex("leehodna$1471533379000$29910165-e741-4d69-9e79-d9a007b0baba");
		System.out.println(s);
	}

}

package cn.tedu.note.test;

import javax.tools.Diagnostic;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class Md5Test {

	@Test
	public void testMd5(){
		String str = "123456";
		String md5 = DigestUtils.md5Hex(str);
		System.out.println(md5);
		//加盐摘要
		String salt = "今天你吃了吗？";
		md5 = DigestUtils.md5Hex(salt+str);
		System.out.println(md5);
	}

}
//43b873e1b842835ca28beb8f34065306

// c8837b23ff8aaa8a2dde915473ce0991











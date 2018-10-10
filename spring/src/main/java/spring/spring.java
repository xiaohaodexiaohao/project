package spring;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class spring {

	public static void main(String[] args) {
		String config = "applicationContext.xml";
		/*
		 * ApplicationContext��һ���ӿ�
		 */
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		//System.out.println(ac);
		
		//Student.class�Ƿ�������class����
		Student stu = ac.getBean("stu1",Student.class);
		System.out.println(stu);
		
		Date day = ac.getBean("day1",Date.class);
		System.out.println(day);
	}

}
















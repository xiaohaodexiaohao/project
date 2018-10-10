package spring;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class spring {

	public static void main(String[] args) {
		String config = "applicationContext.xml";
		/*
		 * ApplicationContext是一个接口
		 */
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		//System.out.println(ac);
		
		//Student.class是方法区的class对象
		Student stu = ac.getBean("stu1",Student.class);
		System.out.println(stu);
		
		Date day = ac.getBean("day1",Date.class);
		System.out.println(day);
	}

}
















package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.Teacher;

public class TestCase {
	@Test
	//测试作用域
	public void test1(){
		//启动spring容器
		String config = "spring.xml";
		/**
		 * 关闭容器需要用其子接口
		 */
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext(config);
		//通过容器获得bean 
		
		Teacher t1 = ac.getBean("t1",Teacher.class);

		Teacher t2 = ac.getBean("t1",Teacher.class);

		System.out.println(t1 == t2);
		
		//关闭容器
		ac.close();
		
	}
}























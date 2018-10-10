package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.Teacher;

public class TestCase {
	@Test
	//����������
	public void test1(){
		//����spring����
		String config = "spring.xml";
		/**
		 * �ر�������Ҫ�����ӽӿ�
		 */
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext(config);
		//ͨ���������bean 
		
		Teacher t1 = ac.getBean("t1",Teacher.class);

		Teacher t2 = ac.getBean("t1",Teacher.class);

		System.out.println(t1 == t2);
		
		//�ر�����
		ac.close();
		
	}
}























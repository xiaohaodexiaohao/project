package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * ������
 * 1.����ʵ��Controller�ӿ�
 * 2.����������Ҫ�󣬷���ֵ������ModelAndView��String����
 * 3.������Ӷ������
 * 4.ʹ��@Controller
 * 5.�����ڷ���ǰ����Ǯ����@RequestMapping(�൱��handlerMapping)
 * @author dell1
 *
 */
@Controller
public class HelloController {
	
	@RequestMapping("/hello.do")
	public String hello(){
		System.out.println("hello()");
		//������ͼ��
		return "hello";
	}
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("toLogin()");
		return "toLogin";
	}
	
	//�����������1:ͨ��request�ṩ�ķ���
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request){
		System.out.println("login()");
		String adminCode = request.getParameter("adminCode");
		System.out.println("adminCode:"+adminCode);
		return "index";
	}
	//�����������2��ͨ��@RequestParamע��
	@RequestMapping("/login2.do")
	//���������ֵһ�����ɻ���������ʹ��ע�⣩
	public String login2(String adminCode,@RequestParam("pwd")String password){
		System.out.println("login2.do");
		System.out.println("adminCode:"+adminCode+"pwd:"+password);
		return "index";
	}
	//�����������3��ͨ����װΪjavaBean
	@RequestMapping("/login3.do")
	public String login3(AdminParam ap){
		System.out.println("login3()");
		String adminCode = ap.getAdminCode();
		System.out.println("adminCode:"+adminCode);
		return "index";
	}
	
	//��ҳ�洫ֵ1�������ݰ󶨵�request��
	@RequestMapping("/login4.do")
	public String login4(AdminParam ap,HttpServletRequest request){
	
		String adminCode = ap.getAdminCode();
		request.setAttribute("adminCode", adminCode);
		
		//Ĭ�������DispatcherServletʹ��ת��
		return "index";
	}
	//��ҳ�洫ֵ2������ModelAndeView����
	@RequestMapping("/login5.do")
	public ModelAndView login5(AdminParam ap){
		System.out.println("login5()");
		String adminCode = ap.getAdminCode();
		//step1.��������ӵ�Map��������
		Map<String,Object> data = new HashMap<String,Object>();
		//�൱��ִ����request.setAttribute
		data.put("adminCode", adminCode);
		//step2. ��Map������ӵ�ModelAndView
		ModelAndView mav = new ModelAndView("index",data);
		return mav;
	}
	//��ҳ�洫ֵ3����������ӵ�ModelMap
	@RequestMapping("/login6.do")
	public String login6(AdminParam ap,ModelMap mm){
		System.out.println("login6()");
		String adminCode = ap.getAdminCode();
		//�൱��ִ����request.setAttribute
		mm.addAttribute("adminCode",adminCode);
		return "index";
	}
	//��ҳ�洫ֵ�������ݰ󶩵�session
	@RequestMapping("/login7.do")
	public String login7(AdminParam ap,HttpSession session){
		System.out.println("login7()");
		String adminCode = ap.getAdminCode();
		session.setAttribute("adminCode",adminCode);
		return "index";
	}
	//�ض���ķ�ʽ ������ֵΪString
	@RequestMapping("/login8.do")
	public String login8(){
		System.out.println("login8()");
		return "redirect:toIndex.do";
	}
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		System.out.println("toIndex()");
		return "index";
	}
	//�ض���ķ�ʽ ������ֵΪModelAndView
	@RequestMapping("/login9.do")
	public ModelAndView login9(){
		System.out.println("login9()");
		RedirectView rv = new RedirectView("toIndex.do");
		ModelAndView mav = new ModelAndView(rv);
		return mav;
	}
	
}
















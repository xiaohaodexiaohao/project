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
 * 处理器
 * 1.不用实现Controller接口
 * 2.方法名不做要求，返回值可以是ModelAndView和String类型
 * 3.可以添加多个方法
 * 4.使用@Controller
 * 5.可以在方法前和类钱加上@RequestMapping(相当于handlerMapping)
 * @author dell1
 *
 */
@Controller
public class HelloController {
	
	@RequestMapping("/hello.do")
	public String hello(){
		System.out.println("hello()");
		//返回视图名
		return "hello";
	}
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("toLogin()");
		return "toLogin";
	}
	
	//接受请求参数1:通过request提供的方法
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request){
		System.out.println("login()");
		String adminCode = request.getParameter("adminCode");
		System.out.println("adminCode:"+adminCode);
		return "index";
	}
	//接受请求参数2：通过@RequestParam注解
	@RequestMapping("/login2.do")
	//跟请求参数值一样（可换参数名，使用注解）
	public String login2(String adminCode,@RequestParam("pwd")String password){
		System.out.println("login2.do");
		System.out.println("adminCode:"+adminCode+"pwd:"+password);
		return "index";
	}
	//接受请求参数3：通过封装为javaBean
	@RequestMapping("/login3.do")
	public String login3(AdminParam ap){
		System.out.println("login3()");
		String adminCode = ap.getAdminCode();
		System.out.println("adminCode:"+adminCode);
		return "index";
	}
	
	//向页面传值1：将数据绑定到request上
	@RequestMapping("/login4.do")
	public String login4(AdminParam ap,HttpServletRequest request){
	
		String adminCode = ap.getAdminCode();
		request.setAttribute("adminCode", adminCode);
		
		//默认情况下DispatcherServlet使用转发
		return "index";
	}
	//向页面传值2：返回ModelAndeView对象
	@RequestMapping("/login5.do")
	public ModelAndView login5(AdminParam ap){
		System.out.println("login5()");
		String adminCode = ap.getAdminCode();
		//step1.将数据添加到Map对象里面
		Map<String,Object> data = new HashMap<String,Object>();
		//相当于执行了request.setAttribute
		data.put("adminCode", adminCode);
		//step2. 将Map对象添加到ModelAndView
		ModelAndView mav = new ModelAndView("index",data);
		return mav;
	}
	//向页面传值3：将数据添加到ModelMap
	@RequestMapping("/login6.do")
	public String login6(AdminParam ap,ModelMap mm){
		System.out.println("login6()");
		String adminCode = ap.getAdminCode();
		//相当于执行了request.setAttribute
		mm.addAttribute("adminCode",adminCode);
		return "index";
	}
	//向页面传值：将数据绑订到session
	@RequestMapping("/login7.do")
	public String login7(AdminParam ap,HttpSession session){
		System.out.println("login7()");
		String adminCode = ap.getAdminCode();
		session.setAttribute("adminCode",adminCode);
		return "index";
	}
	//重定向的方式 ：返回值为String
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
	//重定向的方式 ：返回值为ModelAndView
	@RequestMapping("/login9.do")
	public ModelAndView login9(){
		System.out.println("login9()");
		RedirectView rv = new RedirectView("toIndex.do");
		ModelAndView mav = new ModelAndView(rv);
		return mav;
	}
	
}
















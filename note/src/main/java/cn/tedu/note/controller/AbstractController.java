package cn.tedu.note.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.util.JsonResult;

public abstract class AbstractController {
	/**
	 * ����������������ִ�г����쳣ʱ��, ִ��
	 * �쳣������ handleException
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleException( Exception e){
		e.printStackTrace();
		return new JsonResult(e);
	}

}
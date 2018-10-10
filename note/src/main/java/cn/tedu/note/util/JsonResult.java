package cn.tedu.note.util;

import java.io.Serializable;

import cn.tedu.note.service.UserNotFoundException;

public class JsonResult implements Serializable{
	
	private static final long serialVersionUID = 6885328084890956355L;
	public static final int SUCCESS = 0;
	public static final int ERROR  = 1;
	
	private int state;
	private String message;//´íÎóÏûÏ¢
	private Object data;
	
	public JsonResult() {
		super();
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JsonResult(Object data){
		state = SUCCESS;
		this.data = data;
	}
	public JsonResult(Throwable e) {
		state = ERROR;
		message = e.getMessage();
	}
	public JsonResult(int state, Throwable e) {
		this.state = state;
		this.message = e.getMessage();
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static int getSuccess() {
		return SUCCESS;
	}
	public static int getError() {
		return ERROR;
	}
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + "]";
	}
	
	
}

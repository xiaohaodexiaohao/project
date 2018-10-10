package controller;
/**
 * 用于封装请求参数值
 * 属性名与参数名一直
 * 提供响应的get和set方法
 * 类型要匹配（类型会自动化转化）
 * @author dell1
 *
 */
public class AdminParam {
	private String adminCode;
	private String pwd;
	public String getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}

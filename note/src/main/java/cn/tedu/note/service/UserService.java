package cn.tedu.note.service;

import cn.tedu.note.entity.User;

/**
 * 业务层接口
 * 登录功能：成功返回用户信息，失败抛出异常
 */
public interface UserService {
	User login(String name,String password)
		throws UserNotFoundException,PasswordException;
	/**
	 * 
	 * @param name
	 * @param nick
	 * @param password
	 * @param confirm
	 * @return 注册成功的用户信息
	 * @throws UserNameException 用户名异常
	 * @throws PasswordException 密码异常
	 */
	User regist(String name,String nick,String password,String confirm)
		throws UserNameException,PasswordException;
}

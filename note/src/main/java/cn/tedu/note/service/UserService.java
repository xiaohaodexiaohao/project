package cn.tedu.note.service;

import cn.tedu.note.entity.User;

/**
 * ҵ���ӿ�
 * ��¼���ܣ��ɹ������û���Ϣ��ʧ���׳��쳣
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
	 * @return ע��ɹ����û���Ϣ
	 * @throws UserNameException �û����쳣
	 * @throws PasswordException �����쳣
	 */
	User regist(String name,String nick,String password,String confirm)
		throws UserNameException,PasswordException;
}

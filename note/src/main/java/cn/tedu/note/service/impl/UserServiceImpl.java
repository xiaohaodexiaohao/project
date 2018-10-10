package cn.tedu.note.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.PasswordException;
import cn.tedu.note.service.UserNameException;
import cn.tedu.note.service.UserNotFoundException;
import cn.tedu.note.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Value("#{jdbc.salt}")
	private String salt;
	
	@Resource
	private UserDao userDao;
	
	public User login(String name, String password) throws UserNotFoundException, PasswordException {
		
		if(password==null ||password.trim().isEmpty()){
			throw new PasswordException("�����");
		}
		if(name==null || name.trim().isEmpty()){
			throw new UserNotFoundException("�û�����");
		}
		User user = userDao.findUserByName(
				name.trim());
		if(user==null){
			throw new UserNotFoundException("�û�������");
		}
		
		String pwd = DigestUtils.md5Hex(
				salt+password.trim());
		if(pwd.equals(user.getPassword())){
			return user;
		}
		throw new PasswordException("�������");
	}

	// UserServiceImpl
	
	public User regist(String name, String nick, String password, String confirm)throws UserNameException, PasswordException {
		//���name, �����ظ�
		if(name==null || name.trim().isEmpty()){
			throw new UserNameException("���ܿ�");
		}
		User one = userDao.findUserByName(name);
		if(one!=null){
			throw new UserNameException("��ע��");
		}
		//�������
		if(password==null || password.trim().isEmpty()){
			throw new PasswordException("���ܿ�");
		}
		if(! password.equals(confirm)){
			throw new PasswordException("ȷ�����벻һ��");
		}
		//���nick
		if(nick ==null || nick.trim().isEmpty()){
			nick = name;
		}
		String id = UUID.randomUUID().toString();
		String token = "";
 
		password = DigestUtils.md5Hex(salt+password);
		User user = new User(
				id, name, password, 
				token, nick);
		int n = userDao.addUser(user);
		if(n!=1){
			throw new RuntimeException("���ʧ��!");
		}
		return user;
	}
	
	
}












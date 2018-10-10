package cn.tedu.note.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.NoteNotFoundExcepotion;
import cn.tedu.note.service.NotebookService;
import cn.tedu.note.service.UserNotFoundException;

@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {

	@Resource
	private NotebookDao notebookDao;

	@Resource
	private UserDao userDao;

	public List<Map<String, Object>> listNotebooks(String userId) throws UserNotFoundException {
		if (userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("ID不能空");
		}
		User user = userDao.findUserById(userId);
		//System.out.println(user);
		if (user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		return notebookDao.findNotebooksByUserId(userId);
	}

	public Notebook addNotebook(String userId, String name) {
		String id = UUID.randomUUID().toString();
		String typeId = "0";
		String desc = "0";
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Notebook notebook = new Notebook(id,name,typeId,userId,desc,time);
		System.out.println("-----service----"+notebook);
		notebookDao.addNotebook(notebook);
		
		return notebook;
	}
	
	

}

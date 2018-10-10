package cn.tedu.note.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteNotFoundExcepotion;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.service.NotebookNotFoundExcepotion;
import cn.tedu.note.service.UserNotFoundException;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao noteDao;

	@Resource
	private NotebookDao notebookDao;

	public List<Map<String, Object>> listNotes(String notebookId) throws NotebookNotFoundExcepotion {
		if (notebookId == null || notebookId.trim().isEmpty()) {
			throw new NotebookNotFoundExcepotion("ID为空");
		}
		// Notebook notebook = notebookDao
		// .findNotebookById(notebookId);
		// if(notebook==null){
		// throw new NotebookNoteFoundExcepotion("没有笔记本");
		// }
		int n = notebookDao.countNotebookById(notebookId);
		if (n != 1) {
			throw new NotebookNotFoundExcepotion("没有笔记本");
		}

		return noteDao.findNotesByNotebookId(notebookId);
	}

	public Note getNote(String noteId) throws NoteNotFoundExcepotion {
		if(noteId == null || noteId.trim().isEmpty()){
			throw new NoteNotFoundExcepotion("ID为空");
		}
		return noteDao.findNoteById(noteId);
	}

	public Note addNote(String notebookId, String userId, String title) throws NotebookNotFoundExcepotion, NoteNotFoundExcepotion {
		if(notebookId == null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundExcepotion("ID为空");
		}
		int n = notebookDao.countNotebookById(notebookId);
		if(n != 1){
			throw new NotebookNotFoundExcepotion("笔记本不存在");
		}
		if(userId == null||userId.trim().isEmpty()){
			throw new UserNotFoundException("ID不存在");
		}
		if(title == null||title.trim().isEmpty()){
			title ="";
		}
		String id = UUID.randomUUID().toString();
		String statusId = "0";
		String typeId = "0";
		String body = "";
		//long time = System.currentTimeMillis();
		String time = String.valueOf(System.currentTimeMillis());
		Note note = new Note(id, notebookId, userId, statusId, typeId, title, body, time, time);
		n = noteDao.addNote(note);
		if (n != 1) {
			throw new NoteNotFoundExcepotion("保存失败");
		}
		return note;
	}

	public boolean update(String noteId, String title, 
	        String body) throws NoteNotFoundExcepotion {
	    if(noteId==null || noteId.trim().isEmpty()){
	        throw new NoteNotFoundExcepotion("ID不能空");
	    }
	    Note note = noteDao.findNoteById(noteId);
	    if(note==null){
	        throw new NoteNotFoundExcepotion("没有对应的笔记");
	    } 
	    Note data = new Note();
	    if(title!=null && !title.equals(note.getTitle())){
	        data.setTitle(title);
	    }
	    if(body!=null && !body.equals(note.getBody())){
	        data.setBody(body);
	    }
	    data.setId(noteId);
	    data.setLastModifTime(String.valueOf(System.currentTimeMillis()));
	    System.out.println(data); 
	    int n = noteDao.updateNote(data);
	    return n==1;

	}
	public boolean deleteNote(String noteId) throws NoteNotFoundExcepotion {
	    if(noteId==null || noteId.trim().isEmpty()){
	        throw new NoteNotFoundExcepotion("ID不能空");
	    }
	    Note note = noteDao.findNoteById(noteId);
	    if(note==null){
	        throw new NoteNotFoundExcepotion("没有对应的笔记");
	    } 

	    Note data = new Note();
	    data.setId(noteId);
	    data.setStatusId("1");
	    
	    data.setLastModifTime(String.valueOf(System.currentTimeMillis()));
	    System.out.println("delete--"+data);
	    int n = noteDao.updateNote(data);

	    return n==1;
	}
	
}

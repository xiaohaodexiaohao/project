package cn.tedu.note.test;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.entity.Note;

public class addNoteTest extends BaseTest{
	NoteDao dao;
	@Before
	public void initDao(){
		dao = ctx.getBean("noteDao",NoteDao.class);
	}
	@Test
	public void testaddNote(){
		
		String id = UUID.randomUUID().toString();
	    String notebookId = "2";
		String userId = "56";
		String statusId = "1";
		String typeId = "";
		String title = "±Ê¼Ç";
		String body = "aaaaa";
		String createTime = "56";
	    String lastModifTime = "516";
	    Note note = new Note(id,notebookId,userId,statusId,typeId,title,body,createTime,lastModifTime);
	    System.out.println(dao.addNote(note));
	    
	}
}

package cn.tedu.note.test;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteNotFoundExcepotion;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.service.NotebookNotFoundExcepotion;

public class NoteServiceTest extends BaseTest{
	NoteService service;
	@Before
	public void initService(){
		service = ctx.getBean("noteService",NoteService.class);
	}
	@Test
	public void testaddNoteService() throws NotebookNotFoundExcepotion, NoteNotFoundExcepotion{
		Note note = service.addNote("516f6f4f-eaa3-4c76-84ff-530b92c7f64d", "54456", "454");
		System.out.println(note);
	}
	@Test
	public void testUpdate() throws NoteNotFoundExcepotion{
	    String id = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
	    String title = "Test";
	    String body = "今天天气不错";
	    boolean b = service.update(id, title, body);
	    Note note = service.getNote(id);
	    System.out.println(b);
	    System.out.println(note);
	}
	
}

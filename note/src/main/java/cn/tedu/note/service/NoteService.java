package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteService {
	List<Map<String, Object>> listNotes(String notebookId) throws  NotebookNotFoundExcepotion;
	Note getNote(String noteId) throws NoteNotFoundExcepotion;
	Note addNote(String notebookId,String userId,String title) throws NotebookNotFoundExcepotion, NoteNotFoundExcepotion;
	boolean update(String noteId, String title, String body)
		    throws NoteNotFoundExcepotion;
	
	boolean deleteNote(String noteId) throws NoteNotFoundExcepotion;
}

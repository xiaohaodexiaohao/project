package cn.tedu.note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteDao {
	List<Map<String,Object>> findNotesByNotebookId(String notebookId);
	//String findNoteBodyByNoteId(String noteId);
	Note findNoteById(String id);
	int addNote(Note note);
	int updateNote(Note note); //保存笔记功能
}

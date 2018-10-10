package cn.tedu.note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;
import cn.tedu.note.entity.Notebook;

public interface NotebookDao {
	List<Map<String,Object>> findNotebooksByUserId(String userId);

	int countNotebookById(String notebookId);
	int addNotebook(Notebook notebook);
}

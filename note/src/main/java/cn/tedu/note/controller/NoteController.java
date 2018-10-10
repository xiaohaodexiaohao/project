package cn.tedu.note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteNotFoundExcepotion;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.service.NotebookNotFoundExcepotion;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController extends AbstractController{

	 @Resource 
	 private NoteService noteService;

	 @RequestMapping("/list.do")
	 @ResponseBody
	 public JsonResult list(String notebookId) throws NotebookNotFoundExcepotion{
		 List<Map<String, Object>> list = noteService.listNotes(notebookId);
		 return new JsonResult(list);
	 }
	 
	 
	 @RequestMapping("/load.do")
	 @ResponseBody
	 public JsonResult load(String noteId) throws NoteNotFoundExcepotion{
		 Note note = noteService.getNote(noteId);
		 return new JsonResult(note);
	 }
	 
	 @RequestMapping("/add.do")
	 @ResponseBody
	 public JsonResult add(String notebookId,String userId,String title) throws NotebookNotFoundExcepotion, NoteNotFoundExcepotion{
		 Note note = noteService.addNote(notebookId, userId, title);
		 return new JsonResult(note);	 
	 }
	 @RequestMapping("/update.do")
	 @ResponseBody
	 public JsonResult update(String noteId, String title, String body) throws NoteNotFoundExcepotion {
	     boolean success = noteService.update(noteId, title, body);
	     return new JsonResult(success);
	 }
	 @RequestMapping("/delete.do")
	 @ResponseBody
	 public JsonResult delete(String noteId) throws NoteNotFoundExcepotion {
	     boolean b = noteService.deleteNote(noteId);
	     return new JsonResult(b);
	 }
}

//≤‚ ‘£∫http://localhost:8888/note/note/add.do?notebookId=c8d81ee5-f8cd-49e8-b2e6-ab174a926d95&userId=48595f52-b22c-4485-9244-f4004255b972&title=Hello





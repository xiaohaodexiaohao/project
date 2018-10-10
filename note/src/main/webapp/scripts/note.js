//script/note.js 注意UTF-8编码
var SUCCESS = 0;
var ERROR = 1;

$(function(){
	//var userId = getCookie('userId');
	//console.log(userId);
	loadNotebooks();
	
	//on()方法绑定事件可以区别事件源
	//click() 方法绑定事件，无法区别事件源
	//绑定笔记本列表区域的点击事件
	$('#notebook-list').on('click','.notebook', loadNotes);
	//$('#notebook-list').click(loadNote);
	$('#note-list').on('click','.note', loadNote);
	
	//$('#can').load('alert/alert_note.html');
	//$('#note-list').on('click', '#add_note', showAddNoteDialog);
	$('#note-list').on('click','#add_note',showAddNoteDialog);
	
	$('#notebook-list').on('click','#add_notebook',showAddNotebookDialog);
	//监听新建笔记对话框中的创建笔记按钮
	$('#can').on('click','.create-notebook',addNotebook);
	
	//$('#createnotebook').on('click', addNotebook);
	$('#can').on('click','.close,.cancel',closeDialog);
	$('#can').on('click','.create-note',addNote);
	
	
	
	//绑定点击保存笔记事件
	$('#save_note').on('click', updateNote);
	
	//绑定笔记子菜单的触发事件
	$('#note-list').on('click','.btn-note-menu', showNoteMenu);
	//监听整体的文档区域, 任何位置点击都要关闭笔记子菜单
	$(document).click(hideNoteMenu);
	//监听笔记子菜单中删除按钮的点击
	$('#note-list').on('click', '.btn_delete', showDeleteNoteDialog);
	//监听删除笔记对话框中的确定按钮 
	$('#can').on('click', '.delete-note', deleteNote);
});
/** 删除笔记功能 */
function deleteNote(){
    var url = 'note/delete.do';
    var id = $(document).data('note').id;
    var data = {noteId:id};
    $.post(url, data, function(result){
        if(result.state==SUCCESS){
            //删除成功, 在当前笔记列表中删除笔记
            //将笔记列表中的第一个设置为当前笔记, 否则清空边编辑区域
            var li = $('#note-list .checked').parent();
            var lis = li.siblings();
            if(lis.size()>0){
                lis.eq(0).click();
            }else{
                $('#input_note_title').val("");
                um.setContent("");
            }
            li.remove();
            closeDialog();//关闭对话框!
        }else{
            alert(result.message);
        }
    });

}
/** 打开删除笔记对话框 */
function showDeleteNoteDialog(){
    var id = $(document).data('note').id;
    if(id){
        $('#can').load('alert/alert_delete_note.html', loadNotebookOptions);
        $('.opacity_bg').show();
        return;
    }
    alert('必须选择笔记!');
}
/** 加载移动笔记对话框中的笔记本列表 */
function loadNotebookOptions(){
    var url = 'notebook/list.do';
    var data={userId:getCookie('userId')};
    
    $.getJSON(url, data, function(result){
        if(result.state==SUCCESS){
            var notebooks = result.data;
            //清楚全部的笔记本下拉列表选项
            //添加新的笔记本列表选项
            $('#moveSelect').empty();
            var id=$(document).data('notebookId');
            for(var i=0; i<notebooks.length; i++){
                var notebook = notebooks[i];
                var opt=$('<option></option>')
                    .val(notebook.id)
                    .html(notebook.name);
                //默认选定当时笔记的笔记本ID
                if(notebook.id==id){
                    opt.attr('selected','selected');
                }
                $('#moveSelect').append(opt);
            }
        }else{
            alert(result.message);
        }
    });
}
/** 关闭笔记子菜单事件处理方法 */
function hideNoteMenu(){
    $('.note_menu').hide();
}
/** 显示笔记子菜单处理方法 */
function showNoteMenu(){
    //找到菜单对象, 调用show() 方法
    var btn = $(this);
    //如果当前是被选定的 笔记项目 就弹出子菜单
    btn.parent('.checked').next().toggle();
    //btn.parent('.checked') 获取当前按钮的父元素
    //这个元素必须符合选择器'.checked', 如果不
    //符合就返回空的JQuery元素.  
    return false;//阻止点击事件的继续传播!避免传播到document对象时候, 触发关闭菜单事件
}

function addNotebook(){
	console.log(1222223);
	var url = 'notebook/add.do';
    var name = $('#can #input_notebook').val();

    var data = {userId:getCookie('userId'),
        name:name};
    //console.log(data);

    $.post(url, data, function(result){
        if(result.state==SUCCESS){
            var notebook = result.data;
            console.log(notebook);
            //showNotebooks(notebook);
            //找到显示笔记列表的ul对象
            var ul = $('#notebook-list ul');
            //创建新新的笔记列表项目 li 
            var li = notebookTemplate.replace(
                    '[name]', notebook.name);
            li = $(li);
            li.data('notebookId',notebook.id);
            //设置选定效果
            ul.find('a').removeClass('checked');
            li.find('a').addClass('checked');
            //插入到笔记列表的第一个位置
            ul.prepend(li);
            //关闭添加对话框
            closeDialog();   
        }else{
            alert(result.message);
        }
    });
}

function showAddNotebookDialog(){
	
	 $('#can').load('alert/alert_notebook.html', function(){
         $('#input_notebook').focus();
     });
     $('.opacity_bg').show();
     return;
}


function updateNote(){
	
    var url = 'note/update.do';
    var note = $(document).data('note');
    var data = {noteId:note.id};
    var modified = false;
    var title = $('#input_note_title').val();
    if(title && title!=note.title){
        data.title = title;
        modified = true;
    }
    var body = um.getContent();
    if(body && body != note.body ){
        data.body = body;
        modified = true;
    }
    if(modified){

        $.post(url, data, function(result){

            if(result.state == 0){
                //console.log("Success!");
                //内存中的 note 改成新的数据
                note.title = title;
                note.body = body;
                var l = $('#note-list .checked').parent();
                $('#note-list .checked').remove()
                var li = noteTemplate.replace( '[title]', title);
                var a = $(li).find('a');
                a.addClass('checked');
                l.prepend(a);
            }else{
                alert(result.mesage);
            }
        });
    }
}
function addNote(){
	var url = 'note/add.do';
	var notebookId = $(document).data('notebookId');
	var userId = getCookie('userId');
	var title = $('#input_note').val();
	var data = {notebookId:notebookId ,userId:userId ,title:title};
	console.log(data);
	$.getJSON(url,data,function(result){
		if(result.state == SUCCESS){
			var note = result.data;
			//console.log(note);
			showNote(note);
			var ul = $('#note-list ul');
			var li = noteTemplate.replace('[title]',note.title);
			
			li = $(li);
			
			//绑定笔记ID到Li
			li.data('noteId',note.id);
			//设置选定效果
			ul.find('a').removeClass('checked');
			li.find('a').addClass('checked');
			//插入笔记本列表的第一个位置
			ul.prepend(li);
			//ul.prepend(li);
			//parent.document.location.reload();
			closeDialog();  
			
			
		}else{
			alert(result.message);
		}
	});
}


function closeDialog(){
	//隐藏背景色
	$('.opacity_bg').hide();
	$('#can').empty();
}
function showAddNoteDialog(){
	var id = $(document).data('notebookId');
	if(id){
		$('#can').load('alert/alert_note.html',function(){
			$('#input_note').focus();
		});
		//显示背景色
		$('.opacity_bg').show();
		return;
	}
	alert('必须选择笔记本');
}

//加载笔记内容
function loadNote(){
	var li = $(this);
	//添加被点击的笔记的选定效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	//console.log(id);
	
	var url = 'note/load.do';
	var data = {noteId:li.data('noteId')};
	
	$.getJSON(url,data,function(result){	
		console.log(result);
		
		if(result.state == SUCCESS){
			var note = result.data;
			showNote(note);
		}else{
			alert(result.message);
		}
		
	});
}
function showNote(note){
	//显示文章标题
	$("#input_note_title").val(note.title);//input_note_title
	//显示文章内容
	um.setContent(note.body);
	//绑定笔记信息, 用于保存操作
    $(document).data('note', note);
}

//加载全部笔记
function loadNotes(){
	var li = $(this);//当前被点击的对象li
	
	//在被点击的笔记本添加选定效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	
	var url = 'note/list.do';
	var data = {notebookId:li.data('notebookId')};
	console.log(data);
	
	$(document).data('notebookId',li.data('notebookId'));
    $.getJSON(url,data,function(result){
    	if(result.state == SUCCESS){
    		var notes = result.data;
    		showNotes(notes);
    	   
    	}else{
    		alert(result.message);
    	}
    });
	
}
function showNotes(notes){
	//console.log(notes);
	var ul = $('#note-list ul');
	
	ul.empty();
	for(var i=0;i<notes.length;i++){
		var note = notes[i];
		var li = noteTemplate.replace('[title]',note.title);
		li = $(li);
		//将note的id绑定到li上
		//console.log(note.id);
		li.data('noteId',note.id);
		ul.append(li);
	}
	
}
var noteTemplate = '<li class="online note">'+
'<a>'+
'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> [title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down btn-note-menu"><i class="fa fa-chevron-down"></i></button>'+
'</a>'+
'<div class="note_menu" tabindex="-1">'+
'<dl>'+
    '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
'</dl>'+
'</div>'+
'</li>';

var notebookTemplate = '<li class="online notebook">'+
'<a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i> [name]</a></li>';

function loadNotebooks(){
	
	//利用ajax从服务器获取get数据
	var url = 'notebook/list.do';
	var data = {userId:getCookie('userId')};
	
	$("#nick").html("您好："+getCookie('nick'));
	//$("#user").val(1233);
	$.getJSON(url,data,function(result){
		//console.log(result);
		if(result.state==SUCCESS){
			var notebooks = result.data;
			//showNotebooks将全部笔记本数据展示到notebook-list区域
			showNotebooks(notebooks);
		}else{
			alert(result.message);
		}		
	});
	
	/**
	 * 在notebooks-list区域中显示笔记本列表
	 */
	function showNotebooks(notebooks){
		//找出笔记本的ul元素   遍历notebooks数组
		//将li元素添加到ul元素中去
		
		var ul = $('#notebook-list ul');
		ul.empty();
		for(var i=0;i<notebooks.length;i++){
			var notebook = notebooks[i];
			var li = notebookTemplate.replace('[name]',notebook.name);
			//var li = $('<li></li>').html(notebook.name);
			li=$(li);
			//将notebook.id绑定到li中
			li.data('notebookId',notebook.id);
			ul.append(li);
		}
	}
	var notebookTemplate = '<li class="online notebook">'+
		'<a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i> [name]</a></li>';
	
}













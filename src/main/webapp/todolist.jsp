<%@page import="dto.Todo"%>
<%@page import="java.util.List"%>
<%@page import="dao.TodoRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <link rel="stylesheet" type="text/css" href="assets/css/todolist.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/form.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/todoitem.css" />
    <meta charset="UTF-8">
    
    <script src="assets/js/common.js"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
            
    <title>Todo List</title>
    
    <script>
      function setDone(id) {
    	  
    	  $.ajax({
    		  url: "toggleTodo.do",
    	  	  type: "post",
    	  	  data: {"id" : id},
    	  	  success: function(data){
    	  		  window.location.reload();
    	  	  }
    	  })
    	  
      }

      function addTodo() {
    	  console.log('클릭');
    	  //postData('processAddTodo.jsp', {task : "test"});
    	  let task = $('#text').val();
    	  $.ajax({
    		  url: "addTodo.do",
    		  type: "post",
    		  data: {"task" : task},
    		  success: function(data){
    			  window.location.reload();
    		  }
    	  })
      }

      function remove(id) {
    	  console.log('제거');
    	  $.ajax({
    		  url:"removeTodo.do",
    	  	  type: "post",
    	  	  data: {"id" : id},
    	  	  success: function(data){
    	  		  window.location.reload();
    	  	  }
    	  })

      }
      
      function clearTodo(){
    	  console.log('초기화');
    	  $.ajax({
    		  url:"clearTodo.do",
    		  type: "get",
    		  success: function(data){
				  window.location.reload();    			  
    		  }
   		  })
      
      }

    </script>
  </head>

  <body>
  
  <%
   // MVC 패턴(컨트롤러) 서버측 저장 : 세션(get) setAttribute(컬렉션 리스트 값 저장) 후 세션값 get하여 컬렉션 리스트값 출력해보는 경우입니다.
   List<Todo> todosMVC = (List<Todo>) session.getAttribute("todos");
   out.println(todosMVC);
  
   //싱글턴 패턴으로 dao 패키지 컬렉션 리스트 전체를 리턴하여 출력해주는 경우를 살펴보았습니다.
   TodoRepository repository = TodoRepository.getInstance();
   out.println(repository.getTodos());
   
   List<Todo> todos = repository.getTodos();
  %>
  
    <div class="todo-list-template">
      <div class="title">오늘 할 일</div>

      <section class="form-wrapper">
        <div class="form">
          <input name="text" id="text" placeholder="오늘 할 일을 적어주세요..." />
          <div class="create-button" onclick="addTodo();">추가</div>
          <div class="create-button" onclick="clearTodo();">초기화</div>
        </div>
      </section>

      <section class="todos-wrapper">
      <%
      for(Todo todo : todos){
      %>
        <div class="todo-item" onclick="setDone(<%= todo.getId() %>)">
        <div class="remove" onclick="remove(<%= todo.getId() %>)">&times;</div>
	    <div class="todo-text <%=todo.isDone() ? "checked" : "" %>"><%= todo.getTask() %></div>
	    <%
	    if(todo.isDone()){
	    %>
	    <div class="check-mark">&#x2713;</div>
	    <%
	    }
	    %>
	    </div>
      <%
      }
      %>
        
<!--         <div class="todo-item">
          <div class="remove">&times;</div>
          <div class="todo-text checked">숙제</div>
          <div class="check-mark">&#x2713;</div>
        </div>
        
        <div class="todo-item">
          <div class="remove">&times;</div>
          <div class="todo-text">청소</div>
        </div> -->
        
      </section>
    </div>
  </body>

  </html>
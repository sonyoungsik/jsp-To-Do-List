<%@page import="dto.Todo"%>
<%@page import="dao.TodoRepository"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 

//request.setCharacterEncoding("UTF-8");

TodoRepository repository = TodoRepository.getInstance();

List<Todo> todos = repository.getTodos();

todos.clear();

response.sendRedirect("todolist.jsp");

%>